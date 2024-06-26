//package controller.validation;
//
//import controller.enums.Option;
//
//public class OptionValidation {
//    public static boolean validateCommandOption(String commandOption) {
//        if (commandOption == null || commandOption.isEmpty()){
//            return false;
//        }
//        for (Option option : Option.values()) {
//            if (option.name().equals(commandOption)) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
