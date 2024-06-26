package controller.utils;

import org.apache.commons.cli.Options;

public class OptionCreator {
    public static Options createOptions() {
        Options options = new Options();

//        options.addOption("add", "verbose", false, "Enable verbose mode");
//        options.addOption("delete", "input", true, "Specify input file");
//        options.addOption("list", "output", true, "Specify output file");
        return options;
    }
}
