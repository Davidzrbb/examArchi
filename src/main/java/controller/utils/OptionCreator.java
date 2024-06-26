package controller.utils;

import org.apache.commons.cli.Options;

public class OptionCreator {
    public static Options createOptions() {
        Options options = new Options();
        options.addOption("a", "add", true, "Add a task");
        options.addOption("d", "delete", true, "Delete a task");
        options.addOption("l", "list", true, "List all tasks");
        options.addOption("m", "done", true, "Mark a task as done");
        return options;
    }
}
