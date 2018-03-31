package com.github.jonathanxd.idwall.desafios.strings;

@FunctionalInterface
public interface StringFormatter {

    /**
     * Formats {@code text} based on rules of the implementation of the formatter.
     *
     * @param text Text to format.
     * @return Formatted text.
     */
    String format(String text);

    /**
     * Returns a formatter that applies both this formatting and {@code other} formatting.
     *
     * @param other Other formatter to apply after this.
     * @return Formatter that applies both this formatting and {@code other} formatting.
     */
    default StringFormatter and(StringFormatter other) {
        return s -> other.format(this.format(s));
    }
}
