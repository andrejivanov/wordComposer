package de.ai.kata;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
class OptimizedComposer implements Composer {

    @Override
    public String description() {
        return "optimized composer with filled search tree";
    }

    @Override
    public List<String> compose(List<String> words) {
        List<String> wordsWithSixChars = words.stream().filter(word -> word.length() == 6).collect(toList());
        List<String> wordsShorterSixChars = words.stream()
                .filter(word -> word.length() <= 6 && !word.isEmpty())
                .collect(Collectors.toList());

        Node searchTree = new Node();
        wordsShorterSixChars.forEach(searchTree::insert);
        return new ArrayList<>();
    }
}
