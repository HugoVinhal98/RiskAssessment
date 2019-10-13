**Aluno [1161007](../)** - AR06
=======================================

# 1. Requisitos

**AR06:** Como AR pretendo registar a existência de uma envolvente (e.g. Refinaria da Petrogal) de um determinado tipo (e.g. Local Inflamável) numa determinada localização (e.g. Av. da Liberdade, Leça da Palmeira).

* AR06.1. Com base na informação introduzida, o sistema deve obter a localização GPS do mesmo e solicitar a sua confirmação e/ou retificação. 
* AR06.2. Pelo menos uma imagem aérea centrada na envolvente registada deve ser exibida ao AR.

**Requesitos técnicos do UC**

* Registar uma nova envolvente e ser possível verificar se o nome da mesma corresponde ao tipo inserido pelo analista de risco.

* A imagem aérea mostrada ao AR corresponde ao nome da envolvente inserida, quer este tenha retificado ou não.

# 2. Análise

Este UC está dependente de casos de uso já realizados nas iterações anteriores, sendo eles o SG03, o qual depende diretamente, e por sua vez o SG01 que também é por sua vez necessário para a realização do SG03.

Posto isto, a realização da funcionalidade de retificação do nome da envolvente é suportada através da localização GPS com base na informação obtida, o sistema de georeferenciação utiliza esta informação para encontrar as respetivas envolventes dando ao utilizador a opção de escolher uma das mesmas. De seguida o utilizador confirma ou não a retificação da mesma e conclui assim o processo de criação da envolvente.

Ainda antes da persistência da nova envolvente é também possível ao utilizador visualizar uma imagem aérea centrada na envolvente criada, a qual é possibilitada por uma nova funcionalidade criada no serviço de georeferenciação.

Para esta ultima funcionalidade é necessário gerar uma nova chave para a API da Google Maps para desempenhar a respetiva função. A imagem aérea é mostrada no desktop do utilizador, o qual tem a opção entre uma imagem mais ou menos centrada.

# 3. Design

## 3.1. Realização da Funcionalidade

**Diagrama de Sequência:**

![Image](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/a6fa3010b48d094a1db7965ede82095bf8c47e0b/docs/1161007/AR06/SD.png)

## 3.2. Diagrama de Classes

![Image](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/d89fdcc6c02c450f8b67c2d7d15dbb961f54cf68/docs/1161007/AR06/CD.png)

## 3.3. Padrões Aplicados

Para a realização do caso de uso foram aplicados diversos padrões:

* Padrão repositório, utilizado para estabelecer a ligação com a base de dados e persistir os dados inseridos pelo utilizador;


## 3.4. Testes

**Teste 1:**

* Verificar que é possivel obter as coordenadas GPS através de um endereço postal.

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

* Verificar que é possível obter as envolventes através do serviço da google maps.

    @Test
	public void testGetNearbyPlacesFromJson() throws Exception {
        
		System.out.println("getNearbyPlacesFromJson");   
		Double latitude = -33.8670522;       
		Double longitude = 151.1957362;       
		String tipo = "restaurant";        
		Double raio = 30.0;        
		GoogleMapsServices instance = new GoogleMapsServices();        
		String string1 = "Biaggio Cafe/-33.8669667/151.1958862";      
        List<String> expResult =  new ArrayList();
        expResult.add(string1);
        List<String> result = instance.getNearbyPlacesFromJson(latitude, longitude, tipo, raio);
        assertEquals(expResult, result);
       
    }

# 4. Implementação

Quanto à conformidade entre a implementação e o design efetuado, ambos se encontram de acordo. No entanto, ao longo do decorrer da implementação vi-me obrigado a proceder a determinadas alterações ao design inicialmente proposto para que assim se correspondessem.

# 5. Integration/Demonstration

* Este caso de uso vem integrar no projeto como mais uma funcioanlidade disponível ao analista de risco.

* Quando às depêndencias, como já foi referido anteriormente, o UC utilizada funcionalidades anteriormente implementados nos casos de uso de sistema de georeferenciação 1 e 3.

# 6. Observações

Este UC foi útil para dinamizar mais o trabalho, atribuindo mais dependências entre as funcionalidades, no qual o analista de risco utiliza casos desenvolvidos no sistema de georreferênciação.