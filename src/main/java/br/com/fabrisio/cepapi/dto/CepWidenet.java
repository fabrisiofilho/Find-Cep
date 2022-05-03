package br.com.fabrisio.cepapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CepWidenet {

    private Integer status;
    private String code;
    private String state;
    private String city;
    private String district;
    private String address;

    public Cep toCep() {
        Cep cep = new Cep();
        cep.setCep(this.getCode());
        cep.setBairro(this.getDistrict());
        cep.setComplemento("Sem complemento registrado");
        cep.setLocalidade(this.getCity());
        cep.setLogradouro(this.getAddress());
        cep.setUf(this.getState());
        return cep;
    }

}
