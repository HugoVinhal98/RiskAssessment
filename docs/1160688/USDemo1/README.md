**Aluno [1160688](../)** - AR02
=======================================

# 1. Requisitos

**AR02** Como AR pretendo...

- AR02.1. Os pedidos devem ser apresentados sempre ordenados dos mais antigos para os mais recentes.

- AR02.2. Permitir (opcionalmente) que os pedidos sejam filtrados para um dado distrito (e.g. Porto).

- AR02.3. Permitir que o AR requeira para si a análise de um dos Pedidos de Avaliação de Risco pendentes. Como resultado, o Pedido de Avaliação de Risco fica atribuído ao respetivo AR e a data da atribuição deve ser registada.

Através da interpretação do enunciado deste requisito, cheguei à conclusão que o seu principal intuito é de fornecer ao AR a possibilidade de poder consultar os pedidos de avaliação de risco pendentes de validação e que não estejam atribuídos. Os pedidos vão ter de estar ordenados do mais antigo para o mais recente. Vai ter de haver uma funcionalidade que permita que os pedidos sejam filtrados.

# 2. Análise

Para a realização das funcionalidades que foram propostas foi preciso de fazer uma revisão mais aprofundada no trabalho de EAPLI, para poder relacionar as novas funcionalidades com as que já existiam.

# 3. Design

## 3.1. Realização da Funcionalidade

http://prntscr.com/nwnjp7

## 3.2. Diagrama de Classes

http://prntscr.com/nwnjc8

## 3.3. Padrões Aplicados

*Nesta secção deve apresentar e explicar quais e como foram os padrões de design aplicados e as melhores práticas*

## 3.4. Testes
*Nesta secção deve sistematizar como os testes foram concebidos para permitir uma correta aferição da satisfação dos requisitos.*

**Teste 1:** Verificar que não é possível criar uma instância da classe Exemplo com valores nulos.

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Exemplo instance = new Exemplo(null, null);
	}

# 4. Implementação

Ao longo da implementação foram realizadas algumas mudanças no design, mas seguindo sempre a mesma lógica de pensamento previamente definido. Neste momento o design encontra-se de acordo com a implementação.

# 5. Integration/Demonstration

Estas funcionalidades foram integradas num menu específico para o Analista de Risco, podendo apenas serem utilizadas quando o analista efetuar um registo e um login no sistema. Quando o faz aparecem as funcionalidades á disposição para o Analista quais quer fazer.

# 6. Observações

No fim da primeira iteração considero que a minha performance , de forma geral, foi mediana uma vez que realizei todas as funcionalidades propostas do ponto 2 , exceto a terceira funcionalidade e a realização de testes para essas mesmas funcionalidades. A nível organizacional ainda tenho que melhorar bastante , já que houve dias em que podia ter aproveitado melhor o tempo e consequentemente uma melhor performance a nível do desenvolvimento das funcionalidades, especialmente da terceira funcionalidade, que como já foi dito , não está implementada.
