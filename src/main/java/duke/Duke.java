package duke;

/**
 * Driver class for the bot.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructor for the Bot.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList(storage.readFromStorage());
    }

    /**
     * Function to enable the bot and start interaction with the user.
     */
    public void run() {
        this.ui.greet();
        String userInput = ui.askForInput();

        while (!userInput.equals("bye")) {
            String[] processedInput = Parser.parseInput(userInput);
            ui.say(Command.execute(processedInput, this.taskList));
            userInput = ui.askForInput();
        }
        storage.writeToStorage(this.taskList);
        ui.sayGoodbye();
    }
}


