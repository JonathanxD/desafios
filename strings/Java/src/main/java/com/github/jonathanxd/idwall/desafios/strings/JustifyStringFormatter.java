package com.github.jonathanxd.idwall.desafios.strings;

/**
 * A formatter which justifies the text by appending blank spaces {@code " "} after words until the
 * size of the line matches the specified {@code size}.
 *
 * Example, the following text:
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
 *
 * Will be formatted to:
 *
 * <pre>
 * {@code
 * Lorem ipsum dolor sit amet,  consectetur
 * adipiscing  elit. Vivamus neque   dolor,
 * bibendum  ac  rhoncus  sit  amet,  porta
 * vitae  neque.  Nulla  finibus  convallis
 * elementum.  Suspendisse  semper  posuere
 * rutrum.  Donec interdum ex a ex  ornare,
 * eget feugiat urna volutpat. Fusce  vitae
 * pharetra  tortor,  ac  tincidunt   erat.
 * Interdum  et  malesuada  fames  ac  ante
 * ipsum  primis in faucibus.  Pellentesque
 * feugiat    diam   quis   nibh    iaculis
 * tincidunt.
 * }
 * </pre>
 *
 */
public class JustifyStringFormatter extends SizedStringFormatter {

    /**
     * Creates a formatter which justifies the text by appending blank spaces {@code " "} after
     * words until the size of the line matches the specified {@code size}.
     *
     * @param size Size to justify the text.
     */
    public JustifyStringFormatter(int size) {
        super(size);
    }

    @Override
    public String format(String text) {
        StringBuilder sb = new StringBuilder();

        // Loops each line of text
        for (String line : text.split("(?=\n)")) {

            // Replaces the line break with an empty string,
            // because the line breaks count as characters too and we don't want that.
            String cleanText = line.replace("\n", "");

            if (cleanText.length() != this.getSize()) {
                // Gets the amount of spaces
                // Same as (cleanText.split(" ").length - 1)
                int spaces = cleanText.length() - cleanText.replace(" ", "").length();

                if (spaces != 0) {
                    // Number of characters required to this text to be the same size as the specified size
                    int numberOfChars = (this.getSize() - cleanText.length());
                    // Amount of spaces to be added to each word
                    int eachWord = numberOfChars / spaces;
                    // Amount of spaces to be added to first and last word (rest of previous division).
                    int firstAndLast = numberOfChars % spaces;

                    // The string with blank spaces to be added
                    // Also it works with a loop in place of String.format
                    // appending to a StringBuilder
                    String spacesText = eachWord != 0 ? String
                            .format("%" + eachWord + "s", "") : "";

                    // Splits the blank spaces
                    String[] split = line.split("(?= )");

                    for (int i = 0; i < split.length; i++) {
                        String withSpace = split[i];

                        // This checks if this is not the first word of the line
                        // this is required because spaces is appended before the text (not after it)
                        if (i >= 1) {
                            // Stores whether this is the last word of the line
                            boolean isLast = i + 1 == split.length;

                            // Checks if this is the first or last word of the line
                            // and if additional space is required in the first and last
                            // word.
                            if ((i == 1 || isLast) && firstAndLast != 0) {
                                // Number of additional spaces to add to current word
                                int additionalSpaces = isLast ? firstAndLast : firstAndLast / 2;

                                // Add the spaces and decrements the amount of spaces
                                // to add to last word. (if this is the last word, well, the number will be negative).
                                for (int u = 0; u < additionalSpaces; ++u) {
                                    sb.append(' ');
                                    --firstAndLast;
                                }
                            }

                            // Append spaces required for each word
                            sb.append(spacesText);
                        }

                        // Append the current word.
                        sb.append(withSpace);

                    }

                    // Skips the code above (sb.append(line)) because
                    // current line was already processed.
                    continue;
                }
            }

            // Appends text without changes
            // this only happens if either the text size if greater or equal the specified size
            // or number of spaces to add is equal to 0
            sb.append(line);
        }

        return sb.toString();
    }
}
