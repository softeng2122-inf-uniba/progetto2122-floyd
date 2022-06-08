package it.uniba.app.utils;

/**
 * {@literal <<noECB>>}
 * A class that implements this interface can
 * execute a task without returning anything but passing a string.
 * It indicates that there's only one task and
 * nothing else.
 */
public interface ExecutableTaskString {

    /**
     * Executes a command on the object.
     *
     * @param str the string to pass.
     */
    void execute(String str);
}
