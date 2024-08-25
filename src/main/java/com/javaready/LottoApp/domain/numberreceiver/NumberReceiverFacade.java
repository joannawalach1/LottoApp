package com.javaready.LottoApp.domain.numberreceiver;

import java.util.List;
import java.util.Set;

public class NumberReceiverFacade {

    public String inputNumbers(Set<Integer> numbers) {
        List<Integer> filteredNumbers = filteredNumbers(numbers);
        if (areAllNumbersInRange(numbers)) {
            return "success";
        }
        return "fail";
    }

    private static boolean areAllNumbersInRange(Set<Integer> numbers) {
        return numbers.size() == 6;
    }

    private List<Integer> filteredNumbers(Set<Integer> numbers) {
        return numbers.stream()
               .filter(number -> number >= 1)
               .filter(number -> number <= 99)
               .toList();
    }
}
