package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class of Deadline Task that allows a deadline to be set.
 * Subclass of Task.
 */
public class Deadline extends Task {
    protected LocalDateTime date;

    /**
     * Constructor of Deadline subclass Task that takes in description and deadline of task.
     * @param description A String containing a short description of task.
     * @param date A LocalDateTime object containing the deadline of task.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * Overrides Task toString method by appending Deadline type and deadline date to the string.
     * @return String indicating Deadline type, followed by Task data and finally deadline date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.toLocalDate().toString() + " " + date.toLocalTime().toString() + ")";
    }
}