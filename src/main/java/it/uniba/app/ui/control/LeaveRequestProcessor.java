package it.uniba.app.ui.control;

import it.uniba.app.match.controller.MatchController;
import it.uniba.app.ui.UserInterface;
import it.uniba.app.utils.ExecutableTask;

/**
 * Tries to leave the match, if there's one in progress.
 * Prints error otherwise.
 */
public class LeaveRequestProcessor implements ExecutableTask {
    
    /** Reference to the match controller. */
    private final MatchController matchController;

    /**
     * @param matchControllerObj the reference to the match controller.
     */
    public LeaveRequestProcessor(final MatchController matchControllerObj) {
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
