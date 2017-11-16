package de.ai.kata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) {
        try {
            List<String> words = loadWordsFromFile(args[0]);

            runComposer(new OptimizedComposer(), words);

            runComposer(new SimpleOptimizedComposer(), words);

            runComposer(new DumbComposer(), words);

        } catch (IOException e) {
            System.out.println("Sorry, file not found: " + args[0]);
            e.printStackTrace();
        }
    }

    private static void runComposer(Composer composer, List<String> words) {
        System.out.println("Use " + composer.description());
        long start = System.currentTimeMillis();
        List<String> composedWords = composer.compose(words);
        long end = System.currentTimeMillis();
        System.out.println("Found " + composedWords.size() + " words. Duration " + (end - start) + " ms.");
        composedWords.forEach(System.out::println);
    }

    private static List<String> loadWordsFromFile(String path) throws IOException {
        return Files.lines(Paths.get(path)).collect(toList());
    }
}
