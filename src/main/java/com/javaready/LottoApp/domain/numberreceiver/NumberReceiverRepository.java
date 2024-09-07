package com.javaready.LottoApp.domain.numberreceiver;


import java.time.LocalDateTime;
import java.util.List;

public interface NumberReceiverRepository {
    Ticket save(Ticket ticket);

    List<Ticket> findAllTicketsByDate(LocalDateTime drawDate);
}
