# **AutomatonSimulator**
## **Descrição :**
Este é um programa que simula autômatos determinísticos e não-determinísticos.

Nele é possível cadastrar um autômato, editar e visualizar seus elementos e verificar se palavras informadas com base em seu alfabeto são aceitas ou recusadas pelo autômato.

## **Instruções de utilização :**

#### 1. Criação do autômato
```
1 - Criar autômato
0 - Finalizar programa
------------------------
Opção escolhida :
```
```
1 - Autômato Finito Determinístico
2 - Autômato Finito Não-Determinístico
0 - Voltar
-------------------------------------------
Opção escolhida : 
```
Nessa parte basta criar o autômato conforme os menus acima.

#### 2. Manipulando o autômato
```
  1 - Verificar palavra
  2 - Inserir símbolo no alfabeto do autômato
  3 - Remover símbolo do alfabeto do autômato
  4 - Mostrar alfabeto do autômato
  5 - Adicionar um estado
  6 - Adicionar uma transição
  7 - Remover um estado
  8 - Remover uma transição
  9 - Checar existência de um estado
10 - Checar existência de uma transição
11 - Colocar um estado como 'estado inicial'
12 - Colocar um estado como 'estado final'
13 - Tirar o tipo 'estado inicial' de um estado
14 - Tirar o tipo 'estado final' de um estado
15 - Mostrar estados do autômato
16 - Mostrar transições do autômato
17 - Mostrar estado inicial do autômato
18 - Mostrar estados finais do autômato
  0 - Destruir autômato
-------------------------------------------------
Opção escolhida : 
```
Após o autômato já estar criado, esse menu irá aparecer e o usuário já poderá utilizar o autômato como quiser, conforme as opções informadas. Entretanto, é recomendado que seja seguida a seguinte ordem :
1. Inserir os símbolos do alfabeto
2. Adicionar os estados
3. Adicionar as transições
4. Utilizar o autômato como desejar
## **Observações :**
- Quase todos os erros ou problemas foram tratados com o intuito de que o usuário não perdesse o autômato criado por conta de algo que levasse a finalização forçada do programa.
- Ao utilizar a opção "1 - Verificar palavra", no menu pós-criação do autômato, caso o autômato cadastrado seja um autômato finito não-determinístico, o programa transforma ele para um autômato finito determinístico.
