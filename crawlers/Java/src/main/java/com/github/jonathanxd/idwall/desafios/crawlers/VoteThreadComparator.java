package com.github.jonathanxd.idwall.desafios.crawlers;

import java.util.Comparator;

/**
 * Compares {@link Thread threads} by {@link Thread#getScore() score}.
 */
public final class VoteThreadComparator implements Comparator<Thread> {
    private static final VoteThreadComparator INSTANCE = new VoteThreadComparator();

    private VoteThreadComparator() {
    }

    /**
     * Gets the instance of comparator.
     *
     * @return Instance of comparator.
     */
    public static VoteThreadComparator comparator() {
        return VoteThreadComparator.INSTANCE;
    }

    @Override
    public int compare(Thread o1, Thread o2) {
        return Integer.compare(o1.getScore(), o2.getScore());
    }
}
