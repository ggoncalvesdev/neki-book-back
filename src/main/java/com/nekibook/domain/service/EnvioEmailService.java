package com.nekibook.domain.service;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

public interface EnvioEmailService {
    void sendEmail (Mensagem mensagem);

    @Getter
    @Builder
    class Mensagem {
        @Singular
        private Set<String> destinatarios;
        private String assunto;
        private String corpo;
    }
}
