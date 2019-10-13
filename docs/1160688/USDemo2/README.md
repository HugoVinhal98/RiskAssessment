**Aluno [1160688](../)** - SG03
=======================================

# 1. Requisitos

**SG03** Como  SG  pretendo  obter  quais  são  as  envolventes (designação  e  localização) de  um determinado tipo (e.g. Hospital) que existem na proximidade de um determinado local.

* SG03.1. Esta informação deve ser obtida usando o serviço externo MBS.

* SG03.2. Esta informação deve ser obtida usando o serviço externo GMS.

* SG03.3. Esta  informação  deve  ser  obtida  por  combinação (i.e.  união,  sem  elementos duplicados) dos resultados dos serviços externos anteriores. 

* SG03.4. O sistema deve usar o método anterior que estiver configurado para o efeito.

A interpretação feita deste requisito foi no sentido de, como objetivo principal, a adição de novas funcionalidades ao já criado serviço externo de georeferenciação, no qual o utilizador, através das coordenadas  e de um raio fornecido pelo mesmo,
obtesse a informação desejada, neste caso a envolventes de um determinado tipo.

# 2. Análise

Para a realização deste uc, foi necessária a pesquesa sobre a google api e a micrsoft api, de modo a perceber o funcionamento das mesmas. Da mesmaforma, foi preciso de uma pesquisa para perceber o funcionamento das URL de ambos os serviços.

# 3. Design

## 3.1. Realização da Funcionalidade

http://prntscr.com/nz3lgu

## 3.2. Diagrama de Classes

https://prnt.sc/nzo5ki

## 3.3. Padrões Aplicados

*Nesta secção deve apresentar e explicar quais e como foram os padrões de design aplicados e as melhores práticas*

## 3.4. Testes
Nesta secção verifica-se o funcionamento dos métodos desenvolvidos através de testes.

**Teste 1:** 

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
	
**Teste 2:**

* Verificar que é possível obter as envolventes através do serviço da microsoft bing.	

    @Test
    
	public void testGetNearbyPlacesFromJson() throws Exception {
        
		System.out.println("getNearbyPlacesFromJson");
        
		Double latitude = 47.668979;
        
		Double longitude = -122.387562;
        
		String tipo = "MovieTheaters";
        
		Double raio = 200.0;
        
		MicrosoftBingServices instance = new MicrosoftBingServices();
		
        List<String> expResult = new ArrayList<>();
        expResult.add("Careys Cinema and More/47.66808319091797-122.3854751586914");
        List<String> result = instance.getNearbyPlacesFromJson(latitude, longitude, tipo, raio);
        assertEquals(expResult, result);
        
    }
	
**Teste 3:**

* Verificar que é possível obter as envolventes através do serviço da microsoft bing e da google maps sem elemnetos duplicados.
    @Test
    
	public void testListaFinalSemDuplicados() throws Exception {
        
		System.out.println("listaFinalSemDuplicados");
        
		Double latitude = 47.668979;
        
		Double longitude = -122.387562;
        
		String tipo = "MovieTheaters";
        
		String tipo2 ="movie_theater";
        
		Double raio = 5000.0;
        
		GoogleAndBingServices instance = new GoogleAndBingServices();
        
		String string = "Majestic Bay Theatres/47.6689429/-122.3840766";
        
		String string1 = "Zigogo/47.6688724/-122.3893912";
        
		String string2 = "AMC Oak Tree 6/47.701839447021484-122.34465789794922";
        
		String string3 = "Fremont Outdoor Movies/47.701839447021484-122.3446578979492247.65167999267578-122.35448455810547";
        
		String string4 = "Careys Cinema and More/47.701839447021484-122.3446578979492247.65167999267578-122.3544845581054747.66808319091797-122.3854751586914";
        
		String string5 = "Fremont Original Outdoor Cinema/47.701839447021484-122.3446578979492247.65167999267578-122.3544845581054747.66808319091797-122.385475158691447.61935043334961-122.33849334716797";
       
	    List<String> expResult = new ArrayList();
        expResult.add(string);
        expResult.add(string1);
        expResult.add(string2);
        expResult.add(string3);
        expResult.add(string4);
        expResult.add(string5);
        List<String> result = instance.listaFinalSemDuplicados(latitude, longitude, tipo2, tipo, raio);
        assertEquals(expResult, result);
     
    }

# 4. Implementação

Ao longo da implementação foram realizadas algumas mudanças no design, mas seguindo sempre a mesma lógica de pensamento previamente definido. Neste momento o design encontra-se de acordo com a implementação.

# 5. Integration/Demonstration

Para a integração das funinalidades desenvolvidas, as mesmas foram implementadas num Sistema Externo de Georeferenciação previamente criado.Para além disso foi criada uma nova classe com o nome GoogleAndBingServices, onde foram implementados as funcionalidades(obter as envolventes através do GoogleMapsService e MicrsoftBingServices sem duplicados) que requeriam métodos das classes GoogleMapsServices e MicrosoftBingServices. 

# 6. Observações
Em relação desenvolvimento da funcionalidade, não foi tão complicado como prevíamos pelo facto de o trabalho mais difícil(fazer ligação aos sistemas externos) já ter sido implementado pelos colegas que ficaram responsáveis na semana anterior pelo SG.
