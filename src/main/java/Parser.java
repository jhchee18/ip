public class Parser {

    public Parser() {

    }

    public static Command parse(String fullCommand) {
        String parts[] = fullCommand.split(" ", 0);
        String command = parts[0];
        String mainCommand = "";
        if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            mainCommand = "Add";
        }
        else if (command.equals("delete")) {
            mainCommand = "Delete";
        }
        else if (command.equals("bye")) {
            mainCommand = "Exit";
        }
        else if (command.equals("mark") || command.equals("unmark")) {
            mainCommand = "Mark";
        }
        else if (command.equals("list")) {
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
            case "":
                return new EmptyCommand();
        }
        return new ExitCommand(); //never reach this line (dummy Obj)
    }
}
