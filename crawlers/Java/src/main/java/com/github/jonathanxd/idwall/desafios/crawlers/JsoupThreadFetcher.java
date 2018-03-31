package com.github.jonathanxd.idwall.desafios.crawlers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.github.jonathanxd.idwall.desafios.crawlers.Constants.*;

/**
 * Thread fetcher implemented using {@link Jsoup}.
 */
public class JsoupThreadFetcher implements ThreadFetcher {

    @Override
    public List<Thread> fetch(String subreddit) throws IOException {
        List<Thread> threads = new ArrayList<>();

        // Connects to sub-reddit and fetches the documment
        Document document = Jsoup.connect(BASE_REDDIT_URL + "/r/" + subreddit).get();

        // Select all divs that have thing class
        // those divs contains reddit threads.
        // Also 'getElementsByClass("thing")' works too
        Elements thing = document.body().select("div.thing");

        // Loop every thread
        for (Element element : thing) {

            // Find title elements
            Elements titleElements = element.getElementsByAttributeValue("data-event-action", "title");

            // Find thread score elements
            Elements scoreElements = element.getElementsByClass("score unvoted");

            // Checks whether both title and thread score is present
            if (!titleElements.isEmpty() && !scoreElements.isEmpty()) {
                // Gets the title element
                Element titleElement = titleElements.get(0);

                // Gets the score element
                Element threadScore = scoreElements.get(0);

                // Gets the subreddit key from thread div
                // Note: This returns the same value as provided to subreddit parameter
                String elementSubreddit = element.attr("data-subreddit");

                // Gets the title of thread
                String title = titleElement.text();

                // Gets the link of thread (may be an external link)
                String link = titleElement.attr("href");

                // Gets the permalink (this is the link to comments of thread)
                // this link is relative
                String dataPermalink = element.attr("data-permalink");

                // Checks whether the link of thread is external or not
                // Internal links is prefixed with '/' and external starts with 'http*'
                String redditLink = link.startsWith("/") ? BASE_REDDIT_URL + link : link;

                // Creates an absolute link from the relative 'dataPermalink'
                String commentsLink = BASE_REDDIT_URL + dataPermalink;

                // Thread score
                int score;

                // Tries to parse number of score
                // if the vote texts is not a valid integer, the post will be ignored
                try {
                    // Here we use title tag to get score
                    // because the text may not be a valid integer, like 11.2K.
                    score = Integer.parseInt(threadScore.attr("title"));
                } catch (NumberFormatException ignored) {
                    continue;
                }

                // Creates and add thread to list
                threads.add(Thread.create(elementSubreddit, title, score, redditLink, commentsLink));
            }
        }

        return threads;
    }
}
