package br.com.holytickets.client;

import br.com.holytickets.dto.CepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCepClient", url = "https://viacep.com.br/ws", fallback = ViaCepFallback.class)
public interface ViaCepClient {

    @GetMapping("/{cep}/json")
    CepDTO buscarEnderecoPorCep(@PathVariable("cep") String cep);
}
