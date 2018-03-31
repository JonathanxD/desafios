package com.github.jonathanxd.idwall.desafios.strings;

/**
 * Formatter which appends a line break {@code \n} when text in the current line reaches specified
 * {@link #size size}.
 *
 * Example, the following text:
 *
 * <pre>
 * {@code
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus neque dolor, bibendum ac rhoncus sit amet, porta vitae neque. Nulla finibus convallis elementum. Suspendisse semper posuere rutrum. Donec interdum ex a ex ornare, eget feugiat urna volutpat. Fusce vitae pharetra tortor, ac tincidunt erat. Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque feugiat diam quis nibh iaculis tincidunt.
 * }
 * </pre>
 *
 * Will be formatted to:
 *
 * <pre>
 * {@code
 * Lorem ipsum dolor sit amet, consectetur
 * adipiscing elit. Vivamus neque dolor,
 * bibendum ac rhoncus sit amet, porta
 * vitae neque. Nulla finibus convallis
 * elementum. Suspendisse semper posuere
 * rutrum. Donec interdum ex a ex ornare,
 * eget feugiat urna volutpat. Fusce vitae
 * pharetra tortor, ac tincidunt erat.
 * Interdum et malesuada fames ac ante
 * ipsum primis in faucibus. Pellentesque
 * feugiat diam quis nibh iaculis
 * tincidunt.
 * }
 * </pre>
 */
public class SplitSizeStringFormatter extends SizedStringFormatter {

    /**
     * Creates a formatter which appends a line break ({@code \n}) when the text of current line
     * reaches the specified {@code size}.
     *
     * @param size Size before line break is appended.
     */
    public SplitSizeStringFormatter(int size) {
        super(size);
    }

    @Override
    public String format(String text) {
        StringBuilder sb = new StringBuilder();
        // Splits the text by blank space and line break without removing them from the text
        String[] split = text.split("(?=[ \n])");
        // Stores the length of current line
        // the same value can be retrieved using `sb.lastIndexOf('\n')`
        // but in this case this is not needed
        int len = 0;

        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            int stringLength = s.length();

            // If this is the first word of the line and starts with the blank space
            // appends the string without it
            if (len <= 0 && s.startsWith(" ")) {
                sb.append(s.substring(1));
            } else {
                sb.append(s);
            }

            // In case that this is a line break we should reset the length instead of
            // adding to it
            // I'm not sure, but
            if (!s.equals("\n")) {
                len += stringLength;
            } else if (s.startsWith("\n")) {
                // This is a trick to avoid counting a line break before a text as length of the line
                // The line break may appear in the front of a string because of the pattern
                // used to split the text. Also this is the reason that the `len` check above is against zero or less
                // instead of zero only.
                // This can be changed by changing the `else` of the `len` check above by a check
                // of `s.startsWith('\n')` and then calling `sb.append('\n')` and `s = s.substring(1)` and `sb.append(s)`.
                len = -1;
            } else {
                len = 0;
            }

            // Checks if has more words
            if (i + 1 < split.length) {
                // Gets next word
                String next = split[i + 1];

                // Checks if the current len plus the len of next word is greater than specified size
                // and append a line, if it is the case.
                if ((len + next.length()) > this.getSize()) {
                    len = 0;
                    sb.append('\n');
                }
            }
        }

        return sb.toString();
    }
}
