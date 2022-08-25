package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.EmptyCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;

public class Parser {

    public Parser() {

    }

    public static Command parse(String fullCommand) {
        String[] parts = fullCommand.split(" ", 0);
        String command = parts[0];
        String mainCommand = "";
        if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            mainCommand = "Add";
        } else if (command.equals("delete")) {
            mainCommand = "Delete";
        } else if (command.equals("bye")) {
            mainCommand = "Exit";
        } else if (command.equals("mark") || command.equals("unmark")) {
            mainCommand = "Mark";
        } else if (command.equals("list")) {
            mainCommand = "List";
        }

        switch (mainCommand) {
        case "Delete":
            return new DeleteCommand(Integer.parseInt(parts[1]) - 1);
        case "Exit":
            return new ExitCommand();
        case "Add":
            return new AddCommand(fullCommand);
        case "Mark":
            return new MarkCommand(command, Integer.parseInt(parts[1]) - 1);
        case "List":
            return new ListCommand();
        default:
            return new EmptyCommand();
        }
    }
}
