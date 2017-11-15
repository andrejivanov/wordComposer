package de.ai.kata;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Slf4j
class SimpleOptimizedComposer implements Composer {

    @Override
    public String description() {
        return "simple optimized composer with prefiltered lists";
    }

    @Override
    public List<String> compose(List<String> words) {

        Set<String> wordsWithSixChars = words.stream()
                .filter(word -> word.length() == 6).collect(toSet());
        TreeSet<String> wordsShorterSixChars = words.stream()
                .filter(word -> word.length() <= 6 && !word.isEmpty())
                .collect(Collectors.toCollection(TreeSet::new));

        return wordsWithSixChars.stream()
                .flatMap(word -> searchWordCompositons(word, wordsShorterSixChars).stream())
                .collect(Collectors.toList());
    }

    private List<String> searchWordCompositons(String sixCharsWord, TreeSet<String> potentialComponents) {

        Predicate<String> sameFirstChar = word -> word.charAt(0) == sixCharsWord.charAt(0);

        return potentialComponents.stream()
                .filter(sameFirstChar)
                .map(firstWord -> searchForSecondWord(sixCharsWord, firstWord, potentialComponents))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<String> searchForSecondWord(String word, String firstPart, TreeSet<String> potentialComponents) {

        Predicate<String> sameStartCharAndEqualsRestCharCount = secondPart ->
                secondPart.length() == word.length() - firstPart.length()
                        && secondPart.charAt(0) == word.charAt(firstPart.length());

        return potentialComponents.stream()
                .filter(sameStartCharAndEqualsRestCharCount)
                .filter(secondPart -> word.equals(firstPart + secondPart))
                .map(foundSecondPart -> firstPart + " + " + foundSecondPart + " = " + word)
                .findFirst();
    }
}
