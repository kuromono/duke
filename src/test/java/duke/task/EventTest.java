package duke.task;

import duke.program.TaskLogic;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() {
        Event event = new Event("Event_Test", TaskLogic.DateMaker("21/10/1995 1844"));
        assertEquals(event.toString(), "[E][\u2718] Event_Test (at: 1995-10-21 18:44)");
        assertEquals("Event_Test", event.getDescription());
    }

    @Test
    public void testSetDone() {
        Event event = new Event("Event_Test", TaskLogic.DateMaker("21/10/1995 1844"));
        event.setDone();
        assertEquals("[\u2713]", event.getStatusIcon()); // Tick
    }
}