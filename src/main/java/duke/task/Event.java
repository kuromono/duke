package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class of Event Task that allows an event date to be set.
 * Subclass of Task.
 */
public class Event extends Task {
    protected LocalDateTime date;

    /**
     * Constructor of Event subclass Task that takes in description and date of event.
     * @param description A String containing a short description of task.
     * @param date A LocalDateTime object containing the date of event.
     */
    public Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * Overrides Task toString method by appending Event type and event date to the string.
     * @return String indicating Event type, followed by Task data and finally date of event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.toLocalDate().toString() + " " + date.toLocalTime().toString() + ")";
    }
}
