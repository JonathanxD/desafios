package com.github.jonathanxd.idwall.desafios.crawlers;

import java.io.IOException;
import java.util.List;

/**
 * Thread fetcher interface.
 */
public interface ThreadFetcher {

    /**
     * Fetches all threads of {@code subreddit} (which is visible in main page).
     *
     * @param subreddit Subreddit to fetch threads.
     * @return All threads of main page of {@code subreddit}.
     * @throws IOException If fetcher fails to connect to reddit.
     */
    List<Thread> fetch(String subreddit) throws IOException;

}
