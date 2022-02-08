package duke.Commands;

import duke.Exception.DukeException;

/**
 * Class containing methods to process the user input into a easier to handle form.
 */
public class Parser {

    private static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static int processNumber(String userInput) throws DukeException {
        if (!isInteger(userInput)) {
            throw new DukeException("☹ OOPS!!! Please enter a appropriate integer\n");
        }
        return Integer.parseInt(userInput) - 1;
    }

    private static String[] processInput(String inputMessage) throws DukeException {
        String[] msg = new String[]{"null"};
        // One word messages
        if (inputMessage.equals("list")) {
            msg = new String[]{"list"};
        } else {
            // Mark and unmark have the same syntax, so only process it once
            try {
                if (inputMessage.substring(0, 4).contains("find")) {
                    if (inputMessage.length() <= 4) {
                        throw new DukeException(
                                "☹ OOPS!!! I'm sorry, but you need to provide a description "
                                        + "for the find task\n");
                    }
                    msg = inputMessage.split("find ");
                    msg[0] = "find";
                } else if (inputMessage.substring(0, 4).contains("todo")) {
                    if (inputMessage.length() <= 4) {
                        throw new DukeException(
                                "☹ OOPS!!! I'm sorry, but you need to provide a description "
                                        + "for the Todo task\n");
                    }
                    msg = inputMessage.split("todo ");
                    msg[0] = "todo";
                } else if (inputMessage.substring(0, 6).contains("event ")) {
                    if (inputMessage.contains(" /at")) {
                        String temp = inputMessage.substring(6);
                        msg = temp.split(" /at");
                        if (msg.length != 2) {
                            throw new DukeException(
                                    "☹ OOPS!!! I'm sorry, the duke.Tasks.Event description cannot be empty\n");
                        }
                        msg = new String[] {"event", msg[0], msg[1]};
                    } else {
                        throw new DukeException(
                                "☹ OOPS!!! I'm sorry, but you need to use the following format:\n"
                                        + "event {description} /at {yyyy/MM/dd HH:mm}\n");
                    }
                } else if (inputMessage.contains("delete") || inputMessage.contains("mark")) {
                    msg = inputMessage.split(" ");
                    if (msg.length != 2) {
                        throw new DukeException(
                                "☹ OOPS!!! I'm sorry, but you need to enter a number as well!\n");
                    }
                } else if (inputMessage.substring(0, 9).contains("deadline ")) {
                    if (inputMessage.contains("/by")) {
                        String temp = inputMessage.substring(9);
                        msg = temp.split(" /by");
                        if (msg.length != 2) {
                            throw new DukeException(
                                    "☹ OOPS!!! I'm sorry, the Deadline description cannot be empty\n");
                        }
                        msg = new String[] {"deadline", msg[0], msg[1]};
                    } else {
                        throw new DukeException(
                                "☹ OOPS!!! I'm sorry, but you need to use the following format:\n"
                                        + "deadline {description} /by {yyyy/MM/dd HH:mm}\n");
                    }
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Unrecognized command");
            }
        }
        return msg;
    }

    /**
     * Processes the users input and formats it into a String array separating the important information.
     *
     * @param userInput raw String containing the user's input
     * @return String array containing information on the type of task, description and timing and
     *         if required, else if invalid input return a String array containing only one element "null"
     */
    public static String[] parseInput(String userInput) {
        try {
            return processInput(userInput);
        } catch (DukeException e) {
            return new String[]{"Error", e.getMessage()};
        }
    }
}
