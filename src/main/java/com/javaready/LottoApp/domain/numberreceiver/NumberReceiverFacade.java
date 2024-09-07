package com.javaready.LottoApp.domain.numberreceiver;

import com.javaready.LottoApp.domain.numberreceiver.dto.InputNumberResultDto;
import com.javaready.LottoApp.domain.numberreceiver.dto.TicketDto;
import lombok.AllArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
public class NumberReceiverFacade {

    private final NumberReceiverValidator numberReceiverValidator;
    private final NumberReceiverRepository numberReceiverRepository;
    private final Clock clock;

    public InputNumberResultDto inputNumbers(Set<Integer> numbersFromUser) {
        if (!numberReceiverValidator.filteredNumbers(numbersFromUser)) {
            return InputNumberResultDto.builder()
                    .message("fail")
                    .build();
        }

        String ticketId = UUID.randomUUID().toString();
        LocalDateTime drawData = LocalDateTime.now(clock);
        Ticket ticket = new Ticket(ticketId, drawData, numbersFromUser);
        Ticket savedTicket = numberReceiverRepository.save(ticket);

        if (savedTicket != null) {
            return InputNumberResultDto.builder()
                    .message("Success")
                    .ticketId(savedTicket.ticketId())
                    .drawDate(savedTicket.drawData())
                    .numbersFromUser(savedTicket.numbersFromUser())
                    .build();
        } else {
            return InputNumberResultDto.builder()
                    .message("Fail")
                    .build();
        }
    }

    public List<TicketDto> userNumbers(LocalDateTime drawDate) {
        List<Ticket> allTicketsByDate = numberReceiverRepository.findAllTicketsByDate(drawDate);
        return allTicketsByDate.stream()
                .map(TicketMapper::mapFromTicket)
                .toList();
    }
}
