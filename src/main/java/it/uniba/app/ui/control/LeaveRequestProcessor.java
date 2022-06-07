package it.uniba.app.ui.control;

import it.uniba.app.match.controller.MatchController;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ExecutableTask;

public class LeaveRequestProcessor implements ExecutableTask {
    private final MatchController matchController;

    public LeaveRequestProcessor(MatchController matchControllerObj) {
        this.matchController = matchControllerObj;
    }

    @Override
    public void execute() {
        if (matchController.isInProgress()) {
            UserInterface.printer.getLeaveRequestConfirmation();
            if (new ConfirmationRequester().execute()) {
                matchController.endMatch();
            }
        } else {
            UserInterface.printer.getNoMatchToLeave();
        }
    }

}
