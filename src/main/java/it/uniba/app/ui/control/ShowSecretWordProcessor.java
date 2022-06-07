package it.uniba.app.ui.control;

import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ExecutableTask;

public class ShowSecretWordProcessor implements ExecutableTask {

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
