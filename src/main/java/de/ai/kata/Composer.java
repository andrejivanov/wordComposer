package de.ai.kata;

import java.util.List;

public interface Composer {

    String description();

    List<String> compose(List<String> words);
}
