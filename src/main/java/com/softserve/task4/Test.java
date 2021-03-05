package com.softserve.task4;

import com.softserve.task4.service.ConsoleService;
import com.softserve.task4.service.HumanService;
import com.softserve.task4.service.ITesterService;
import com.softserve.task4.service.WindowService;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        HumanService humanService = new HumanService();

        System.out.println("Choose how to open the app: window or console");
        Scanner scanner = new Scanner(System.in);
        String data = scanner.next();
        if (data.equalsIgnoreCase("window")) {
            ITesterService tester = new WindowService(humanService);
            tester.test();
        } else {
            ITesterService tester = new ConsoleService(humanService);
            tester.test();
        }
    }
}