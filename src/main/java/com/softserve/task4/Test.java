package com.softserve.task4;

import com.softserve.task4.models.Man;
import com.softserve.task4.models.Woman;
import com.softserve.task4.service.HumanService;

public class Test {
    public static void main(String[] args) {
        Man man = new Man("oleg", "IvaNoV", 180, 80);
        Man man2 = new Man("Alex", "lee", 190, 85);
        Woman woman = new Woman("Elena", "Ivanova", 160, 55);
        Woman woman2 = new Woman("Katrine", "Virk", 162, 50);
        HumanService.compatibilityTest(man, man2);
        HumanService.compatibilityTest(woman, woman2);
        HumanService.compatibilityTest(man, woman);
        HumanService.compatibilityTest(man2, woman);
    }
}