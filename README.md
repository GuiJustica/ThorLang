# 🐾 ThorLang Compiler Project

Este projeto implementa um compilador básico para a linguagem de programação educacional **ThorLang** (uma linguagem inspirada no meu cachorro Thor!), utilizando Java. O compilador é composto pelas fases de Análise Léxica, Análise Sintática (Parser Descendente Recursivo) e a construção de uma Árvore Sintática Abstrata (AST), além de uma tradução simples para o código-alvo (aparentemente Java/pseudo-código).

## 🏷️ Linguagem ThorLang: Vocabulário

A linguagem utiliza uma sintaxe e um vocabulário temáticos, conforme definido nos Autômatos Finitos Determinísticos (AFDs) e na gramática:

| Palavra-Chave (Lexema) | Significado | Tipo (Token) | Equivalente (Comum) |
| :--- | :--- | :--- | :--- |
| `Thor` | Início da Classe/Programa | `Thor` | `public class Main {` |
| `Gordoo` | Início da Função Principal | `Gordao` | `public static void main(...) {` |
| `inteiro` | Declaração de Inteiro | `inteiro` | `int` |
| `flutua` | Declaração de Ponto Flutuante | `float` | `float` |
| `Texto` | Declaração de String | `string` | `String` |
| `au` | Condicional IF | `if` | `if` |
| `auuuu` | Loop WHILE | `while` | `while` |
| `auau` | Loop FOR | `for` | `for` |
| `latindo` | Comando de Impressão | `latindo` | `System.out.println()` |
| `petisco` | Fim do Bloco de Código | `petisco` | `}` |
| `carnecarne` | Incremento | `increse` | `++` |
| `limaolimao` | Decremento | `deincrese` | `--` |
| `some`, `subtraia`, `multi`, `div` | Operadores Aritméticos | `Mais`, `Menos`, `Multi`, `Div` | `+`, `-`, `*`, `/` |
| `leia` | Comando de Leitura (Input) | `leia` | `Scanner` / Leitura |

## 📐 Estrutura do Compilador

O projeto é modularizado em Java, seguindo as fases tradicionais de um compilador.

### 1. Análise Léxica (Lexer)

O Lexer é responsável por ler o código-fonte (`arquivo.txt`) e gerar uma sequência de `Token`s.

* **`AFD.java`:** Classe abstrata base para todos os Autômatos Finitos Determinísticos (AFDs).
* **`Token.java`:** Estrutura de dados que armazena o `tipo` (ex: `id`, `if`, `numero`) e o `lexema` (o valor literal do token).
* **`Lexer.java`:** Gerencia a leitura do `CharacterIterator` e itera sobre a lista de AFDs (`afds`) para reconhecer o próximo token.
* **AFDs Implementados:**
    * `AtribPlus.java`: Operadores de atribuição (`=`) e relacionais (`==`, `>`, `<`, `>=`, `<=`) e símbolos de pontuação/estrutura (`(`, `)`, `{`, `}`, `[`, `]`, `;`, `\n`).
    * `MathOperator.java`: Operadores matemáticos em forma de palavra (`some`, `subtraia`, `multi`, `div`).
    * `PalavraReservada.java`: Reconhece todas as palavras-chave da linguagem (ex: `au`, `auuuu`, `inteiro`).
    * `Numero.java`: Reconhece sequências de dígitos.
    * `Palavra.java`: Reconhece identificadores (variáveis).

### 2. Análise Sintática (Parser)

O Parser verifica se a sequência de tokens está em conformidade com a gramática da ThorLanf.

* **`Parser.java`:** Implementa um **Parser Descendente Recursivo**. Cada método (`prog()`, `declara()`, `bloco()`, `cmd()`, etc.) corresponde a uma regra da gramática.
* **Tradução (Geração de Código):** O método `traduz(String code)` é chamado dentro dos métodos `matchL`/`matchT` para gerar uma tradução simples em tempo real para o código-alvo (aparentemente, uma sintaxe semelhante a Java/C).

### 3. Árvore Sintática Abstrata (AST)

Embora o parser não construa a AST de forma completa no código fornecido (o *parser* está focado principalmente na validação sintática e tradução), as classes de AST estão presentes:

* **`Node.java`:** Representa um nó na árvore com nome (`nome`), filhos (`nodes`) e campos opcionais `enter` e `exit` (provavelmente usados para geração de código ou visualização).
* **`Tree.java`:** Classe que gerencia a árvore, oferecendo métodos para travessia (`preOrder`) e impressão.
* **`maintree.java`:** Contém um exemplo de como instanciar e manipular a estrutura da AST.

## 🛠️ Como Executar o Compilador

1.  **Pré-requisitos:** Certifique-se de ter o Java Development Kit (JDK) instalado.
2.  **Estrutura:** Mantenha todos os arquivos `.java` no mesmo diretório.
3.  **Código-Fonte:** Crie o arquivo de entrada `arquivo.txt` com o código ThorLanf que você deseja compilar (como o exemplo fornecido).

    ```thorlanf
    Thor
    Gordoo
    inteiro x = 1 ;
    au x > 0
      p = 3 ;
      k = 2 ;
      p = 3 ;
    petisco
    auuuu x > 0
        leia
        str i = oi ;
        x carnecarne ;
    petisco
    auau x = 1 ; x < 10 ; x carnecarne
        latindo ( oi );
    petisco
    ```

4.  **Compilar e Rodar:**
    ```bash
    # Compila todos os arquivos Java
    javac *.java
    
    # Executa a classe principal
    java Main
    ```

O programa irá:
1. Ler o conteúdo de `arquivo.txt`.
2. Gerar e exibir a lista de tokens.
3. Iniciar o `Parser`, que tentará validar a sintaxe e imprimir a tradução do código em tempo real.
4. Se o código for válido, a mensagem "Sintaticamente correta" será exibida.
