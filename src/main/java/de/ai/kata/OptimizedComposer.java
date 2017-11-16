package de.ai.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

class OptimizedComposer implements Composer {

    @Override
    public String description() {
        return "optimized composer with search tree, very fast";
    }

    @Override
    public List<String> compose(List<String> words) {
        List<String> wordsWithSixChars = words.stream().filter(word -> word.length() == 6).collect(toList());
        List<String> wordsShorterSixChars = words.stream()
                .filter(word -> word.length() <= 6 && !word.isEmpty())
                .collect(Collectors.toList());

        Node searchTree = new Node('~');
        wordsShorterSixChars.forEach(searchTree::insert);

        List<String> results = new ArrayList<>();

        wordsWithSixChars.forEach(
                word -> {
                    int index = 0;
                    List<Boolean> path = searchTree.getPath(word);
                    for (Boolean wordExists : path) {
                        if (wordExists && searchTree.exists(word.substring(index + 1))) {
                            results.add(word.substring(0, index + 1) + " + " + word.substring(index + 1) + " => " + word);
                        }
                        index++;
                    }
                }
        );
        return results;
    }


}
