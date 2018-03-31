package com.github.jonathanxd.idwall.desafios.strings;

import java.util.function.IntFunction;

/**
 * A formatter which receives a size.
 */
public abstract class SizedStringFormatter implements StringFormatter {

    /**
     * Specified size.
     */
    private final int size;

    SizedStringFormatter(int size) {
        this.size = size;
    }

    /**
     * Gets the specified size.
     *
     * @return Specified size.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns a formatter that applies this formatting and then the formatting of {@code other}
     * constructed with this {@link #getSize() size}.
     *
     * @param other Function that creates other formatter to format the string.
     * @return A formatter that applies both this formatting and {@code other} formatting.
     */
    public StringFormatter and(IntFunction<StringFormatter> other) {
        StringFormatter formatter = other.apply(this.getSize());

        return StringFormatter.super.and(formatter);
    }
}
