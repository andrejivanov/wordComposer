package de.ai.kata;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

class DumbComposer implements Composer {

    @Override
    public String description() {
        return "strait forward implementation of composer, triple loop, very slow";
    }

    public List<String> compose(List<String> words) {
        List<String> wordsWithSixChars = words.stream().filter(word -> word.length() == 6).collect(toList());

        List<String> results = new ArrayList<>();
        wordsWithSixChars.forEach(
                sixCharsWord -> words.forEach(
                        firstWord -> words.forEach(
                                secondWord -> {
                                    if (sixCharsWord.equals(firstWord + secondWord)) {
                                        results.add(firstWord + " + " + secondWord + " => " + sixCharsWord);
                                    }
                                }
                        )
                )
        );
        return results;
    }
}
