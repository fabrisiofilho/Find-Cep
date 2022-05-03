package br.com.fabrisio.cepapi.service;

import br.com.fabrisio.cepapi.dto.Cep;

public interface CepService {

    Cep search(String cep);

}
