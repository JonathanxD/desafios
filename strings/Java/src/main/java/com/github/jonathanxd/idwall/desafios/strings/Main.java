package com.github.jonathanxd.idwall.desafios.strings;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size:");
        int size = scanner.nextInt();

        System.out.println("Justify (true/false):");
        boolean justify = scanner.nextBoolean();

        System.out.println("Enter with the text (and then enter ^EOF):");

        StringBuilder text = new StringBuilder();

        String s;

        while (!(s = scanner.nextLine()).equals("^EOF")) {
            text.append(s).append('\n');
        }

        StringFormatter formatter = new SplitSizeStringFormatter(size);

        if (justify) {
            formatter = formatter.and(new JustifyStringFormatter(size));
        }

        System.out.println(formatter.format(text.toString()));
    }

}
