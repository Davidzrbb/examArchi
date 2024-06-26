package fr.esgi.controller.utils;

public class ArgumentChecker {

    public static boolean isGotOneArguments(String[] args) {
        return args.length == 1;
    }

    public static boolean isGotZeroArguments(String[] args) {
        return args.length == 0;
    }

    public static boolean isInvalidNumberOfArguments(String[] args) {
        return  args.length > 1;
    }

}
