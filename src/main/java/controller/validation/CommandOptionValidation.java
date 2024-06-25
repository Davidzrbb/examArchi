package controller.validation;
import controller.enums.CommandOption;

public class CommandOptionValidation {
    public static boolean validateCommandOption(String commandOption) {
        if (commandOption == null || commandOption.isEmpty()){
            return false;
        }
        for (CommandOption option : CommandOption.values()) {
            if (option.name().equals(commandOption)) {
                return true;
            }
        }
        return false;
    }
}
