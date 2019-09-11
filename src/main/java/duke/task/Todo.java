package duke.task;

/**
 * Class of Todo Task that simply allows description to be specified.
 * Subclass of Task.
 */
public class Todo extends Task {

    /**
     * Constructor of Todo subclass Task that takes in a description.
     * @param description A String containing a short description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides Task toString method by appending Todo type.
     * @return String indicating Todo type, followed by Task data.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}