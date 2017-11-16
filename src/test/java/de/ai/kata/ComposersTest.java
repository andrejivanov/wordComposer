package de.ai.kata;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;


public class ComposersTest {

    private List<String> allWords;
    private List<String> desiredResult;

    @Before
    public void setUp() throws Exception {
        allWords = loadWordsFromFile();
        desiredResult = desiredResult();
        desiredResult.sort(Collator.getInstance(new Locale("de")));
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
        System.out.println("Results " + results.size());
        // assume order of results doesn't matter
        results.sort(Collator.getInstance(new Locale("de")));
        assertEquals("List should be exact same", desiredResult, results);
    }

    private static List<String> loadWordsFromFile() throws IOException {
        return Files.lines(Paths.get("src/test/resources/wordlistShort.txt")).collect(toList());
    }

    private List<String> desiredResult() {
        List<String> desiredList = new ArrayList<>(14);
        desiredList.add("aaa + bbb => aaabbb");
        desiredList.add("aaab + bb => aaabbb");
        desiredList.add("ddd + eee => dddeee");
        desiredList.add("al + bums => albums");
        desiredList.add("bar + ely => barely");
        desiredList.add("be + foul => befoul");
        desiredList.add("con + vex => convex");
        desiredList.add("here + by => hereby");
        desiredList.add("jig + saw => jigsaw");
        desiredList.add("tail + or => tailor");
        desiredList.add("we + aver => weaver");
        desiredList.add("echt + er => echter");
        desiredList.add("wer + den => werden");
        desiredList.add("wie + der => wieder");
        return desiredList;
    }
}
