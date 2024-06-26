package fr.esgi.controller.utils;

public class ArgumentChecker {

    public static boolean isGotOneValue(String[] args) {
        return args.length == 1;
    }

    public static boolean isInvalidNumberOfArguments(String[] args) {
        return  args.length > 1;
    }

}
