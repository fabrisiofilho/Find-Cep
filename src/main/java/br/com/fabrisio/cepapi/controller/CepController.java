package br.com.fabrisio.cepapi.controller;

import br.com.fabrisio.cepapi.dto.Cep;
import br.com.fabrisio.cepapi.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/cep.api")
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/{cep}")
    public Cep getCEP(@PathVariable String cep){
        return cepService.search(cep);
    }

}
