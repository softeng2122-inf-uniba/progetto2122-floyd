package it.uniba.app.match;

/**
 * < < Entity > > Tipo composto da una stringa e un booleano per ogni carattere
 */
public class Word {
    private String word;

    private boolean[] marked;

    public Word(String word) {
        this.word = word;
        this.marked = new boolean[Match.NUM_OF_CELLS];
    }

    public String getString() {
        return word;
    }

    public boolean isMarked(int index) {
        return marked[index];
    }

    public void setMarked(int index) {
        marked[index] = true;
    }

    public void resetMarked() {
        for (int i = 0; i < marked.length; i++) {
            marked[i] = false;
        }
    }

}
