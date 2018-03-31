package com.github.jonathanxd.idwall.desafios.crawlers.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Main {

    public static void main(String[] args) {
        ApiContextInitializer.init();

        TelegramBotsApi api = new TelegramBotsApi();

        try {
            api.registerBot(new RedditHotThreadsBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

}
