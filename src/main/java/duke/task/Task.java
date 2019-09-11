package duke.task;

import java.io.Serializable;

/**
 * Main class that allows a task to be specified.
 * Superclass of Deadline, Event and Todo.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task that takes in the description of the task.
     * @param description A String containing a short description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Allows to set the task as completed.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Allows to get the description of the task.
     * @return String of the task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Allows to get the status of completion of the task.
     * @return String containing a tick or cross symbol indication whether the task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    /**
     * Converts several information of the tasks to a readable stirng, such as completion status and description.
     * @return String containing completion status and description of task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}