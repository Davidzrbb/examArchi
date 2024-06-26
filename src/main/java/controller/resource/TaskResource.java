package controller.resource;
import controller.utils.OptionCreator;
import org.apache.commons.cli.*;

public class TaskResource {
    public static void dispatch(String[] args) {
        var loadOptions = OptionCreator.createOptions();
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(loadOptions, args);
            var options = cmd.getOptions();
            System.out.println("Options: " + options.length);
            if (options.length == 0) {
                System.err.println("No command provided.");
                System.exit(1);
            }
            // Continue with processing...

        } catch (ParseException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
