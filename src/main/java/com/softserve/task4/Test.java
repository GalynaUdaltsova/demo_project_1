package com.softserve.task4;

import com.softserve.task4.service.ConsoleService;
import com.softserve.task4.service.HumanService;

public class Test {
    public static void main(String[] args) {
        HumanService humanService = new HumanService();
        ConsoleService tester = new ConsoleService(humanService);
        tester.test();
    }
}
