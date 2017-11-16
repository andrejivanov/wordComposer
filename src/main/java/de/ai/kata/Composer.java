package de.ai.kata;

import java.util.List;

public interface Composer {

    // Description of composer. Short explanation how composer works.
    String description();

    // Takes whole list of words and
    // returns list with all six letter words which are composed of two concatenated smaller words
    // E.g.:
    // al + bums => albums
    // bar + ely => barely
    List<String> compose(List<String> words);
}
