**Aluno [1160656](../)** - SE05
=======================================

# 1. Requisitos



**Demo1** Como SE pretendo submeter de uma só vez um conjunto de Pedidos de Avaliação de Risco que devem ser tratados de forma conjunta.


Através da interpretação do enunciado deste requisito, cheguei à conclusão que o seu principal intuito é submeter(importar) um conjunto de pedidos, através de um ficheiro em formato json ou xml.

# 2. Análise

Para a realização deste uc, necessitei de fazer uma revisão nas matérias lecionadas em lprog e rcomp, pois foram aplicados conteúdos destas cadeiras no uc.
Foi também necessário analisar com atenção os ucs, SE04, SE03, SE02,SE01, (principalmente o SE01), para poder enquadrar o meu código corretamente com o resto do serviço externo.

# 3. Design


## 3.1. Realização da Funcionalidade
Esta funcionalidade está aplicada no serviço externo, logo não tem qualquer classes ui de interação com o utilizador.
O UC processa-se da seguinte forma:
1) O utilizador seleciona a opção "Post" no programa "Post Man", e escreve "submeterMultiplosPedidos";
2) Posteriormente coloca no secção "body" o xml/Json (tem de escolher o formato correto) do conjunto de pedidos que quer submeter.
3) Na classe HTTPRequestMultiplos lê o que foi introduzido pelo utilizador e começa a realização do uc.
4)O método processPostSubmeterMultiplosPedidos() controla o caso de uso todo, sendo que lê o xml/json e persistea informação do pedido através do método importarMultiplosPedidoController() da classe ImportarMultiplosPedidoController.
5) Esse método lê o xml ou json introduzido pelo utilizador através dos métodos correspondentes de leitura da classe InterpretarMultiplosPedidos.
6) O método processPostSubmeterMultiplosPedidos() persiste posteriormente a informação no repositório de pedidos e retorna o status final do uc.
7) O status é recebido pelo método  processPostSubmeterMultiplosPedidos() que por sua vez usa o método fillContentBasedOnStatus() para mostrar ao utilizador a mensagem apropriada para o status do uc.
8) A mensagem é mostrada ao utilizador, indicando o sucesso ( ou não) da operação.

https://prnt.sc/o2npao

## 3.2. Diagrama de Classes

https://prnt.sc/o2npes

## 3.3. Padrões Aplicados

*Nesta secção deve apresentar e explicar quais e como foram os padrões de design aplicados e as melhores práticas*


# 4. Implementação

A implementação seguiu no geral o design proposto no início, sendo que a única alterção foi a criação da classe HTTPRequestMultiplos.



# 5. Integration/Demonstration

Esta funcionalidade foi integrada no serviço externo já implementado nos SG01-SG04, tendo seguido um padrão similar de realização de funcionalidades.
# 6. Observações

Considero que tive uma boa prestação na realização deste UC, tendo conseguido apresentar os requisitos propostos no enunciado funcionais. No entanto, tive uma fraca prestação a nível de tests unitários, já que não fiz, qualquer teste.
