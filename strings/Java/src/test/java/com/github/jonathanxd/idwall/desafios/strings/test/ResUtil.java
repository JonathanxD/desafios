package com.github.jonathanxd.idwall.desafios.strings.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

public class ResUtil {

    public static String getAsString(InputStream inputStream, Charset charset) {
        return new BufferedReader(new InputStreamReader(inputStream, charset))
                .lines()
                .collect(Collectors.joining("\n"));
    }

}
