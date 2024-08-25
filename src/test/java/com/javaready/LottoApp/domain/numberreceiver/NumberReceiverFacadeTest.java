package com.javaready.LottoApp.domain.numberreceiver;


import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberReceiverFacadeTest {


    @Test
    void should_return_success_when_user_gave_six_numbers() {
        //given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        //when
        String numbers = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6));
        //then
        assertThat(numbers).isEqualTo("success");
    }

    @Test
    void should_return_failed_when_user_gave_less_than_six_numbers() {
        //given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        //when
        String numbers = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5));
        //then
        assertThat(numbers).isEqualTo("fail");
    }

    @Test
    void should_return_failed_when_user_gave_more_than_six_numbers() {
        //given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        //when
        String numbers = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6, 7));
        //then
        assertThat(numbers).isEqualTo("fail");
    }

    @Test
    void should_return_failed_when_user_gave_out_of_range_number() {
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        //when
        String numbers = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3000, 4, 5, 6, 7));
        //then
        assertThat(numbers).isEqualTo("fail");
    }
}