**Aluno [1160656](../)** - AR01
=======================================

# 1. Requisitos

*Nesta secção o estudante deve indicar a funcionalidade desenvolvida bem como descrever a sua interpretação sobre a mesma e sua correlação e/ou dependência de/com outros requisitos.*

*Exemplo*

**Demo1** Como {AR} pretendo...

- AR01.1. Usar um mecanismo de autenticação simulado (mock) em que o utilizador e respetiva palavra-passe são iguais e correspondem a um endereço de email.

- AR01.2. Usar um mecanismo de autenticação em que a informação dos utilizadores está convenientemente persistida numa base de dados.


Através da interpretação do enunciado deste requisito, cheguei à conclusão que o seu principal intuito é de fornecer ao AR a possibilidade de se autenticar no sistema, para permitir realizar as suas atividades. Em primeiro lugar será necessário a criação de utilizadores, com validações dos seus atributos. Ou seja, a palavra passe e o nome do utilizador terá de ter regras especificas de criação.
Posteriormente será persistida, de forma conveniente, na base de dados a informação criada.

# 2. Análise

Para a realização deste uc, necessitei de fazer uma pequena revisão nas matérias dadas ao longo do semestre na cadeira de EAPLI, nomeadamente dos padrões de software e na persistência. Para além disso analisei cuidadosamente os ucs realizados no projeto da disciplina de eapli, para conseguir integrar com sucesso a minha funcionalidade.

# 3. Design


## 3.1. Realização da Funcionalidade
Para realizar esta funcionalidade o utilizador terá de seguir os seguintes passos:
1) No menu geral da aplicação deve selecionar a opção número 24.
2) Escrever um email válido para a sua conta.
3) Escrever uma password válida para a sua conta.
4) Escrever um username válido para a sua conta.
5) Registo feito com sucesso.
6) Para dar login com a nova conta criada, selecionar a opção 25 no menu geral.
7) Inserir o email a password da conta.
8) Login efetuado com sucesso.

https://prnt.sc/nwcyvh

## 3.2. Diagrama de Classes

http://prntscr.com/nwn9gj

## 3.3. Padrões Aplicados

*Nesta secção deve apresentar e explicar quais e como foram os padrões de design aplicados e as melhores práticas*

## 3.4. Testes

**Teste 1, Test2, Test3, Test4, Test5:** Verificar se a validação da password, tem em conta todos os critérios possíveis.


 @Test
 public void testValidarPasswordTamanho() {
		 System.out.println("validarPassword");
		 String password = "Gil1";
		 AnalistaRisco instance = new AnalistaRisco();
		 boolean expResult = false;
		 boolean result = instance.validarPassword(password);
		 assertEquals(expResult, result);


 @Test
 public void testValidarPasswordMaiuscula() {
		 System.out.println("validarPassword");
		 String password = "gil1111";
		 AnalistaRisco instance = new AnalistaRisco();
		 boolean expResult = false;
		 boolean result = instance.validarPassword(password);
		 assertEquals(expResult, result);

 }

 @Test
 public void testValidarPasswordMinuscula() {
		 System.out.println("validarPassword");
		 String password = "GIL11111";
		 AnalistaRisco instance = new AnalistaRisco();
		 boolean expResult = false;
		 boolean result = instance.validarPassword(password);
		 assertEquals(expResult, result);

 }

 @Test
 public void testValidarPasswordNumero() {
		 System.out.println("validarPassword");
		 String password = "Gillllll";
		 AnalistaRisco instance = new AnalistaRisco();
		 boolean expResult = false;
		 boolean result = instance.validarPassword(password);
		 assertEquals(expResult, result);

 }

 @Test
 public void testValidarPasswordCorreto() {
		 System.out.println("validarPassword");
		 String password = "Gillllll1";
		 AnalistaRisco instance = new AnalistaRisco();
		 boolean expResult = true;
		 boolean result = instance.validarPassword(password);
		 assertEquals(expResult, result);

 }
 **Test6, Test7:** Verificar se a validação da email, tem em conta todos os critérios possíveis.

	 @Test
	 public void testValidarEmail() {
			 System.out.println("validarEmail");
			 String email = "gil";
			 AnalistaRisco instance = new AnalistaRisco();
			 boolean expResult = false;
			 boolean result = instance.validarEmail(email);
			 assertEquals(expResult, result);

	 }


	 @Test
	 public void testValidarEmailCorreto() {
			 System.out.println("validarEmail");
			 String email = "gil@gmail.com";
			 AnalistaRisco instance = new AnalistaRisco();
			 boolean expResult = true;
			 boolean result = instance.validarEmail(email);
			 assertEquals(expResult, result);

	 }



# 4. Implementação

A implementação seguiu no geral o design proposto no início, no entanto sofreu algumas alterações que já estão de acordo com o ficheiro do design recente. As alterações foram relativamente pequenas, sendo baseadas em alterações de nomes e adição dos métodos necessários à validação dos dados do analista de risco.



# 5. Integration/Demonstration

Esta funcionalidade foi integrada no menu geral da aplicação, sendo assim possível aceder a esta cada vez que se inicia a aplicação. Foi adicionado um menu de analista de risco, que aparece ao utilizador após um login bem sucedido, para este conseguir fazer as funcionalidades do AR02.

# 6. Observações

Considero que tive uma boa prestação na realização deste UC, tendo conseguido apresentar os requisitos propostos no enunciado funcionais. No entanto, penso que posso melhorar a nível de testes, pois o número de testes efetuados não é suficiente para assegurar a ausência total de bugs.
