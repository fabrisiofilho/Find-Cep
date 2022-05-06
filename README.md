# Find-Cep

## Consome duas api prinmariamente, ViaCep e Widenet.

### Requisição e Endpoint

Não temos retrição, e publico, então apenas um get sem precisar passar nada já retornaria.

```
  Get -> Para URL/cep.api/{cep}
  Retornar um json com o Objeto CEP.
  {
    "cep": "111111111",
    "logradouro": "Rua X",
    "complemento": "de 744/745 a 1199/1200",
    "bairro": "Bairro X",
    "localidade": "Cidade X",
    "uf": "Estado X em sigla"
  }
```

### Planos futuros

Buscar um forma aonde seja feito todas as requisições ao mesmo tempo para diversas APIS e pegar a que retornar mais rapido.
