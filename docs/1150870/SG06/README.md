**Aluno Exemplo [1150870](../)** - SG06
=======================================


# 1. Requisitos

*Nesta secção o estudante deve indicar a funcionalidade desenvolvida bem como descrever a sua interpretação sobre a mesma e sua correlação e/ou dependência de/com outros requisitos.*

**SG06.** Como SG pretendo obter a diferença de elevação (em metros) relativamente ao nível médio daágua do mar existente entre duas localizações.

- SG06.1. Esta informação deve ser obtida usando o serviço externo MBS

- SG06.2. Esta informação deve ser obtida usando o serviço externo GMS

- SG06.3. Esta informação deve ser obtida por combinação (i.e. valor médio) dos resultados dos serviços externos anteriores

- SG06.4. O sistema deve usar o método anterior que estiver configurado para o efeito


**Requisitos Técnicos do UC:**

- Establecer uma ligação com os serviços externos da Google Maps Services e Micsoft Bing Services;


# 2. Análise

Para ser capaz de obter os dados pedidos foi preciso utilizar código previamente implementado, nomeadamente API's previamente implementadas que obtivessem os dados necessários. Lido do ficheiro o serviço a usar para cálculo da diferença de elevação( Bing, Google ou a combinação de ambos),dá se então inicio ao dos método correspondente. 

Ambos( Bing/ Google) com comportamentos identicos inicialmente recebem a rua,localidade e endereço das duas localizações que queremos obter a diferença. Utilizando um método previamente feito é obtido as coordenadas de cada uma das localizações através de um JSON retornado de um URL definido, isto pertencente à API geocoding capaz de retornar as coordenadas de um dado local.

Após serem retornadas as coordenadas de cada localização é calculada a elevação de cada localização utilizando essas mesmas coordenadas. Segue o mesmo raciocinio anterior em que a elevação é obtida através de um JSON retornado de um URL definido, pertencente também a uma API Elevation capaz de retornar a elevação através das coordenadas de um local.

Tendo então as elevações de ambas as localizações resta apenas realizar o cálculo da diferença. 

Para o caso em que existe a combinação de ambos segue o raciocinio anterior, mas faz o calculo de ambos ( Bing e Google) somando os resultados obtidos e divindo por 2( média). 

Tal como em cima escrito a escolha do serviço é feita através da leitura de um ficheiro de configuração que possui o serviço a utilizar. Depois, uma funcionalidade criada lê a informação do ficheiro de configuração,
e consoante o que este possui ele processa o método desejado.


# 3. Design

## 3.1. Realização da Funcionalidade

![picture](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/bc1bd8dc8bb50c8169bd844f4bc89adcc75c8127/docs/1150870/SG06/sd_2.png)
![picture](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/bc1bd8dc8bb50c8169bd844f4bc89adcc75c8127/docs/1150870/SG06/sd_1.png)

## 3.2. Diagrama de Classes

![picture](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/bc1bd8dc8bb50c8169bd844f4bc89adcc75c8127/docs/1150870/SG06/cd_1.png)

## 3.3. Padrões Aplicados

## 3.4. Testes 

**Teste1: Obtenção da diferença de elevação utilizando o método lido pelo ficheiro** 

![picture](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/bc1bd8dc8bb50c8169bd844f4bc89adcc75c8127/docs/1150870/SG06/test_3.png)


**Teste2: Diferença de elevação (Google)** 

![picture](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/bc1bd8dc8bb50c8169bd844f4bc89adcc75c8127/docs/1150870/SG06/test_4.png)

**Teste3: Diferença de elevação (Bing)**

![picture](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/bc1bd8dc8bb50c8169bd844f4bc89adcc75c8127/docs/1150870/SG06/test_2.png)


**Teste4:Valor médio da diferença de elevações ( Ambos Google e Bing)** 

![picture](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/bc1bd8dc8bb50c8169bd844f4bc89adcc75c8127/docs/1150870/SG06/test_1.png)


# 4. Implementação

**Organização do UC SG06**

![picture](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/bc1bd8dc8bb50c8169bd844f4bc89adcc75c8127/docs/1150870/SG06/general.png)

**Ficheiros Relevantes**

- Para escolha de qual serviço utilizar ( Bing,Google ou ambos) um ficheiro à parte denominado "configsGeoRef.txt" indica então qual o serviço. A não existencia de uma UI, ou seja, a não existência de uma interação com o utilizador levou-nos a optar então por estar maneira de escolha de serviço.


# 5. Integration/Demonstration

- A funcionalidade desenvolvida foi inserida dentro do sub projeto relativo ao sistema de geo-referenciação
- Este UC foi desenvolvido de forma a utilizar implementações previamente feitas nomeadamente da obtenção de coordenadas através de uma rua,localidade e endereço bem como a implemtação da obtenção da elevação através dessas coordenadas obtidas;
- Há também ligação com a classe HTTPClient, onde se encontram implementados os métodos que preparam os URL para mais tarde se fazer pedidos/ligação às API's.

# 6. Observações

Considero que os objetivos principais( de SG06.1 - SG06.4 ) do desenvolvimento desta funcionalidade foram obtidos com sucesso.



