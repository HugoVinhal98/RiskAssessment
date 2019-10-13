**Aluno [1150870](../)** - SE02
=======================================

# 1. Requisitos

**SE02** Como SE pretendo obter o resultado (final ou o status atual) de um Pedido de Avaliação de Risco previamente submetido.

Esta funcionalidade está inserida no contexto de um Sistema Externo que pretende ser responsável pelo processamento dos pedidos de análise de risco.

Este UC e a suas funcionalidades estarão dependentes da implementação do UC anterior ( submeter um Pedido de Avaliação de Risco para um ou mais locais ) visto que para obter um resultado primeiro é preciso existir um pedido de avaliação de risco e este processado. Após lidos os ficheiros relativos ao pedido, processado esse mesmo e persistidos os dados é possivel então o envio de um HTTP com os dados relativos a esse pedido. 

Algo em ter em conta é que o Pedido de Avaliação de Risco submetido possa ainda ter de ser validado por um Analista de Risco antes de este ser dado como concluído o que significa que neste caso o resultado esperado não seria então toda a informação associada ao pedido mas sim uma mensagem a indicar o estado do pedido, neste caso algo do género "Pedido em Validação por um Analista de Risco". 

**Requisitos Técnicos do UC:**

Os formatos dos ficheiros de saída  pelo Sistema Externo serão JSON , XML bem como XHTML.

Sendo um dos formatos de ficheiro de saída um ficheiro XML, um XSD deve ser criado para verificar a validade desse tal ficheiro XML e caso esta validade não se evidencie uma resposta deve ser enviada com uma mensagem sugestiva.

É necessário que todos os pedidos feitos ao Sistema Externo sejam adquiridos através de comunicações HTTP(S). Será usado o cliente de HTTP(S) "Postman" para este efeito, sendo que o envio dos ficheiros é feito através deste.


# 2. Análise

**Considerações Importantes para o Design:**

Toda a implementação de código usada em EAPLI para a análise de risco (desde classes de domínio, persistência, métodos de cálculo de análise de risco) terá de estar presente neste Sistema Externo, de forma a este ser capaz de:

- Obter dados persistidos após leitura de ficheiros e cálculo de certos dados para então ser possivel o envio de uma resposta com todos os dados necessários e relevantes relativos a um dado pedido

...

# 3. Design

*Nesta secção o estudante deve descrever o design adotado para satisfazer a funcionalidade. Entre outros, o estudante deve apresentar diagrama(s) de realização da funcionalidade, diagrama(s) de classes, identificação de padrões aplicados e quais foram os principais testes especificados para validar a funcionalidade.*

*Para além das secções sugeridas, podem ser incluídas outras.*

## 3.1. Realização da Funcionalidade


![picture](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/dc779017988ccf6ea0d6414bd6d445df6206b0f2/docs/1150870/SE02/SD_SE02.png)

## 3.2. Diagrama de Classes

![picture](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/dc779017988ccf6ea0d6414bd6d445df6206b0f2/docs/1150870/SE02/CD.png)

## 3.3. Padrões Aplicados

Por necessidade de em certos casos ser devolvido 2 ou mais tipos de dados diferentes, foi utilizado por 2 vezes em cenários diferentes o padrão DTO em que os 2 DTOs estão devidamente identificados ( ImprimirResultadoDTO, DadosPedidosDTO ).

Foi também utizado o padrão Repositório para aceder aos diferentes dados presistidos relativos à classe Pedido

## 3.4. Testes 


**Teste 1:** Ambos os testes ( verificar o meu xml ou outro xml) o teste seria o mesmo, daí tanto SE01 como SE02 utilizam o mesmo teste para testar se um XML é feito corretamente através da utilização de um XSD validador.


@Test
public void validate() throws SAXException, IOException {

    System.out.println("validate");

    String xsd = "ImportarPedido.xsd";
    String xml = "<Pedido><ObjetoSeguro><Nome>Isep</Nome><NecessidadeAnalista>sim</NecessidadeAnalista><ListaCoberturas><Cobertura>incendio</Cobertura><Cobertura>sismo</Cobertura></ListaCoberturas></ObjetoSeguro></Pedido>";

    boolean result, expResult = true;

    try {
        System.out.println("Validate XML against XSD Schema");
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(xsd));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new StringReader(xml)));
        result = true;
    } catch (IOException e) {
        result = false;
    } catch (SAXException e) {
        System.out.println("Error when validate XML against XSD Schema");
        System.out.println("Message: " + e.getMessage());
        result = false;
    }

    assertEquals(expResult, result);

}

# 4. Implementação

**Organização do UC SE02**

![picture](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/dc779017988ccf6ea0d6414bd6d445df6206b0f2/docs/1150870/SE02/Impl1.png)

**Ficheiros Relevantes:**

- Para validação do XML foi criado um ficheiro XSD com nome ( BuscarPedido.xsd ) que valida se o XML formado está em conformidade com o resultado esperado

# 5. Integration/Demonstration

- Foi desenvolvida uma implementação geral para o Sistema Externo, de forma a que todos os UCs relacionados com ele, e não apenas este, pudessem usufruir dessas funcionalidades.


# 6. Observações

Penso que os objetivos principais desta funcionalidade foram alcançados à excepção do formato de saída XHTML que não consegui implementar visto que dava umas complicações nas várias tentativas de implementação e então apenas apresento JSON e XML ( Validado por XSD ).

A possibilidade de implementação do protocolo HTTPS também poderia ser algo a melhorar. 