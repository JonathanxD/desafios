# Segundo desafio

## Como executar

No Linux:

```
./gradlew run
```

No Windows:

```
./gradlew.bat run
```

Entre com os subreddits (separando-os com `;`) e os threads serão exibidos na CLI formatados.

### Como executar o bot

Primeiramente é necessário informar o token de um bot, pode tanto modificar o código do bot (mais especificamente a classe `RedditHotThreadsBot`), ou definir a propriedade `bot_token`, como foi demonstrado abaixo:

No Linux:

```
./gradlew :TelegramBot:run -Dbot_token=...
```

No Windows:

```
./gradlew.bat :TelegramBot:run -Dbot_token=...
```

E então execute o comando: `/nadaparafazer cats;worldnews` e os threads serão enviados como mensagens (formatadas em Markdown).

## Implementação

Foi utilizado o [Jsoup](https://jsoup.org/) (Framework no qual já tenho conhecimento e gosto bastante por se assimilar ao JQuery) para fazer análise dos sub-reddits.
