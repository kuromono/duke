package duke.task;

import duke.program.TaskLogic;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToString() {
        Deadline deadline = new Deadline("Deadline_Test", TaskLogic.DateMaker("21/10/1995 1844"));
        assertEquals(deadline.toString(), "[D][\u2718] Deadline_Test (by: 1995-10-21 18:44)");
        assertEquals("Deadline_Test", deadline.getDescription());
    }

    @Test
    public void testSetDone() {
        Deadline deadline = new Deadline("Deadline_Test", TaskLogic.DateMaker("21/10/1995 1844"));
        deadline.setDone();
        assertEquals("[\u2713]", deadline.getStatusIcon()); // Tick
    }
}
