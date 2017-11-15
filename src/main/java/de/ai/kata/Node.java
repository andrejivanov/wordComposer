package de.ai.kata;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Node {
    private Set<Node> children;
    private char key;
    private boolean wordEnd;

    public Node(char key) {
        this.children = new HashSet<>();
        this.wordEnd = false;
        this.key = key;
    }

    public Node() {
        this.children = new HashSet<>();
        this.wordEnd = false;
    }

    public char getKey() {
        return key;
    }

    public boolean isWordEnd() {
        return wordEnd;
    }

    public void setWordEnd(boolean wordEnd) {
        this.wordEnd = wordEnd;
    }

    public void insert(String word) {
        Node node = getOrCreate(word.charAt(0));
        if (word.length() == 1) {
            node.setWordEnd(true);
        } else {
            node.insert(word.substring(1));
        }
    }

    public void getPath(String word) throws NodeNotFoundException {
        Node child = getChildByKey(word.charAt(0)).orElseThrow(NodeNotFoundException::new);
    }

    private Node getOrCreate(char character) {
        return getChildByKey(character).orElse(new Node());
    }

    private Optional<Node> getChildByKey(char character) {
        return children.stream().filter(node -> node.getKey() == character).findFirst();
    }
}
