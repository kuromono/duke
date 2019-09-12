package duke.program;

import duke.task.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskLogicTest {

    // Carriage Return Line Feed - edit this if test fails
    public static final String CRLF = "\r\n";

    @Test
    public void testList() {
        //surpress output with redirector
        PrintStream sysout = System.out;
        ByteArrayOutputStream out = SampleData.outputRedirector();

        // Test blank
        ArrayList<Task> task_list = new ArrayList<Task>();
        TaskLogic.list(task_list);
        String expected =
                "____________________________________________________________" + CRLF +
                "There is nothing in the list!" + CRLF +
                "____________________________________________________________" + CRLF;

        assertEquals(expected, out.toString());

        out.reset();

        task_list = SampleData.initTestTasks();
        TaskLogic.list(task_list);
        expected =
            " 1.[T][✘] test_1" + CRLF +
            " 2.[T][✘] test_2" + CRLF +
            " 3.[D][✓] test_3 (by: 2010-10-10 10:00)" + CRLF +
            " 4.[T][✓] test_4" + CRLF +
            " 5.[E][✘] test_5 (at: 2010-10-10 20:00)" + CRLF +
            " 6.[T][✓] test_6" + CRLF;

        assertEquals(expected, out.toString());

        //revert normal output
        System.setOut(sysout);
    }

    @Test
    public void testDone() {
        ArrayList<Task> task_list = SampleData.initTestTasks();

        TaskLogic.done(task_list, 1);
        String expected = "[\u2713]";
        assertEquals(expected, task_list.get(0).getStatusIcon());
    }

    @Test
    public void testDeleteTask() {
        //surpress output with redirector
        PrintStream sysout = System.out;
        SampleData.outputRedirector();

        ArrayList<Task> task_list = SampleData.initTestTasks();

        int expected = task_list.size() - 1;
        TaskLogic.delete(task_list, task_list.size()/2);
        assertEquals(expected, task_list.size());

        // Outside of bounds should not delete
        TaskLogic.delete(task_list, 0);
        assertEquals(expected, task_list.size());

        // Outside of bounds should not delete
        TaskLogic.delete(task_list, task_list.size() + 1);
        assertEquals(expected, task_list.size());

        //revert normal output
        System.setOut(sysout);
    }

    @Test
    public void testAddTask() {
        //surpress output with redirector
        PrintStream sysout = System.out;
        SampleData.outputRedirector();

        ArrayList<Task> task_list = new ArrayList<Task>();

        String desc = "Test_Task";
        String date = "";
        Task t = TaskLogic.make_task("todo", desc, date);

        int expected = task_list.size() + 1;

        TaskLogic.add_task(task_list, t);
        assertEquals(expected, task_list.size());

        task_list = SampleData.initTestTasks();
        expected = task_list.size() + 3;
        TaskLogic.add_task(task_list, t);
        TaskLogic.add_task(task_list, t);
        TaskLogic.add_task(task_list, t);

        assertEquals(expected, task_list.size());

        //revert normal output
        System.setOut(sysout);
    }

    @Test
    public void testMakeTask() {
        //surpress output with redirector
        PrintStream sysout = System.out;
        SampleData.outputRedirector();

        String desc = "Test_Task";
        String date = "10/10/2044 0707";
        Task t = TaskLogic.make_task("todo", desc, date);

        assertTrue(t instanceof Todo);

        t = TaskLogic.make_task("deadline", desc, date);
        assertTrue(t instanceof Deadline);

        t = TaskLogic.make_task("event", desc, date);
        assertTrue(t instanceof Event);

        //revert normal output
        System.setOut(sysout);
    }

    @Test
    public void testFindTask() {
        //surpress output with redirector
        PrintStream sysout = System.out;
        ByteArrayOutputStream out = SampleData.outputRedirector();

        ArrayList<Task> task_list = SampleData.initTestTasks();


        int expected = 1;
        int actual = TaskLogic.find_task(task_list, "_5");
        assertEquals(expected, actual);

        //Output test
        String expected_output =
                "____________________________________________________________" + CRLF +
                "Here are the matching tasks in your list:" + CRLF +
                "5.[E][✘] test_5 (at: 2010-10-10 20:00)" + CRLF +
                "____________________________________________________________" + CRLF;

        assertEquals(expected_output, out.toString());

        expected = 6;
        actual = TaskLogic.find_task(task_list, "test");
        assertEquals(expected, actual);

        expected = 6;
        actual = TaskLogic.find_task(task_list, "est");
        assertEquals(expected, actual);

        expected = 0;
        actual = TaskLogic.find_task(task_list, "tst");
        assertEquals(expected, actual);

        expected = 1;
        actual = TaskLogic.find_task(task_list, "2");
        assertEquals(expected, actual);

        // random query
        Random r = new Random();
        byte[] random_bytes = new byte[20];
        r.nextBytes(random_bytes);
        String random = new String(random_bytes);
        expected = 0;
        actual = TaskLogic.find_task(task_list, random);
        System.out.println("testFindTask: Random String  : " + random);
        assertEquals(expected, actual);

        //revert normal output
        System.setOut(sysout);
    }

    @Test
    public void testReset() {
        ArrayList<Task> task_list = SampleData.initTestTasks();
        ArrayList<Task> expected_task_list = new ArrayList<Task>();

        assertTrue(!expected_task_list.equals(task_list));
        task_list = TaskLogic.reset(task_list);

        assertEquals(expected_task_list, task_list);
    }

    @Test
    public void testIntValidator() {
        String int_overflow = "2147483648";
        String int_max = "2147483647";
        String int_negative = "–1";
        Random r = new Random();
        String int_random = Integer.toString(r.nextInt(2147483647));
        byte[] random_bytes = new byte[20];
        r.nextBytes(random_bytes);
        String random = new String(random_bytes);

        System.out.println("testIntValidator: Random Integer : " + int_random);
        System.out.println("testIntValidator: Random String  : " + random);

        assertTrue(!TaskLogic.IntValidator(int_overflow));
        assertTrue(TaskLogic.IntValidator(int_max));
        assertTrue(!TaskLogic.IntValidator(int_negative));
        assertTrue(TaskLogic.IntValidator(int_random));
        assertTrue(!TaskLogic.IntValidator(random));
    }

    @Test
    public void testDateValidator() {
        String valid_date = "10/12/2040 2359";
        String valid_date_2 = "08/04/2008 1200";
        String valid_date_wrong_format = "2040/12/10 2359";
        String valid_date_wrong_format_2 = "2359 10/12/2040";
        String valid_date_wrong_format_symbol = "10-12-2040 2359";
        String valid_date_wrong_format_symbol_2 = "10\\12\\2040 2359";
        String valid_date__wrong_format_zero = "8/4/2008 1200";

        String invalid_date_wrong_day = "32/10/2040 2359";
        String invalid_date_wrong_month = "10/30/2040 2359";
        String invalid_date_wrong_min = "10/12/2040 2360";
        String invalid_date_wrong_hour = "10/12/2040 2459";
        Random r = new Random();
        byte[] random_bytes = new byte[20];
        r.nextBytes(random_bytes);
        String random = new String(random_bytes);

        System.out.println("testDateValidator: Random String  : " + random);

        assertTrue(TaskLogic.DateValidator(valid_date));
        assertTrue(TaskLogic.DateValidator(valid_date_2));
        assertTrue(!TaskLogic.DateValidator(valid_date_wrong_format));
        assertTrue(!TaskLogic.DateValidator(valid_date_wrong_format_2));
        assertTrue(!TaskLogic.DateValidator(valid_date_wrong_format_symbol));
        assertTrue(!TaskLogic.DateValidator(valid_date_wrong_format_symbol_2));
        assertTrue(!TaskLogic.DateValidator(valid_date__wrong_format_zero));
        assertTrue(!TaskLogic.DateValidator(invalid_date_wrong_day));
        assertTrue(!TaskLogic.DateValidator(invalid_date_wrong_month));
        assertTrue(!TaskLogic.DateValidator(invalid_date_wrong_min));
        assertTrue(!TaskLogic.DateValidator(invalid_date_wrong_hour));
        assertTrue(!TaskLogic.DateValidator(random));
    }

    @Test
    public void testDateMaker() {
        LocalDateTime date = TaskLogic.DateMaker("10/10/2044 1844");
        String expected_date = "2044-10-10";
        String expected_time = "18:44";

        assertTrue(date instanceof LocalDateTime);
        assertEquals(expected_date, date.toLocalDate().toString());
        assertEquals(expected_time, date.toLocalTime().toString());
    }

    @Test
    public void testPrintError() {
        //Output redirector
        PrintStream sysout = System.out;
        ByteArrayOutputStream out = SampleData.outputRedirector();

        TaskLogic.printError("!Print Test Error Message!");
        String expected_output = "!Print Test Error Message!" + CRLF;

        System.setOut(sysout);

        assertEquals(expected_output, out.toString());
    }
}
