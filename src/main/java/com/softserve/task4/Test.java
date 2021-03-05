package com.softserve.task4;

import com.softserve.task4.service.ConsoleService;
import com.softserve.task4.service.HumanService;
import com.softserve.task4.service.ITesterService;
import com.softserve.task4.service.WindowService;

public class Test {
    public static void main(String[] args) {
        HumanService humanService = new HumanService();
//        ITesterService tester = new ConsoleService(humanService);
        ITesterService tester = new WindowService(humanService);
        tester.test();
    }
}