package com.github.hronom.logspammer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class LogSpammerApp implements ApplicationRunner {
    private final Log logger = LogFactory.getLog(getClass());

    public static void main(String[] args) {
        SpringApplication.run(LogSpammerApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String cyrillicSetStr = findCharactersInUnicodeBlock(Character.UnicodeBlock.CYRILLIC).toString();
        String latinSetStr = findCharactersInUnicodeBlock(Character.UnicodeBlock.BASIC_LATIN).toString();

        StringBuilder sb = new StringBuilder();
        sb.append(cyrillicSetStr);
        sb.append("\n");
        sb.append(latinSetStr);
        sb.append("\n");
        sb.append("<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<title>Page Title</title>\n" +
            "</head>\n" + "<body>\n" + "\n" + "<h1>This is a Heading</h1>\n" +
            "<p>This is a paragraph.</p>\n" + "\n" + "</body>\n" + "</html>");
        sb.append("\n");
        for (int j = 0; j < 2_000; j++) {
            sb.append(String.valueOf(j));
        }

        String str = sb.toString();

        for (int i = 0; i < 50_000; i++) {
            logger.info(String.format("Entry %d %s", i, str));
        }
    }

    private static Set<Character> findCharactersInUnicodeBlock(final Character.UnicodeBlock block) {
        final Set<Character> chars = new HashSet<>();
        for (int codePoint = Character.MIN_CODE_POINT; codePoint <= Character.MAX_CODE_POINT; codePoint++) {
            if (block == Character.UnicodeBlock.of(codePoint)) {
                chars.add((char) codePoint);
            }
        }
        return chars;
    }
}