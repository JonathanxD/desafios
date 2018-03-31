# Primeiro Desafio

**Nota: escrevi todo o desafio em inglês (com exceção a este README), espero que não tenha problema.**

## Como executar

No Linux:

```
./gradlew run
```

No Windows:

```
./gradlew.bat
```

Após isto entre com o tamanho do texto, se é necessário justificar o texto e entre com o texto, quando terminar o texto escreva `^EOF` no final e de enter (o `^EOF` deve estar em uma linha separada).

## Detalhes de implementação

Utilizei uma interface funcional e implementei com a classe `SizedStringFormatter` (para ter uma classe comum a todos formatadores com tamanhos), e também mais duas classes para implementar esta classe abstrata, a `SplitSizeStringFormatter` que formata o texto limitando o tamanho de cada linha e a `JustifyStringFormatter` que formata o texto justificando ele.

A combinação dos dois formatadores pode ser feita por meio do método `and` da interface funcional (quando a criei não tinha esta intenção, mas acabou que foi uma boa forma de compor mais de um formatador sem criar uma quarta classe).

## Testes unitarios

Também está incluso testes unitários utilizando os textos de exemplo fornecidos no repositório e um texto gerado pelo [Lipsum](https://br.lipsum.com/) que também utilizei na documentação.
