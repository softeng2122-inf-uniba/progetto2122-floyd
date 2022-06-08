package it.uniba.app.ui.control;

import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ExecutableTaskString;

/**
 *  @{literal <<control>>}
 * Prints the latest secret word, if there is one.
 * Prints error otherwise.
 */
public final class ShowSecretWordProcessor implements ExecutableTaskString {

    /** Instantiates a ShowSecretWord task. */
    public ShowSecretWordProcessor() {
    }

    /**
     * @param lastSecretWord the latest secret word.
     *                       Can be {@code null} if none is set.
     */
    @Override
    public void execute(final String lastSecretWord) {
        if (lastSecretWord == null) {
            UserInterface.printer.getSecretWordMissing();
        } else {
            UserInterface.printer.getShowSecretWord(lastSecretWord);
        }
    }

}
