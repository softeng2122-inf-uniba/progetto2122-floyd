package it.uniba.app.match;

/**
 * {@literal <<entity>>}
 * Represents the secret word of a match.
 * Composed of a string and an array of bools.
 * <p>
 * The idea is that for each character in the string,
 * there's a boolean telling whether or not that
 * character has already been reported by the colors
 * of the cells.
 */
public class Word {
    
    /** String representing the secret word. */
    private String word;

    /**
     * Holds the markings for each letter,
     * when they get checked.
     * <p>
     * Max number is given by
     * {@link it.uniba.app.match.Match#NUM_OF_CELLS}
     */
    private boolean[] marked;

    /** Instantiates an empty word object. */
    public Word() {
        this.word = null;
        this.marked = new boolean[Match.NUM_OF_CELLS];
    }

    /**
     * Copy constructor. Instantiates a copied word.
     *
     * @param wordObj the secret word to copy.
     */
    public Word(final Word wordObj) {
        this.word = wordObj.word;
        this.marked = new boolean[Match.NUM_OF_CELLS];
        for (int i = 0; i < this.marked.length; i++) {
            this.marked[i] = wordObj.marked[i];
        }
    }
    
    /**
     * @return the secret word as string.
     */
    public String getString() {
        return word;
    }

    /**
     * Sets a new secret word.
     *
     * @param str the new secret word.
     */
    public void setString(String str) {
        this.word = str;
    }

    /**
     * @param idx the cell number to retrieve from.
     * @return {@code true} if that cell has been already visited,
     *         {@code false} otherwise.
     */
    public boolean isMarked(int idx) {
        return marked[idx];
    }

    /**
     * Sets the given cell as visited.
     *
     * @param idx the cell number to flag as marked.
     */
    public void setMarked(int idx) {
        marked[idx] = true;
    }
}
