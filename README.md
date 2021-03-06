**Descrição** :

Somos uma startup do ramo de alimentos e precisamos de uma aplicação web para gerir nosso negócio. Nossa especialidade é a venda de lanches, de modo que alguns lanches são opções de cardápio e outros podem conter ingredientes personalizados.

A seguir, apresentamos a lista de ingredientes disponíveis:

| **INGREDIENTE** | **VALOR** |
| --- | --- |
| Alface | R$ 0.40 |
| Bacon | R$ 2,00 |
| Hambúrguer de carne | R$ 3,00 |
| Ovo | R$ 0,80 |
| Queijo | R$ 1,50 |

Segue as opções de cardápio e seus respectivos ingredientes:

| **LANCHE** | **INGREDIENTES** |
| --- | --- |
| X-Bacon | Bacon, hambúrguer de carne e queijo |
| X-Burger | Hambúrguer de carne e queijo |
| X-Egg | Ovo, hambúrguer de carne e queijo |
| X-Egg Bacon | Ovo, bacon, hambúrguer de carne e queijo |

O valor de cada opção do cardápio é dado pela soma dos ingredientes que compõe o lanche. Além destas opções, o cliente pode personalizar seu lanche e escolher os ingredientes que desejar. Nesse caso, o preço do lanche também será calculado pela soma dos ingredientes.

Existe uma exceção à regra para o cálculo de preço, quando o lanche pertencer à uma promoção. A seguir, apresentamos a lista de promoções e suas respectivas regras de negócio:

| **PROMOÇÃO** | **REGRA DE NEGÓCIO** |
| --- | --- |
| Light | Se o lanche tem alface e não tem bacon, ganha 10% de desconto. |
| Muita carne | A cada 3 porções de carne o cliente só paga 2. Se o lanche tiver 6 porções, ocliente pagará 4. Assim por diante... |
| Muito queijo | A cada 3 porções de queijo o cliente só paga 2. Se o lanche tiver 6 porções, ocliente pagará 4. Assim por diante... |
| Inflação | Os valores dos ingredientes são alterados com frequência e não gastaríamos que isso influenciasse nos testes automatizados. |

**CRITÉRIOS DE COMPLETUDE**

O projeto deve ser entregue atendendo aos seguintes critérios

- O server-side deve ser desenvolvido em Java, utilizando Maven para gerenciar as dependências.
- O client-side deve ser desenvolvido em HTML, CSS e JavaScript (apenas com jQuery, ou com algum framework se desejar)
- Deve possuir cobertura de testes automatizados para os seguintes pontos: Valor dos lanches de cardápio, regra para cálculo de preço e promoções.
- Não é necessário se preocupar com a autenticação dos usuários.
- Não é necessário persistir os dados em um banco, pode fazer armazenamento em memória.

**PERGUNTAS SOBRE REQUISITOS**

- É necessario criar um mecanismo para cadastro de regras?
- É necessario criar um cadastro para lanches?
- É necessario criar um pedido para que eu possa incluir mais que um lanche?
- Duas ou mais regras podem ser aplicadas para o mesmo lanche?
- Podemos ter outros ingredientes?

**Instruções para executar**

**server-side**

- Caso você esteja rodando pelo IDE (Intellij por exemplo), depois de baixar as dependencias, execute a classe "KrustyKrabApplication".

- Caso queira executar pelo terminal: 
  - Ir até o pom.xml do modulo api, e descomente a dependencia "spring-boot-starter-tomcat"
  - no modulo root ou api, rodar a instrução do maven "mvn package" 
  - Ir até o diretorio target do modulo api e rodar "java -jar api-0.0.1-SNAPSHOT.jar"


**client-side**

Depois que o server_side estiver rodando, abrir o arquivo "KrustyKrab_CLIENT.html"
