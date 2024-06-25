package controller.resource;
import org.apache.commons.cli.*;

public class TaskResource {
    public static Options createOptions() {
        Options options = new Options();
        options.addOption("add", "verbose", false, "Enable verbose mode");
        options.addOption("delete", "input", true, "Specify input file");
        options.addOption("list", "output", true, "Specify output file");
        return options;
    }

    public static void dispatch(String[] args, Options options) {

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
