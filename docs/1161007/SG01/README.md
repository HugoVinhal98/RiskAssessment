**Aluno [1161007](../)** - SG01
=======================================

# 1. Requisitos

**SG01:** Como SG pretendo obter a localização GPS de um determinado local a partir do seu endereço postal.

* SG01.1. Esta informação deve ser obtida usando o serviço externo Microsoft Bing Services (MBS). 

* SG01.2. Esta informação deve ser obtida usando o serviço externo Google Maps Services (GMS). 

* SG01.3. O sistema deve usar o serviço que estiver configurado para o efeito.

A interpretação feita deste requisito foi no sentido de, como objetivo principal, a criação de um serviço externo de georeferenciação no qual o utilizador, através de um endereço postal fornecido pelo mesmo,
obtesse a informação desejada, neste caso a localização GPS.

Desta forma o serviço externo deverá disponibilizar a interação com a Microsoft Bing Services (MBS) ou através da Google Maps Services (GMS) para assim obter as informações desejadas.

# 2. Análise

**Requesitos técnicos do UC:**

* Establecer uma ligação com os serviços externos da Google Maps Services e Micsoft Bing Services;

**Considerações importantes para o Design:**

* A implementação será organizada através de um interligação modular entre classes no serviço de georeoreferência.

* Quanto à persistência de dados, irá haver dependência de determinadas classes de dominío já existentes, para assim ser possível o armazenamento dos dados.

# 3. Design

## 3.1. Realização da Funcionalidade

**Diagrama de Sequência:**
![Image](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/f4a934a455eb090ebcdc1317eaa925aaa192a050/docs/1161007/SG01/SD.png)


## 3.2. Diagrama de Classes

![Image](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/f4a934a455eb090ebcdc1317eaa925aaa192a050/docs/1161007/SG01/CD.png)

## 3.4. Testes 

Nesta secção verifica-se através de testes tanto os métodos criados para os pedidos ao sistema externo da microsoft bing, assim como para os serviços da google maps.

**Teste 1:** 

* Verificar que é possivel obter as coordenadas GPS através de um endereço postal válido usando os serviços externos do google maps.
* Verificar que não é possivel devolver as coordenadas GPS caso sejam inseridos dados inválidos( retornando null ) usando os serviços externos do google maps.

	@Test
    public void testGetCoordenadasFromJson() throws Exception {
        System.out.println("getCoordenadasFromJson");
        GoogleMapsServices instance = new GoogleMapsServices();

        String rua = "rua joaquim moreira da silva";
        String localidade = "vila do conde";
        String endereco = "4480";

        String rua2 = "...";
        String localidade2 = "...";
        String endereco2 = "...";

        List<Double> expResult = new ArrayList<>();
        List<Double> expResult2 = null;

        expResult.add(41.3442806);
        expResult.add(-8.7476959);

        List<Double> result = instance.getCoordenadasFromJson(rua, localidade, endereco);
        List<Double> result2 = instance.getCoordenadasFromJson(rua2, localidade2, endereco2);

        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
    }
	
	
	
**Teste 2:**
	
* Verificar que é possivel obter as coordenadas GPS através de um endereço postal válido usando os serviços externos da microsoft bing.
* Verificar que não é possivel devolver as coordenadas GPS caso sejam inseridos dados inválidos( retornando null ) usando os serviços externos da microsoft bing.

	@Test
    public void testGetCoordenadasFromJson() throws Exception {
        System.out.println("getCoordenadasFromJson");
        MicrosoftBingServices instance = new MicrosoftBingServices();

        String rua = "rua joaquim moreira da silva";
        String localidade = "vila do conde";
        String endereco = "4480";

        String rua2 = "...";
        String localidade2 = "...";
        String endereco2 = "...";

        List<Double> expResult = new ArrayList<>();
        List<Double> expResult2 = null;

        expResult.add(41.34431);
        expResult.add(-8.74794);

        List<Double> result = instance.getCoordenadasFromJson(rua, localidade, endereco);
        List<Double> result2 = instance.getCoordenadasFromJson(rua2, localidade2, endereco2);

        assertEquals(expResult, result);
        assertEquals(expResult2, result2);

    }
	
A partir dos testes representados é também possível afirmar que os restantes métodos implementados em função deste caso se uso também estão a funcionar corretamente visto que ambos os métodos chamam os restantes.

# 4. Implementação

Quanto à conformidade entre a implementação e o design efetuado, ambos se encontram de acordo. No entanto, ao longo do decorrer da implementação vi-me obrigado a proceder a determinadas alterações ao design inicialmente proposto para que assim se correspondessem.

# 5. Integration/Demonstration

Para a integração da funcionalidade desonvolvida, sendo esta a primeira a ser concebida no que toca aos casos de uso relativos ao serviço de georeferenciação(SG), foi criado um serviço externo denominado de GeoReferenceService. 

Neste serviço serão implementadas todas as funcionalidades relativas ao SG, o qual se encontra divido em classes para melhor organização do projeto:

* HTTPClient, onde se encontram implementados os métods que fazem a ligação às API's;
* MicrosoftBingServices, onde se encontram implementados os métodos especificos às funcionalidade exigidas de acordo com os paràmetros exigidos pela API da miscrosoft bing;
* GoogleMapsServices, onde se encontram implementados os métodos especificos às funcionalidade exigidas de acordo com os paràmetros exigidos pela API da google maps.

![Image](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/8d34d0d08c8541da5d452f753a759dfdc0306d18/docs/1161007/SG01/integra%C3%A7%C3%A3o.png)
# 6. Observações

No que toca ao decorrer do desenvolvimento da funcionalidade, foi nos primeiro dias onde foram encontrados os maiores problemas. Fazer a ligação aos sistemas externos foi a parte mais complicada, no entanto, após perceber como se procedia tornou-se mais fácil desenvolver o restante caso de uso.