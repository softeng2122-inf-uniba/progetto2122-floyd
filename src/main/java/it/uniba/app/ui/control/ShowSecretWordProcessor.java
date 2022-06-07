package it.uniba.app.ui.control;

import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ExecutableTaskString;

public class ShowSecretWordProcessor implements ExecutableTaskString {

    public ShowSecretWordProcessor() {
    }

    @Override
    public void execute(String lastSecretWord) {
        if (lastSecretWord == null) {
            UserInterface.printer.getSecretWordMissing();
        } else {
            UserInterface.printer.getShowSecretWord(lastSecretWord);
        }
    }

}
