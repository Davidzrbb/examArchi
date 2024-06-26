package controller.model;

import controller.enums.Option;

public class CommandOption {
    private final Option option;
    private final String longOption;
    private final boolean hasArg;
    private final String description;

    CommandOption(Option option, String longOption, boolean hasArg, String description) {
        this.option = option;
        this.longOption = longOption;
        this.hasArg = hasArg;
        this.description = description;
    }

    public Option getOption() {
        return option;
    }

    public String getLongOption() {
        return longOption;
    }

    public boolean hasArg() {
        return hasArg;
    }

    public String getDescription() {
        return description;
    }
}
