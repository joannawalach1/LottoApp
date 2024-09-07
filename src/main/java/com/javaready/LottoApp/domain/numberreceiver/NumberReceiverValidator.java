package com.javaready.LottoApp.domain.numberreceiver;

import java.util.Set;

class NumberReceiverValidator {

    private static final Integer MIN_NUMBER_FROM_USER = 1;
    private static final Integer MAX_NUMBER_FROM_USER = 99;
    private static final long MAX_AMOUNT_OF_NUMBERS = 6;


    boolean filteredNumbers(Set<Integer> numbers) {
       return numbers.stream()
               .filter(number -> number >= MIN_NUMBER_FROM_USER)
               .filter(number -> number <= MAX_NUMBER_FROM_USER)
               .count() == MAX_AMOUNT_OF_NUMBERS;
    }
}
