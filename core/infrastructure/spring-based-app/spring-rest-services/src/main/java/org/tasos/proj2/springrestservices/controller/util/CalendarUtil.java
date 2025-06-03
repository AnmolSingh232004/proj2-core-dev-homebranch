package org.tasos.proj2.springrestservices.controller.util;

public class CalendarUtil {

    public static final String colorGreen = "#228B22";
    public static final String colorBrown = "#964B00";
    public static final String colorBlue = "#0000FF";

    public static String getColorForPillPerActType(String actType) {
        switch(actType) {
        case "GYM":
            return colorGreen;
        case "HOUSE":
            return colorBrown;
        case "CAR":
            return colorBlue;
        default:
            return "#808080";
        }
    }

}
