package it.uniba.app;

/**
 * < < Entity > > Tipo composto da una stringa e un booleano per ogni carattere
 */
class Word {
    private String word;

    private boolean[] isMarked;

    public Word(String word) {
        this.word = word;
        this.isMarked = new boolean[5];
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
