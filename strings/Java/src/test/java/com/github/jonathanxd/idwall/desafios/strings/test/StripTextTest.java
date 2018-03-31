package com.github.jonathanxd.idwall.desafios.strings.test;

import com.github.jonathanxd.idwall.desafios.strings.JustifyStringFormatter;
import com.github.jonathanxd.idwall.desafios.strings.SplitSizeStringFormatter;
import com.github.jonathanxd.idwall.desafios.strings.StringFormatter;

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;

public class StripTextTest {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final String INPUT_TEXT = StripTextTest.getRes("input.txt");
    private static final String EXPECTED_OUTPUT = StripTextTest.getRes("expected_output.txt");
    private static final String EXPECTED_OUTPUT_JUSTIFIED =
            StripTextTest.getRes("expected_output_justified.txt");
    private static final String LIPSUM = StripTextTest.getRes("lipsum.txt");
    private static final String EXPECTED_LIPSUM = StripTextTest.getRes("expected_lipsum.txt");

    private static String getRes(String res) {
        return ResUtil
                .getAsString(StripTextTest.class.getClassLoader().getResourceAsStream(res), UTF_8);
    }

    @Test
    public void stripTextTest() {
        StringFormatter formatter = new SplitSizeStringFormatter(40);
        String s = formatter.format(INPUT_TEXT);
        Assert.assertEquals(EXPECTED_OUTPUT, s);
    }

    @Test
    public void stripTextAndJustifyTest() {
        StringFormatter formatter = new SplitSizeStringFormatter(40)
                .and(JustifyStringFormatter::new);

        String s = formatter.format(INPUT_TEXT);
        Assert.assertEquals(EXPECTED_OUTPUT_JUSTIFIED, s);
    }

    @Test
    public void lipsumTest() {
        StringFormatter formatter = new SplitSizeStringFormatter(40)
                .and(JustifyStringFormatter::new);

        String s = formatter.format(LIPSUM);
        Assert.assertEquals(EXPECTED_LIPSUM, s);
    }
}
