**Aluno Exemplo [1150870](../)** - AR04
=======================================


# 1. Requisitos

*Nesta secção o estudante deve indicar a funcionalidade desenvolvida bem como descrever a sua interpretação sobre a mesma e sua correlação e/ou dependência de/com outros requisitos.*

**AR04.** Como AR pretendo analisar um Pedido de Avaliação de Risco pendente de validação que esteja a mim atribuído.

- AR04.1. Os detalhes do Pedido de Avaliação de Risco devem ser apresentados.

- AR04.2. Permitir exportar a informação do Pedido de Avaliação de Risco em causa para um documento XHTML permitindo aquando da visualização do documento colapsar (esconder/mostrar) partes da informação (e.g. as justificações).

- AR04.4. Permitir que o AR confirme o(s) resultado(s) do Pedido de Avaliação de Risco, podendo este acrescentar algum comentário/observação (opcional). O pedido deve ser dado como concluído.

- AR04.5. Permitir que o AR atribua diretamente resultado(s) ao Pedido de Avaliação de Risco. Neste caso, é obrigatório introduzir uma fundamentação. O pedido deve ser dado como concluído.

- AR04.6. Permitir que o AR solicite uma reavaliação automática do Pedido de Avaliação de Risco. Neste caso, é necessário que o AR altere ou remova alguma da informação que suporta o(s) resultado(s) atual e/ou introduza novos dados (e.g. a existência de um quartel de bombeiros) que devem ser tidos em consideração. Após reavaliação automática ter ocorrido, o Pedido de Avaliação de Risco deve voltar a ser analisado pelo mesmo AR..

**Requisitos Técnicos do UC:**

- Após a exportação dos dados para um XHTML, aquando da visualização dos dados do ficheiro é premitido o colapso de partes de informação, neste caso é permitido o colapso da Lista de Coberturas do Objeto Seguro associado ao pedido bem como todas as justificações e datas dessas mesmas justificações. 
- O ficheiro exportado XHTML segue um template com nome "templatePedido.html" .
- Independentemente da opção que o Analista de Risco escolha para fazer, no fim tem sempre de escrever uma justificação do porquê das suas decisões que mais tarde, como antes referido será mostrado no XHTML.
- Relativo a cada justificação, uma data de escrita dessa justificação será sempre associada à justificação.



# 2. Análise


Este UC está ligado e dependente do UC AR03 - "Como AR pretendo consultar os meus (i.e. a mim atribuídos) Pedidos de Avaliação de Risco pendentes de validação." visto que o pedido que é analisado é recebido após o término do UC AR03, é o UC AR03 quem despoleta este UC.

Após terminado o UC AR03 e despoletado este UC, o analista de risco terá em sua mão um pedido e terá então um menu com todas as opções possiveis a fazer com esse pedido:

- Exportar informação para um XHTML
- Confirmar os resultados acrescentando um comentário e dando o pedido como concluido
- Atribuir diretamente os resultados, acrescentando um comentário e dando o pedido como concluído
- Solicitar uma reavaliação automática do pedido e após a reavaliação haver confirmação dos dados para assim dar como concluido o pedido

Um promenor importante a referir, o Analista de Risco escolhendo a opção de reavaliação de um pedido deveria ser capaz de alterar diversos dados por exemplo uma lista de envolventes nova, uma matriz de risco etc.. o que apenas acontece é que o Analista de Risco tem acesso á lista total de coberturas, à lista de coberturas do dado pedido e tem então a opção de manter a lista de coberturas do pedido e então não fazer reavaliação nenhuma ou então criar uma lista de coberturas nova podendo asism então ser possivel uma reavaliação que no fim espera a confirmação para o pedido poder passar a concluído.

Antes da escolha de qualquer opção do menu é mostrado ao Analista de Risco todas os dados relativo a um pedido para este então saber se deseja confirmar os resultados isto é, concordar com os dados mostrados relativos ao pedido e dar então como concluido o pedido e sem mais necessidade de avaliação por um analista de risco.

Se desejar atribuir diretamente os resultados o que isto faz é dar a opção ao do Analista de Risco introduzir por si mesmo os dados ( consciente de que estes mesmos dados estejam corretos ) para então uma nova avaliação de risco seja feita e o pedido seja dado como concluido e sem mais necessidade de uma avaliação por um analista de risco.

Por ultimo se desejar uma reavaliação automática, como em cima descrito ,o analista de risco apresenta uma nova lista de coberturas que fará o cálculo total para uma nova avaliação de risco mas no fim espera , ao contrário com as opções anteriores , não dando o pedido como concluido pois este fica de novo em espera para ser de novo confirmado e dado então aí como concluido e sem necessidade de uma avaliação por um analista de risco.


# 3. Design

## 3.1. Realização da Funcionalidade

![picture](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/09225b50ae4efd23ef81672b9d73150188c2632f/docs/1150870/AR04/AR04_SD_1.png)
![picture](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/09225b50ae4efd23ef81672b9d73150188c2632f/docs/1150870/AR04/AR04_SD_2.png)


## 3.2. Diagrama de Classes

![picture](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/09225b50ae4efd23ef81672b9d73150188c2632f/docs/1150870/AR04/AR04_CD.png)

## 3.3. Padrões Aplicados

Por necessidade de aquando da impressão de todos os dados relativo a um pedido ser preciso a devolução de mais que um tipo de dado a utilização de um DTO é feita, PedidoBuscarDTO, este localizado num próprio package chamado DTO que contém todos os DTOs utilizados.

Foi também utizado o padrão Repositório para aceder aos diferentes dados presistidos relativos às diferentes classes utilizadas, nomeadamente Pedido,AvaliacaoRisco e ObjetoSeguro.

## 3.4. Testes 

Toda a implementação nova foi feita a nível de Controller e UI, nenhuma implementação nova a nível de domínio para que pudessem existir testes a serem feitos.

# 4. Implementação

**Organização do UC AR04**

[!image](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/09225b50ae4efd23ef81672b9d73150188c2632f/docs/1150870/AR04/OrganizacaoAR04.png)

**DTO usado para organização da informação a apresentar no ecrã:**

[!image](https://bitbucket.org/1150812/lapr4-2018-2019-grupo-di-3/raw/09225b50ae4efd23ef81672b9d73150188c2632f/docs/1150870/AR04/DTO_AR04.png)


# 5. Integration/Demonstration

- Este UC foi desenvolvido de forma a só poder ser executado quando corretamente logado como analista de risco.

- Este UC é despoletado pelo UC AR03 , recebendo um pedido previamente validado durante a execução do UC AR03.

# 6. Observações

Aquando da realização do AR04.6  "Permitir que o AR solicite uma reavaliação automática do Pedido de Avaliação de Risco." o Analista de Risco deveria conseguir alterar qualquer dado que desejasse mas apenas consegue alterar a lista de coberturas do objeto seguro no pedido, está feito desta forma devido a limitações do projeto já vindo de EAPLI pois teria de se alterar o projeto em bastantes sitios e poderia criar conflitos com o projeto atual. De qualquer das formas é algo que percebo que não está feito 100% correto e da maneira como deveria de ser.

Considero que os objetivos principais( de AR04.1 - AR04.6 ) do desenvolvimento desta funcionalidade foram obtidos com sucesso à exceção do promenor acima apresentado. 



