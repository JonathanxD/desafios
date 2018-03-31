package com.github.jonathanxd.idwall.desafios.crawlers;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ThreadFetcher fetcher = new JsoupThreadFetcher();

        Scanner scanner = new Scanner(System.in);

        String subRedditString = scanner.nextLine();
        String[] subReddits = subRedditString.split(";");

        for (String subReddit : subReddits) {
            try {
                fetcher.fetch(subReddit).stream()
                       .filter(thread -> thread.getScore() >= 5000)
                       .sorted(VoteThreadComparator.comparator().reversed())
                       .forEach(Main::printThread);
            } catch (IOException e) {
                throw new RuntimeException(String.format("Failed to fetch subreddit %s.", subReddit), e);
            }
        }

    }

    private static void printThread(Thread thread) {
        int size = (int) Math.log10(thread.getScore()) + 1;
        System.out.printf("%-" + size + "s   Title:    %s %s%n", "↑", thread.getTitle(), "(/r/" + thread.getSubreddit() + ")");
        System.out.printf("%-" + size + "s   Link:     %s%n", thread.getScore(), thread.getLink());
        System.out.printf("%-" + size + "s   Comments: %s%n", "↓", thread.getCommentsLink());
        System.out.println();
    }

}
