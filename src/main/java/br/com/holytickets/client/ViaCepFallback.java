package br.com.holytickets.client;

import br.com.holytickets.dto.CepDTO;
import org.springframework.stereotype.Component;

@Component
public class ViaCepFallback implements ViaCepClient {
    @Override
    public CepDTO buscarEnderecoPorCep(String cep) {
        return null;
    }
}

