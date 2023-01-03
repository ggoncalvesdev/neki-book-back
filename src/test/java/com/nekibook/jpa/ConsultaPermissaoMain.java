package com.nekibook.jpa;

import com.nekibook.ApiNekibookApplication;
import com.nekibook.domain.model.Permissao;
import com.nekibook.domain.repository.PermissaoRepository;
import java.util.List;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaPermissaoMain {

  public static void main(String[] args) {
    ApplicationContext applicationContext = new SpringApplicationBuilder(
      ApiNekibookApplication.class
    )
      .web(WebApplicationType.NONE)
      .run(args);

    PermissaoRepository permissaoRepository = applicationContext.getBean(
      PermissaoRepository.class
    );
    /*   List<Permissao> todasPermissoes = permissaoRepository.listar(); */

    /* for (Permissao permissao : todasPermissoes) {
      System.out.printf(
        "%s - %s\n",
        permissao.getNome(),
        permissao.getDescricao()
      );
    }*/
  }
}
