package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testToString() {
        Todo todo = new Todo("Todo_Test");
        assertEquals(todo.toString(), "[T][\u2718] Todo_Test");
        assertEquals("Todo_Test", todo.getDescription());
    }

    @Test
    public void testSetDone() {
        Todo todo = new Todo("Todo_Test");
        todo.setDone();
        assertEquals("[\u2713]", todo.getStatusIcon()); // Tick
    }
}
