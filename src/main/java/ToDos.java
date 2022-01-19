public class ToDos extends Task {

    public ToDos (String task) throws DukeException {
        super(task);
        if (task.length() < 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
