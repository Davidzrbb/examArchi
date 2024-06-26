package controller.resource;
import controller.utils.OptionCreator;
import org.apache.commons.cli.*;

public class TaskResource {
    public static void dispatch(String[] args) {
        var options = OptionCreator.createOptions();
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            boolean verbose = cmd.hasOption("verbose");
            String inputFile = cmd.getOptionValue("input");

            // Continue with processing...

        } catch (ParseException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
