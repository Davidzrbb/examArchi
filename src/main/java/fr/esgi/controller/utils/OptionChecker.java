package fr.esgi.controller.utils;

import org.apache.commons.cli.Option;

public class OptionChecker {
    public static boolean isInvalidNumberOfOptions(Option[] options) {
        return options.length != 1;
    }
}
