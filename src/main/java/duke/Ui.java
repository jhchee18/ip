package duke;

import java.util.Scanner;

import duke.task.Task;

public class Ui {
    public static final String LOADING_ERROR_MESSAGE = "Task list is empty.";
    public static final String GREETING_MESSAGE = "Hello! I'm RyanBot ☺\nWhat can I do for you?\n";
    public static final String GOODBYE_MESSAGE = "Bye. Please don't leave me :( Hope to see you again soon!";
    public Ui() {

    }

    public void showLoadingError() {
        System.out.println(LOADING_ERROR_MESSAGE);
    }

    public void showGreetingMessage() {
        System.out.println(GREETING_MESSAGE);
    }

    public void showGoodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        return s;
    }

    public String beautyWrapTask(Task task) {
        String taskType = task.getTaskType();
        String preposition = "";
        if (task.getTime() != null && task.getDate() != null) {
            if (taskType == "E") {
                preposition = "(at: ";
            } else {
                preposition = "(by: ";
            }
        }
        return "[" + task.getTaskType() + "][" + (task.isMarked() ? "X" : " ") + "] "
                + task.getTaskName() + preposition + task.getOutputDateAndTime();
    }

    public void showError(String errorMessage) {
        System.out.println("Error message: " + errorMessage);
    }

    public void printList(TaskList taskList) {
        String listOutput = "Here are the tasks in your list:\n";
        int index = 1;
        for (Task t : taskList.getList()) {
            listOutput += index + "." + this.beautyWrapTask(t) + "\n";
            index++;
        }
        System.out.println(listOutput);
    }
}
