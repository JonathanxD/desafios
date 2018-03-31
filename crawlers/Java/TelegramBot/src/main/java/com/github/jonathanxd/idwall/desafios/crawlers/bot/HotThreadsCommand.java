package com.github.jonathanxd.idwall.desafios.crawlers.bot;

import com.github.jonathanxd.idwall.desafios.crawlers.Constants;
import com.github.jonathanxd.idwall.desafios.crawlers.JsoupThreadFetcher;
import com.github.jonathanxd.idwall.desafios.crawlers.ThreadFetcher;
import com.github.jonathanxd.idwall.desafios.crawlers.VoteThreadComparator;

import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HotThreadsCommand extends BotCommand {

    private final ThreadFetcher fetcher = new JsoupThreadFetcher();

    public HotThreadsCommand() {
        super("nadaparafazer", "Mostra os topicos que estão bombando nos sub-reddits informations");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        List<String> subreddits = Arrays.stream(arguments)
                                        .flatMap(s -> Arrays.stream(s.split(";")))
                                        .collect(Collectors.toList());

        for (String subreddit : subreddits) {
            try {
                fetcher.fetch(subreddit)
                       .stream()
                       .filter(thread -> thread.getScore() >= 5000)
                       .sorted(VoteThreadComparator.comparator().reversed())
                       .forEach(thread ->
                               this.send(String.format(
                                       "**[%s](%s)** ([/r/%s](%s))%n" +
                                               "Score: %d%n" +
                                               "[Comentarios](%s)",
                                       thread.getTitle(), thread.getLink(),
                                       thread.getSubreddit(), Constants.BASE_REDDIT_URL + "/r/" + thread.getSubreddit(),
                                       thread.getScore(),
                                       thread.getCommentsLink()
                               ), absSender, chat));
            } catch (IOException e) {
                this.send(String.format("Um erro ocorreu ao tentar obter os threads do subreddit %s%n.Mensagem: %s.",
                        subreddit,
                        e.getMessage()), absSender, chat);

            }
        }
    }

    private void send(String message, AbsSender absSender, Chat chat) {
        try {
            absSender.execute(new SendMessage()
                    .setChatId(chat.getId())
                    .setParseMode(ParseMode.MARKDOWN)
                    .setText(message)
            );
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
