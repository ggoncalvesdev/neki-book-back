package com.nekibook.domain.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.nekibook.domain.exception.NegocioException;
import com.nekibook.domain.exception.UsuarioNaoEncontradoException;
import com.nekibook.domain.model.Grupo;
import com.nekibook.domain.model.Leitura;
import com.nekibook.domain.model.Livro;
import com.nekibook.domain.model.Summary;
import com.nekibook.domain.model.Usuario;
import com.nekibook.domain.model.dto.imgbb.ImgBBDTO;
import com.nekibook.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

  @Autowired
  UsuarioRepository userRepository;

  @Autowired
  EmailService emailService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private EntityManager manager;

  @Autowired
  private GrupoService cadastroGrupo;

  @Value("${imgbb.host.url}")
	private String imgBBHostUrl;
	
	@Value("${imgbb.host.key}")
    private String imgBBHostKey;

  @Transactional
  public List<Usuario> getAll() {
    // emailService.sendEmail("gabrielfn.neves@gmail.com", "Teste de envio de email", "Email enviado.");
    return userRepository.findAll();
  }

  @Transactional
  public List<Usuario> findByNomeUsuario(String nomeUsuario) {
    List<Usuario> usuarios = null;
    usuarios = userRepository.findAllByNomeContainingIgnoreCase(nomeUsuario);
    return usuarios;
  }

  @Transactional
  public Usuario getById(Integer id) {
	  //pegar dados do user 
	  Usuario user = userRepository.findById(id).orElse(null);
	  //filtrar as relações 
	  Set<Leitura> listaLeituras = user.getLeituras();
	  Set<Leitura> leituraResumo = new HashSet<>();
	  for (Leitura leitura : listaLeituras) {
		  Leitura novaLeitura = new Leitura();
		  novaLeitura.setIdLeitura(leitura.getIdLeitura());
		  Livro livro = leitura.getLivros();
		  livro.setLeituras(null);
		  novaLeitura.setLivros(livro);
		  novaLeitura.setStatus(leitura.getStatus());
		  
		  Set<Summary> listaSummary = new HashSet<>();
		  for(Summary summary : leitura.getSummaries()) {
			  Summary newSummary = new Summary();
			  newSummary.setComments(summary.getComments());
			  newSummary.setIdSummary(summary.getIdSummary());
			  newSummary.setSummary(summary.getSummary());
			  listaSummary.add(newSummary);
			  
		  }
		  novaLeitura.setSummaries(listaSummary);
		  leituraResumo.add(novaLeitura);
	  }
	  //devolver ususario filtrado
	  user.setLeituras(leituraResumo);
    return user;
  }

  @Transactional
  public Usuario findByEmail(String email) {
    return userRepository.findByEmail(email).get();
  }

  public Usuario saveUser(Usuario user) {
    detach(user);

    Optional<Usuario> usuarioExistente = userRepository.findByEmail(
      user.getEmail()
    );

    if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(user)) {
      throw new NegocioException(
        String.format(
          "Já existe um usuário cadastrado com o e-mail %s",
          user.getEmail()
        )
      );
    }

    return userRepository.save(user);
  }

  @Transactional
  public Usuario updateFotoUsuario(
    Integer usuarioId,
    MultipartFile file
) throws IOException {
      
    RestTemplate restTemplate = new RestTemplate();
    String serverUrl = imgBBHostUrl + imgBBHostKey;
    
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    
    MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
    
    ContentDisposition contentDisposition = ContentDisposition
        .builder("form-data")
        .name("image")
        .filename(file.getOriginalFilename())
        .build();
    
    fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
    
    HttpEntity<byte[]> fileEntity = new HttpEntity<>(file.getBytes(), fileMap);
    
    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
    body.add("image", fileEntity);
    
    HttpEntity<MultiValueMap<String, Object>> requestEntity =
        new HttpEntity<>(body, headers);
    
    ResponseEntity<ImgBBDTO> response = null;
    ImgBBDTO imgDTO = new ImgBBDTO();
    Usuario novoUsuario = new Usuario(); 
    try {
      response = restTemplate.exchange(
          serverUrl,
          HttpMethod.POST,
          requestEntity,
          ImgBBDTO.class);
      
      imgDTO = response.getBody();
      System.out.println("ImgBBDTO: " + imgDTO.getData().toString());
    } catch (HttpClientErrorException e) {
      e.printStackTrace();
    }
    
    //Converte os dados da editora recebidos no formato String em Entidade
    //  Coleta os dados da imagem, após upload via API, e armazena na Entidade Editora
    if(null != imgDTO) {
      // Usuario usuarioFromJson = convertUsuarioFromStringJson(usuarioTxt);
      Usuario usuarioFromBd = userRepository.findById(usuarioId).orElse(null);
      if(usuarioFromBd != null){

        usuarioFromBd.setImagemFileName(imgDTO.getData().getImage().getFilename());
        usuarioFromBd.setImagemNome(imgDTO.getData().getTitle());
        usuarioFromBd.setImagemUrl(imgDTO.getData().getUrl());
        novoUsuario = userRepository.save(usuarioFromBd);
      }
    }
    
    return novoUsuario;
  }

//   private Usuario convertUsuarioFromStringJson(String usuarioJson) {
// 		Usuario usuario = new Usuario();
		
// 		try {
// //			ObjectMapper objectMapper = new ObjectMapper();
// 			ObjectMapper objectMapper = new ObjectMapper()
// 					  .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		  
// 			objectMapper.registerModule(new JavaTimeModule());
			
// 			usuario = objectMapper.readValue(usuarioJson, Usuario.class);
// 		} catch (IOException err) {
// 			System.out.printf("Ocorreu um erro ao tentar converter a string json para um instância da entidade Editora", err.toString());
// 		}
		
// 		return usuario;
// 	}

  @Transactional
  public Usuario updateUser(Integer id, Usuario user) {
    Usuario userAtualizado = userRepository.findById(id).orElse(null);
    if (userAtualizado != null) {
      userAtualizado.setEmail(user.getEmail());
      // userAtualizado.setPassword(user.getPassword());
      userAtualizado.setNome(user.getNome());
      userAtualizado.setSetor(user.getSetor());
      userAtualizado.setImagemFileName(null);
      return userRepository.save(userAtualizado);
    } else {
      return null;
    }
  }

  @Transactional
  public Boolean deleteUser(Integer id) {
    Usuario user = userRepository.findById(id).orElse(null);
    if (user != null) {
      userRepository.delete(user);
      return true;
    } else {
      return false;
    }
  }

  @Transactional
  public Usuario recuperarSenha(String email) {
    Usuario user = userRepository.findByEmail(email).orElse(null);

    if (user == null) {
      //usuario não existe
    }
    int n = 6;
    String AlphaNumericString =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
    StringBuilder sb = new StringBuilder(n);

    for (int i = 0; i < n; i++) {
      int index = (int) (AlphaNumericString.length() * Math.random());

      sb.append(AlphaNumericString.charAt(index));
    }

    sb.toString();

    String novaSenha = sb.toString();
    String encodedPass = passwordEncoder.encode(novaSenha);
    user.setPassword(encodedPass);

    Usuario usuarioAtualizado = saveUser(user);

    emailService.sendEmail(
      email,
      "Aqui está sua nova senha",
      "Olá " + user.getNome() + " ,uma nova senha foi gerada: " + novaSenha
    );

    return usuarioAtualizado;
  }

  @Transactional
  public void alterarSenha(
    Integer usuarioId,
    String senhaAtual,
    String novaSenha
  ) {
    Usuario user = buscarOuFalhar(usuarioId);

    if (user.senhaNaoCoincideCom(senhaAtual)) {
      throw new NegocioException(
        "Senha atual informada não coincide com a senha do usuário."
      );
    }

    user.setPassword(novaSenha);
  }

  @Transactional
  public void desassociarGrupo(Integer usuarioId, Long grupoId) {
    Usuario usuario = buscarOuFalhar(usuarioId);
    Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

    usuario.removerGrupo(grupo);
  }

  @Transactional
  public void associarGrupo(Integer usuarioId, Long grupoId) {
    Usuario usuario = buscarOuFalhar(usuarioId);
    Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

    usuario.adicionarGrupo(grupo);
  }

  public Usuario buscarOuFalhar(Integer usuarioId) {
    return userRepository
      .findById(usuarioId)
      .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
  }

  public void detach(Usuario user) {
    manager.detach(user);
  }
}
