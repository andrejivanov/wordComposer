package de.ai.kata;

import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;


public class ComposersTest {

    private List<String> allWords;

    @Before
    public void setUp() throws Exception {
        allWords = loadWordsFromFile();
    }

    @Test
    public void dumbComposerShouldFindAllResults() throws Exception {
        runComposerWithMetering(new DumbComposer());
    }

    @Test
    public void simpleOptimizedComposerShouldFindAllResults() throws Exception {
        runComposerWithMetering(new SimpleOptimizedComposer());
    }

    @Test
    public void optimizedComposerShouldFindAllResults() throws Exception {
        runComposerWithMetering(new OptimizedComposer());
    }

    private void runComposerWithMetering(Composer composer) {
        long start = System.currentTimeMillis();
        List<String> results = composer.compose(allWords);
        long end = System.currentTimeMillis();
        System.out.println("Test " + composer.description() + ". Duration " + (end - start) + " ms.");
        assertEquals("List should contain 11 results", 14, results.size());
    }

    @SneakyThrows(IOException.class)
    private static List<String> loadWordsFromFile() {
        return Files.lines(Paths.get("src/test/resources/wordlistShort.txt")).collect(toList());
    }
}
