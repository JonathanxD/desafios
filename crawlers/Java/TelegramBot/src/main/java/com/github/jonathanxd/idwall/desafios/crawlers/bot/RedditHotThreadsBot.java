package com.github.jonathanxd.idwall.desafios.crawlers.bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class RedditHotThreadsBot extends TelegramLongPollingCommandBot {

    public RedditHotThreadsBot() {
        super("getthreads");

        register(new HotThreadsCommand());
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage()) {

            if (update.getMessage().hasText()) {
                SendMessage message = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Use /nadaparafazer [subreddit(;subreddit)*] para buscar os topicos quentes dos subreddits informados");

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotToken() {
        return System.getProperty("bot_token", "<bot_token>");
    }
}
