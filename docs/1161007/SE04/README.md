**Aluno [1161007](../)** - SE04
=======================================

# 1. Requisitos

**SE04:** Como SE pretendo solicitar uma Comparação de Avaliação de Risco usando duas matrizes de risco distintas para um dado local e respetivo conjunto de coberturas.

Esta funcionalidade está inserida no contexto de um Sistema Externo que pretende ser responsável pelo processamento dos pedidos de análise de risco.

Este caso de uso está dependente do caso de uso anterior, "As RA service I want to provide a comparison of risk assessment using diferent versions of the risk matrix", utilizando a funcionalidade desenvolvida anteriormente e respetivos resultados

**Requisitos Técnicos do UC:**

Os formatos dos ficheiros de saída pelo Sistema Externo serão JSON , XML bem como XHTML.

Sendo um dos formatos de ficheiro de saída um ficheiro XML, um XSD deve ser criado para verificar a validade desse tal ficheiro XML e caso esta validade não se evidencie uma resposta deve ser enviada com uma mensagem sugestiva.

É necessário que todos os pedidos feitos ao Sistema Externo sejam adquiridos através de comunicações HTTP(S). Será usado o cliente de HTTP(S) "Postman" para este efeito, sendo que o envio dos ficheiros é feito através deste.
 

# 2. Análise

**Considerações importantes para o Design:**

* A implementação será organizada através de um interligação modular entre classes no serviço externo.

* Obter dados persistidos após o bootstrap e cálculo de certos dados para então ser possivel o envio de uma resposta com todos os dados necessários e relevantes relativos às matrizes e ao pedido de avaliação de risco.

# 3. Design

## 3.1. Realização da Funcionalidade

**Diagrama de Sequência:**
![Image](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/6a6c2b0c09548c1bf48e33fb884d5ec360278150/docs/1161007/SE04/SD.png)

## 3.2. Diagrama de Classes

![Image](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/f282e7fc0f9b62aaa23dc0a7d2bc371fc447588a/docs/1161007/SE04/CD.png)

## 3.3. Padrões Aplicados

Para a realização do caso de uso foram aplicados diversos padrões:

* Padrão repositório, necessário para a persistência das matrizes a comparar e dos pedidos de avaliação de risco a utilizar.

* Padrão DTO, nas classes ImprimirResultadoDTO e ComparacaoArDTO, que facilitaram na organização dos dados que são utilizados para guradar os resultados exportados e para fazer a comparação de avaliação de risco respetivamente.

## 3.4. Testes

**Teste 1:** Teste geral para os casos de uso do SE, o qual comprova a validação de um ficheiro XML a partir de um ficheiro XSD respetivo:


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

**Organização do UC:**

![image](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/3cfdc0496de77aadfcb0ebe936637016d5b3d69f/docs/1161007/SE04/implementacao.png)

**Ficheiros Relevantes:**

- Para validação do XML foi criado um ficheiro XSD, com nome BuscarComparacao.xsd. que valida se o XML formado está em conformidade com o resultado esperado.

# 5. Integration/Demonstration

* Foi desenvolvida uma implementação geral para o Sistema Externo, de forma a que todos os UCs relacionados com ele, e não apenas este, pudessem usufruir dessas funcionalidades.

# 6. Observações

* Apesar da implementação dos casos de exportação de ficheiro em formato JSON e XML, infelizmente não foi possível alcançar o terceiro nivel de exportação para XHTML.