package com.softserve.task4;

import com.softserve.task4.service.ConsoleService;
import com.softserve.task4.service.HumanService;
import com.softserve.task4.service.ITesterService;
import com.softserve.task4.service.WindowService;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        HumanService humanService = new HumanService();

        System.out.println("Choose how to open the app:\n1 - window\n2 - console");
        while (true){
            Scanner scanner = new Scanner(System.in);
            String data = scanner.next();
            switch (data) {
                case "1":
                    ITesterService windowTester = new WindowService(humanService);
                    windowTester.test();
                    return;
                case "2":
                    ITesterService consoleTester = new ConsoleService(humanService);
                    consoleTester.test();
                    return;
                default:
                    System.out.println("Choose how to open the app:\n1 - window\n2 - console");
            }
        }
    }
}