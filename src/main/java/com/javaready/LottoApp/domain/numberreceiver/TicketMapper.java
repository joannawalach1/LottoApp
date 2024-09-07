package com.javaready.LottoApp.domain.numberreceiver;

import com.javaready.LottoApp.domain.numberreceiver.dto.TicketDto;

import java.util.HashSet;

public class TicketMapper {


    public static TicketDto mapFromTicket(Ticket ticket) {
        return TicketDto.builder()
                .ticketId(ticket.ticketId())
                .drawDate(ticket.drawData())
                .numbersFromUser(ticket.numbersFromUser())
                .build();
    }
}
