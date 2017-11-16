package de.ai.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

class Node {

    private List<Node> children;
    private char key;
    private boolean wordEnd;

    Node(char key) {
        this.children = new ArrayList<>();
        this.wordEnd = false;
        this.key = key;
    }

    private char getKey() {
        return key;
    }

    private boolean isWordEnd() {
        return wordEnd;
    }

    private void setWordEnd() {
        this.wordEnd = true;
    }

    void insert(String word) {
        Node node = getOrCreate(word.charAt(0));
        if (word.length() == 1) {
            node.setWordEnd();
        } else {
            node.insert(word.substring(1));
        }
    }

    List<Boolean> getPath(String word) {
        Optional<Node> child = getChildByKey(word.charAt(0));
        if (child.isPresent()) {
            ArrayList<Boolean> endOfWords = new ArrayList<>();
            endOfWords.add(child.get().isWordEnd());
            if (word.length() == 1) {
                return endOfWords;
            } else {
                endOfWords.addAll(child.get().getPath(word.substring(1)));
                return endOfWords;
            }
        } else {
            return emptyList();
        }
    }

    boolean exists(String word) {
        if (word.isEmpty()) {
            return this.isWordEnd();
        } else {
            Optional<Node> childByKey = getChildByKey(word.charAt(0));
            return childByKey.map(node -> node.exists(word.substring(1))).orElse(false);
        }
    }

    private Node getOrCreate(char character) {
        return getChildByKey(character).orElseGet(() -> create(character));
    }

    private Optional<Node> getChildByKey(char character) {
        return children.stream().filter(node -> node.getKey() == character).findFirst();
    }

    private Node create(char character) {
        Node node = new Node(character);
        this.children.add(node);
        return node;
    }

}
