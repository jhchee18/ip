package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyTaskException;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;


public class AddCommand extends Command {
    public static final boolean IS_EXIT = false;
    public final String fullCommand;

    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String parts[] = fullCommand.split(" ", 0);
            String command = parts[0];
            if (parts.length == 1) {
                throw new EmptyTaskException();
            }
            String taskName = "";
            String dateString = "";
            String timeString = "";
            for (int i = 1; i < parts.length; i++) {
                if (parts[i].charAt(0) != '/') {
                    taskName += parts[i] + " ";
                } else {
                    dateString = parts[i + 1];
                    timeString = parts[i + 2];
                    break;
                }
            }

            LocalDate date = null;
            if (!dateString.equals("")) {
                date = validateDateString(dateString);
            }

            LocalTime time = null;
            if (!timeString.equals("")) {
                time = validateTimeString(timeString);
            }

            Task task = new Task("DummyTask", date, time);
            if (command.equals("todo")) {
                task = new ToDo(taskName, date, time);
            } else if (command.equals("deadline")) {
                task = new Deadline(taskName, date, time);
            } else if (command.equals("event")) {
                task = new Event(taskName, date, time);
            }
            taskList.getList().add(task);
            System.out.println("Got it. I've added this task:\n" + ui.beautyWrapTask(task) + "\nNow you have " + taskList.getList().size() + " tasks in the list.\n");

            /* * *
             *  Write file in duke.txt
             * * */
            String list = "";
            for (Task t : taskList.getList()) {
                list += t.toString();
            }
            storage.write(list);
        }
        catch (EmptyTaskException ex){
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        catch (DateTimeParseException ex) {
            System.out.println("Invalid date & time format. Please follow the format of date as \"YYYY-MM-DD\" and time as \"HHMM\".");

        }
    }

    public boolean isExit() {
        return this.IS_EXIT;
    }

    public LocalTime validateTimeString(String timeString) {
        //desired date format "1800"
        String validatedTimeString = timeString.substring(0,2) + ":" + timeString.substring(2,4) + ":" + "00";
        LocalTime time = LocalTime.parse(validatedTimeString);
        return time;
    }

    public LocalDate validateDateString(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        return date;
    }
}
