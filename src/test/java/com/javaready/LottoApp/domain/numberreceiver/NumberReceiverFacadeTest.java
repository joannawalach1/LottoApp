package com.javaready.LottoApp.domain.numberreceiver;


import com.javaready.LottoApp.domain.numberreceiver.dto.InputNumberResultDto;
import com.javaready.LottoApp.domain.numberreceiver.dto.TicketDto;
import org.junit.jupiter.api.Test;
import pl.lotto.domain.AdjustableClock;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberReceiverFacadeTest {

    AdjustableClock clock = new AdjustableClock(LocalDateTime.of(2024, 9, 3, 11, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
            new NumberReceiverValidator(),
            new InMemoryNumberReceiverRepositoryTestImpl(),
            clock
    );

    @Test
    void should_return_success_when_user_save_six_numbers_to_database() {
        // Given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);

        // When
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        LocalDateTime drawDate = LocalDateTime.of(2024, 9, 3, 13, 0, 0);
        List<TicketDto> ticketDtos = numberReceiverFacade.userNumbers(drawDate);

        assertThat(ticketDtos).contains(
                TicketDto.builder()
                        .ticketId(result.ticketId())
                        .drawDate(result.drawDate())
                        .numbersFromUser(result.numbersFromUser())
                        .build()
        );
    }

    @Test
    void should_return_failed_when_user_gave_less_than_six_numbers() {
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5);
        //when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("fail");
    }

    @Test
    void should_return_failed_when_user_gave_more_than_six_numbers() {
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6, 7);
        //when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("fail");
    }

    @Test
    void should_return_failed_when_user_gave_out_of_range_number() {
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3000, 4, 5, 6);
        //when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message()).isEqualTo("fail");
    }
}