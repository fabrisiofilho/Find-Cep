package br.com.fabrisio.cepapi.service;

import br.com.fabrisio.cepapi.dto.Cep;
import br.com.fabrisio.cepapi.dto.CepViaCep;
import br.com.fabrisio.cepapi.dto.CepWidenet;
import br.com.fabrisio.cepapi.exceptions.CepExpetion;
import br.com.fabrisio.cepapi.exceptions.RequestException;
import br.com.fabrisio.cepapi.utils.Util;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Slf4j
public class CepServiceImpl implements CepService {

    private String VIA_CEP = "http://viacep.com.br/ws/";
    private String WIDENET = "https://ws.apicep.com/cep/";

    @Override
    public Cep search(String cep) {
        return find(cep);
    }

    private Cep find(String cep) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            log.info("Começando processo de procura por Widenet");
            return findByWidenet(cep).toCep();
        } catch(Exception e) {
            log.info("Começando processo de procura por ViaCep");
            return modelMapper.map(findByViaCep(cep), Cep.class);
        }
    }

    private CepViaCep findByViaCep(String cep) {
        String url = VIA_CEP + cep.replace("-", "") + "/json/";
        Gson gson = new Gson();
        try {
            CepViaCep json = gson.fromJson(request(url), CepViaCep.class);
            if (json.isErro()) {
                throw new CepExpetion("Não foi encontrado nenhum registro");
            }
            return json;
        }catch(Exception e) {
            log.error("Cep não encontrado no ViaCep");
            throw new CepExpetion("Erro : " + e.getMessage());
        }
    }

    private CepWidenet findByWidenet(String cep) {
        String url = WIDENET + cep + ".json";
        Gson gson = new Gson();
        try{
            CepWidenet json = gson.fromJson(request(url), CepWidenet.class);
            if (json.getStatus().equals(404)) {
                throw new CepExpetion("Não foi encontrado nenhum registro");
            }
            return json;
        } catch (Exception e) {
            log.error("Cep não encontrado no Widenet");
            throw new CepExpetion("Erro : " + e.getMessage());
        }
    }

    private String request(String url) {
        try {
            URL urlRequest = new URL(url);

            HttpURLConnection conexao = (HttpURLConnection) urlRequest.openConnection();

            if (conexao.getResponseCode() != 200)
                throw new RequestException("HTTP error code : " + conexao.getResponseCode());

            BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            return Util.converteJsonEmString(resposta);
        } catch(Exception e) {
            throw new CepExpetion("Erro : " + e.getMessage());
        }
    }

}
