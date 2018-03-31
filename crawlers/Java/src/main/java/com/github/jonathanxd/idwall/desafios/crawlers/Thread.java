package com.github.jonathanxd.idwall.desafios.crawlers;

import java.util.Objects;

/**
 * Data class to store thread data.
 */
public final class Thread {
    /**
     * Sub-reddit of the thread
     */
    private final String subreddit;

    /**
     * Title of the thread
     */
    private final String title;

    /**
     * Score of the thread
     */
    private final int score;

    /**
     * Link to thread (external or internal)
     */
    private final String link;

    /**
     * Link to comments of thread
     */
    private final String commentsLink;

    private Thread(String subreddit, String title, int score, String link,
                   String commentsLink) {
        this.subreddit = subreddit;
        this.title = title;
        this.score = score;
        this.link = link;
        this.commentsLink = commentsLink;
    }

    /**
     * Creates a {@link Thread} instance.
     *
     * @param subreddit    Sub-reddit of the thread
     * @param title        Title of the thread
     * @param score        Score of the thread.
     * @param link         Link to thread.
     * @param commentsLink Link to thread comments.
     */
    public static Thread create(String subreddit, String title, int score, String link, String commentsLink) {
        return new Thread(subreddit, title, score, link, commentsLink);
    }

    /**
     * Gets the sub-reddit of the thread.
     *
     * @return Sub-reddit of the thread.
     */
    public String getSubreddit() {
        return this.subreddit;
    }

    /**
     * Gets the title of the thread.
     *
     * @return Title of the thread.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets the score of the thread.
     *
     * @return Score of the thread.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Gets the link to thread (may be external link).
     *
     * @return Link to the thread.
     */
    public String getLink() {
        return this.link;
    }

    /**
     * Gets the comments link.
     *
     * @return Comments link.
     */
    public String getCommentsLink() {
        return this.commentsLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thread thread = (Thread) o;
        return this.getScore() == thread.getScore() &&
                Objects.equals(this.getSubreddit(), thread.getSubreddit()) &&
                Objects.equals(this.getTitle(), thread.getTitle()) &&
                Objects.equals(this.getLink(), thread.getLink()) &&
                Objects.equals(this.getCommentsLink(), thread.getCommentsLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getSubreddit(), this.getTitle(), this.getScore(), this.getLink(), this.getCommentsLink());
    }

    @Override
    public String toString() {
        return "Thread{" +
                "subreddit='" + this.getSubreddit() + '\'' +
                ", title='" + this.getTitle() + '\'' +
                ", score=" + this.getScore() +
                ", link='" + this.getLink() + '\'' +
                ", commentsLink='" + this.getCommentsLink() + '\'' +
                '}';
    }
}
