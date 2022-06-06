package it.uniba.app.match;

/**
 * < < Entity > > Tipo composto da una stringa e un booleano per ogni carattere
 */
public class Word {
    private String word;

    private boolean[] isMarked;

    public Word(String word) {
        this.word = word;
        this.isMarked = new boolean[Match.NUM_OF_CELLS];
    }

    public String getWord() {
        return word;
    }

    public boolean getIsMarked(int index) {
        return isMarked[index];
    }

    public void setAsMarked(int index) {
        isMarked[index] = true;
    }

    public void resetMarked() {
        for (int i = 0; i < isMarked.length; i++) {
            isMarked[i] = false;
        }
    }

}
