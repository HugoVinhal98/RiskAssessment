**Aluno [1150812](../)** - SG05
=======================================

# 1. Requisitos

**SG05:** Como SG pretendo obter a distância (em quilómetros) e tempo de viagem (em minutos) entre
duas localizações tendo em consideração a forma de deslocação (e.g. de automóvel, a pé).

**SG05.1** Esta informação deve ser obtida usando o serviço externo MBS.

**SG05.2** Esta informação deve ser obtida usando o serviço externo GMS.

**SG05.3** Esta informação deve ser obtida por combinação (i.e. distância e tempo médio) dos
resultados dos serviços externos anteriores.

**SG05.4**. O sistema deve usar o método anterior que estiver configurado para o efeito.

Esta funcionalidade está inserida no contexto do Serviço de Geo-Referenciação (SG), que pretende ser responsável pela obtenção de dados relativos
a diversos locais, de forma a enriquecer a informação destes.

# 2. Análise

* Para ser capaz de obter os dados desejados, tive de procurar uma API que me obtivesse essa informação. Foi encontrada, para cada um dos
serviços externos mencionados (GMS e MBS) uma API denominada Distance Matrix API no dois serviços.

* Para cada API, tive de analisar como construir o URL corretamente, de forma a que ele me devolvesse a informação corretamente.

* A construção deste URL foi feita dentro do projeto, partindo de 3 atributos dados: a localização dos dois locais a analisar e a forma
de deslocação. Atributos como o formato do tempo de viagem e das horas são colocados diretamente no URL sem que estes tenham de ser fornecidos,
dado que são sempre os mesmos.

* Depois do URL construído e do request feito, é devolvido um JSON com diversas informações relativas à informação enviada. Foi necessário
o desenvolvimento de um leitor de JSON para cada serviço que fosse capaz de ler o JSON devolvido, e fosse buscar o valor da distância e
do tempo de viagem, que depois seria colocado num DTO próprio para mais fácil manipulação dos dados obtidos.

* Relativamente à obtenção de informação através de combinação de resultados, foi desenvolvida uma funcionalidade que calcula primeiro os
valores de cada um dos serviços externos, e para cada tipo de valor (distância e duração da viagem), faz a média deles.

* Era pretendido também que o sistema usasse automaticamente um dos 3 métodos definidos (MBS, GMS ou combinação dos dois). Para isso foi
criado um ficheiro de configuração que possui o método a utilizar. Depois, uma funcionalidade criada lê a informação do ficheiro de configuração,
e consoante o que este possui ele processa o método desejado.


# 3. Design

## 3.1. Realização da Funcionalidade

![SD_1.jpg](SD_1.jpg)

## 3.2. Diagrama de Classes

![CD_1.jpg](CD_1.jpg)

## 3.3. Padrões Aplicados

* Foi utilizado o padrão DTO (Data Transfer Object) para ser mais fácil organizar os dados obtidos dos serviços.

## 3.4. Testes

**Test 1: Construtor de URL (GMS)**

     /**
     * Test of prepareDynamicURLDistanceBetweenPlacesGoogle method, of class
     * HTTPClient.
     */
    @Test
    public void testPrepareDynamicURLDistanceBetweenPlacesGoogle() {
        System.out.println("prepareDynamicURLDistanceBetweenPlacesGoogle");
        String location1 = "Porto,PT";
        String location2 = "Lisbon,PT";
        String travelType = "driving";
        String expResult = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=Porto,PT&destinations=Lisbon,PT&travelType=driving&key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";
        String result = HTTPClient.prepareDynamicURLDistanceBetweenPlacesGoogle(location1, location2, travelType);
        System.out.println(result);
        assertEquals(expResult, result);

    }

**Test 2: Construtor de URL (MBS)**

    /**
     * Test of prepareDynamicURLDistanceBetweenPlacesBing method, of class
     * HTTPClient.
     */
    @Test
    public void testprepareDynamicURLDistanceBetweenPlacesBing() {

        System.out.println("prepareDynamicURLDistanceBetweenPlacesBing");
        String location1 = "37.77916,-122.42";
        String location2 = "32.71568,-117.16172";
        String travelType = "driving";
        String expResult = "https://dev.virtualearth.net/REST/v1/Routes/DistanceMatrix?origins=37.77916,-122.42&destinations=32.71568,-117.16172&distanceUnit=km&travelMode=driving&timeUnit=minute&key=Al1EAkK3EqBNoUdCQv97MAxdU8KcxPEdxuhMOoPxG4UMbrwbfVKETUpRCdS-8RBK";
        String result = HTTPClient.prepareDynamicURLDistanceBetweenPlacesBing(location1, location2, travelType);
        System.out.println(result);
        assertEquals(expResult, result);

    }

**Test 3: Leitor de JSON (Bing)**    

    /**
     * Test of getDistanceBetweenPlacesFromJson, of class MicrosoftBingServices.
     */
    @Test
    public void testGetDistanceBetweenPlacesFromJson() throws Exception {

        System.out.println("getDistanceBetweenPlacesFromJson");
        MicrosoftBingServices instance = new MicrosoftBingServices();

        long distance = 808;
        long duration = 432;

        String location1 = "37.77916,-122.42";
        String location2 = "32.71568,-117.16172";
        String travelMode = "driving";

        DistanceBetweenPlacesDTO expResult = new DistanceBetweenPlacesDTO(distance, duration);

        DistanceBetweenPlacesDTO result = instance.getDistanceBetweenPlacesFromJson(location1, location2, travelMode);
        
        System.out.println("Result Distance = " + result.getDistance());
        System.out.println("Result Duration = " + result.getDuration());
        System.out.println("ExpResult Distance = " + expResult.getDistance());
        System.out.println("ExpResult Duration = " + expResult.getDuration());

        assertEquals(expResult, result);

    }

**Test 4: Leitor de JSON (Google)**        

    /**
     * Test of getDistanceBetweenPlacesFromJson, of class GoogleMapsServices.
     */
    @Test
    public void testGetDistanceBetweenPlacesFromJson() throws Exception {

        System.out.println("getDistanceBetweenPlacesFromJson");
        GoogleMapsServices instance = new GoogleMapsServices();

        long distance = 313;
        long duration = 177;

        String location1 = "Porto,PT";
        String location2 = "Lisbon,PT";
        String travelType = "driving";

        DistanceBetweenPlacesDTO expResult = new DistanceBetweenPlacesDTO(distance, duration);

        DistanceBetweenPlacesDTO result = instance.getDistanceBetweenPlacesFromJson(location1, location2, travelType);

        System.out.println("Result Distance = " + result.getDistance());
        System.out.println("Result Duration = " + result.getDuration());
        System.out.println("ExpResult Distance = " + expResult.getDistance());
        System.out.println("ExpResult Duration = " + expResult.getDuration());

        assertEquals(expResult, result);

    }

**Test 5: Resultados Combinados**        

    /**
     * Test of getAverageDistanceBetweenPlaces method, of class
     * GoogleAndBingServices.
     */
    @Test
    public void testGetAverageDistanceBetweenPlaces() throws Exception {

        System.out.println("getAverageDistanceBetweenPlaces");

        long averageDis = 26;
        long averageDur = 22;

        String location1 = "Seattle,WA";
        String location2 = "Redmond,WA";
        String coordinates1 = "47.6044,-122.3345";
        String coordinates2 = "47.6731,-122.1185";
        String travelType = "driving";

        GoogleAndBingServices instance = new GoogleAndBingServices();

        DistanceBetweenPlacesDTO expResult = new DistanceBetweenPlacesDTO(averageDis, averageDur);

        DistanceBetweenPlacesDTO result = instance.getAverageDistanceBetweenPlaces(location1, location2, coordinates1, coordinates2, travelType);
        
        System.out.println("Result Distance = " + result.getDistance());
        System.out.println("Result Duration = " + result.getDuration());
        System.out.println("ExpResult Distance = " + expResult.getDistance());
        System.out.println("ExpResult Duration = " + expResult.getDuration());

        assertEquals(expResult, result);

    }

**Test 6: Obtenção de distância e duração selecionando método configurado**    

    /**
     * Test of getDistanceBetweenPlacesDefault method, of class
     * GoogleAndBingServices.
     */
    @Test
    public void testGetDistanceBetweenPlacesDefault() throws Exception {

        System.out.println("getDistanceBetweenPlacesDefault");

        long distance = 26;
        long duration = 21;

        String location1 = "Seattle,WA";
        String location2 = "Redmond,WA";
        String coordinates1 = "47.6044,-122.3345";
        String coordinates2 = "47.6731,-122.1185";
        String travelType = "driving";

        GoogleAndBingServices instance = new GoogleAndBingServices();

        DistanceBetweenPlacesDTO expResult = new DistanceBetweenPlacesDTO(distance, duration);

        DistanceBetweenPlacesDTO result = instance.getDistanceBetweenPlacesDefault(location1, location2, coordinates1, coordinates2, travelType);
        
        System.out.println("Result Distance = " + result.getDistance());
        System.out.println("Result Duration = " + result.getDuration());
        System.out.println("ExpResult Distance = " + expResult.getDistance());
        System.out.println("ExpResult Duration = " + expResult.getDuration());

        assertEquals(expResult, result);

    }

# 4. Implementação

**DTO usado para organização da informação:**

![DTO.PNG](DTO.PNG)

**Exemplo de construtor de URL (Google):**

![Google_Construct.PNG](Google_Construct.PNG)

**Leitor de JSON obtido (Google):**

![JSON_Google.PNG](JSON_Google.PNG)

**Funcionalidade que retorna os resultados combinados:**

![Combined.PNG](Combined.PNG)

# 5. Integration/Demonstration

* A funcionalidade desenvolvida foi inserida dentro do sub projeto relativo ao sistema de geo-referenciação, possuindo uma estrutura
semelhante às outras que pertencem a este.

# 6. Observações

* Considero que os objetivos principais do desenvolvimento desta funcionalidade foram obtidos com sucesso.