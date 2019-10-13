**Aluno [1160656](../)** - SG04
=======================================

# 1. Requisitos



**Demo1** Como SG pretendo obter a elevação (em metros) relativamente ao nível médio da água do mar de um determinado local.

- SG4.1.Esta informação deve ser obtida usando o serviço externo MBS.

- SG04.2. Esta informação deve ser obtida usando o serviço externo GMS.

- SG04.3. Esta informação deve ser obtida por combinação (i.e. valor médio) dos resultados dos serviços externos anteriores.

- SG04.4. O sistema deve usar o método anterior que estiver configurado para o efeito.

Através da interpretação do enunciado deste requisito, cheguei à conclusão que o seu principal intuito é de obter a elevação de um determinado local. Primeiro terei de obter a elevação de um determinado ponto na google e posteriormente na bing. Por fim, terei de fazer a média desses dois valores, para obter o valor mais preciso possível.

# 2. Análise

Para a realização deste uc, necessitei de fazer uma pesquisa extensa, para perceber o funcionamento dos API da google e microsoft. Posteriormente, tive de me informar em relação à construção dos urls específicos para este UC, quer da bing, que da microsoft. Foi também necessário analisar o código dos casos de uso SG01 e SG02, para adaptar corretamente o meu, mediante das classes/ métodos, obtidos pelos meus colegas.

# 3. Design


## 3.1. Realização da Funcionalidade
Esta funcionalidade está aplicada num serviço externo, logo o seu funcionamento foi maioritariamente testado a nível de testes unitários.
O UC processa-se da seguinte forma:
1) Na classe HTTPClient existem dois métodos de nome prepareDynamicURLElevation
um para a bing e outro para a google. Esses métodos criam um url usado para obter a elevação de uma determinada localização. Essa localização é recebida por parâmetros (latitude e longitude).

2) Posteriormente, na mesma classe existe o método getResultHttpAsStream() que recebe o url criado e faz o pedido à API respetiva.

3) Nas classes GoogleMapsServices e MicrosoftBingServices existe um método de nome obterJSONElavation() que recebe as coordenadas e acede à API para retornar a informação em formato JSON.

4) Posteriormente, nessas mesmas classes o método GetElevation percorre o ficheiro de JSON (obtido no método anterior) e obtém a elevação em metros da localização fornecida nos parâmetros(latitude e longitude).

5) Por fim, na classe GoogleAndBingServices o método mediaElevation() retorna a média das elevações obtidas pela google e pela microsoft, para uma dada localização fornecida nos parâmetros(latitude e longitude).


http://prntscr.com/nzessx

## 3.2. Diagrama de Classes

http://prntscr.com/nzodcc

## 3.3. Padrões Aplicados

*Nesta secção deve apresentar e explicar quais e como foram os padrões de design aplicados e as melhores práticas*

## 3.4. Testes

**Teste 1:**  Testes para verificar se o url elevation está corretamente construído.
    @Test
    public void testPrepareDynamicURLElevationGoogle() {
        System.out.println("prepareDynamicURLElevationGoogle");
        Double latitude = 20.0;
        Double longitude = 10.0;
        String expResult = "https://maps.googleapis.com/maps/api/elevation/json?locations=20.0,10.0&key=AIzaSyCbWGXQ1jlIW2z73nF8QqD_hvUm6AEcp1Y";
        String result = HTTPClient.prepareDynamicURLElevationGoogle(latitude, longitude);
        assertEquals(expResult, result);


    @Test
    public void testPrepareDynamicURLElevationBing() {
        System.out.println("prepareDynamicURLElevationBing");
        Double latitude = 35.89431;
        Double longitude = -110.72522;
        String expResult = "http://dev.virtualearth.net/REST/v1/Elevation/List?points=35.89431,-110.72522&key=Al1EAkK3EqBNoUdCQv97MAxdU8KcxPEdxuhMOoPxG4UMbrwbfVKETUpRCdS-8RBK";
        String result = HTTPClient.prepareDynamicURLElevationBing(latitude, longitude);
        assertEquals(expResult, result);

    }

**Teste 2:** Testes para verificar se a elevation obtida pelo método corresponde à real, ou seja, a obtida pela api respetiva.

   @Test
   public void testGetElevation() throws Exception {
       System.out.println("getElevation");
       double latitude = 20;
       double longitude = 10;
       GoogleMapsServices instance = new GoogleMapsServices();
       Double expResult = 538.543212890625;
       Double result = instance.getElevation(latitude, longitude);
       assertEquals(expResult, result);

   }

 @Test
 public void testGetElevation() throws Exception {
    System.out.println("getElevation");
    Double latitude = 35.89431;
    Double longitude = -110.72522;
    MicrosoftBingServices instance = new MicrosoftBingServices();
    long expResult = 1776;
     long result = instance.getElevation(latitude, longitude);
    assertEquals(expResult, result);
}



**Teste 3:** Teste para verificar se a elevação final obtida é igual à média das elevações obtidas pelos sistema google e microsoft.

    @Test
    public void testMediaElevation() throws Exception {
        System.out.println("mediaElevation");
        double latitude = 27.0;
        double longitude = 86.0;
        GoogleAndBingServices instance = new GoogleAndBingServices();
        double expResult = 358.64666748046875;
        double result = instance.mediaElevation(latitude, longitude);
        assertEquals(expResult, result, 0.0);

    }




# 4. Implementação

A implementação seguiu no geral o design proposto no início, sendo que a única alteração foi a adição da classe GoogleAndBingServices que tem como intuito calcular a média entre as duas elevações(bing e google).



# 5. Integration/Demonstration

Esta funcionalidade foi integrada no sistema externo de georeferenciação já implementado nos SG01 e SG02. Sendo adicionada uma classe que "une" o sistema de georeferencia da google e da bing.

# 6. Observações

Considero que tive uma boa prestação na realização deste UC, tendo conseguido apresentar os requisitos propostos no enunciado funcionais. A nível de teste houve uma melhoria significativa, tendo feito testes a todas os métodos realizados.
