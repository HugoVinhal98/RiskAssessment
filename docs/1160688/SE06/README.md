**Aluno [1160688](../)** - SE06
=======================================

# 1. Requisitos



**Demo1** Como  SE  pretendo  conhecer  a disponibilidade atual(sim/não  e carga atual) do  Serviço  de Avaliação de Risco para receber/processar novos pedido


Após a leitura do enunciado , o propósito do  UC é verificar a disponibilidade do sistema e a sua carga atual.

# 2. Análise

De modo a fazer o uc , foi preciso de fazer uma pesquisa intensa sobre os conceitos de Threads e concorrência
,estes que pertencem à disciplina de SCOMP. Também tive de ver novamente alguns conceitos de HTTP lecionados na disciplina de RCOMP. Da mesma maneira, precisei de fazer uma pequena revisão da matéria de LPROG. Tive , também, de implementar os meus ucs de acordo com o código previamente feito no sistema externo.

# 3. Design


## 3.1. Realização da Funcionalidade
Esta funcionalidade está aplicada no serviço externo, logo não tem qualquer classes ui de interação com o utilizador.
O UC processa-se da seguinte forma:
1) O utilizador seleciona a opção "Post" no programa "Post Man", e escreve “Disponibilidade";
2) Posteriormente coloca no secção "body” a seguinte frase: “Diga-me a disponibilidade” em formato plain/text.
3) Na classe HTTPRequestDisponibilidade lê o que foi introduzido pelo utilizador e começa a realização do uc.
4)O método processPostDisponibilidade() controla o caso de uso todo, sendo que lê o texto introduzido e verifica a disponibilidade e o número de threads em execução através do método processPostDisponbilidadeController().
5) Esse método verifica a quantidade de threads que estão no ativo e compara com o número de threads máximo. Posteriormente devolve a disponibilidade e o número de threads ativo (status).
6) O status é recebido pelo método  processPostDisponibilidade() que por sua vez usa o método fillContentBasedOnStatus() para mostrar ao utilizador a mensagem apropriada para o status do uc.
7) A mensagem é mostrada ao utilizador, indicando o sucesso ( ou não) da operação.

http://prntscr.com/o2n1fl

## 3.2. Diagrama de Classes

http://prntscr.com/o2n13y

## 3.3. Padrões Aplicados

*Nesta secção deve apresentar e explicar quais e como foram os padrões de design aplicados e as melhores práticas*


# 4. Implementação

A implementação seguiu no geral o design proposto no início, sendo que a única alterção foi a criação da classe HTTPRequestMultiplos.



# 5. Integration/Demonstration

Esta funcionalidade foi integrada de acordo com o que já estava implementado no serviço externo previamente feito pelos colegas responsáveis pelos ucs SE01, SE02, SE03, SE04.
# 6. Observações

Considero que tive uma boa prestação na realização deste UC, tendo conseguido apresentar os requisitos propostos no enunciado funcionais. Foi um caso de uso que provocou algumas dificuldades.
