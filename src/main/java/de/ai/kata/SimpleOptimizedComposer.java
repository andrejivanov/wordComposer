package de.ai.kata;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

class SimpleOptimizedComposer implements Composer {

    @Override
    public String description() {
        return "simple optimized composer with prefiltered lists";
    }

    @Override
    public List<String> compose(List<String> words) {

        Set<String> wordsWithSixChars = words.parallelStream()
                .filter(word -> word.length() == 6).collect(toSet());
        Set<String> wordsShorterSixChars = words.parallelStream()
                .filter(word -> word.length() <= 6 && !word.isEmpty())
                .collect(Collectors.toSet());

        return wordsWithSixChars.parallelStream()
                .flatMap(word -> searchWordCompositons(word, wordsShorterSixChars).parallelStream())
                .collect(Collectors.toList());
    }

    private List<String> searchWordCompositons(String sixCharsWord, Set<String> potentialComponents) {

        Predicate<String> sameFirstChar = word -> word.charAt(0) == sixCharsWord.charAt(0);

        return potentialComponents.parallelStream()
                .filter(sameFirstChar)
                .map(firstWord -> searchForSecondWord(sixCharsWord, firstWord, potentialComponents))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<String> searchForSecondWord(String word, String firstPart, Set<String> potentialComponents) {

        Predicate<String> sameStartCharAndEqualsRestCharCount = secondPart ->
                secondPart.length() == word.length() - firstPart.length()
                        && secondPart.charAt(0) == word.charAt(firstPart.length());

        return potentialComponents.parallelStream()
                .filter(sameStartCharAndEqualsRestCharCount)
                .filter(secondPart -> word.equals(firstPart + secondPart))
                .map(foundSecondPart -> firstPart + " + " + foundSecondPart + " => " + word)
                .findFirst();
    }
}
