package api.tests;

import static api.baseApiMethods.UserApi.setItemsPerPageApi;
import static flow.PublicEnums.ItemsPerPage._All;


public class test2 {
    public static void main(String[] args) {
        String ANSI_RED    = "\u001B[31m";
        String ANSI_BOLD   = "\u001B[1m";
        String ANSI_GREEN   = "\u001b[32m";
        String ANSI_RESET  = "\u001B[0m";

        System.out.println(ANSI_GREEN+ANSI_BOLD+"PASS!"+ANSI_RESET);
        System.out.println("asd");
        System.out.println("asd");
        System.out.println(ANSI_RED+ANSI_BOLD+"FAILED!"+ANSI_RESET);
        System.out.println("asd");
        System.out.println("asd");

    }
}
