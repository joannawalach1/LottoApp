package com.javaready.LottoApp.domain.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryNumberReceiverRepositoryTestImpl implements NumberReceiverRepository {
    Map<String, Ticket> inMemoryDataBase = new ConcurrentHashMap<>();
    @Override
    public Ticket save(Ticket ticket) {
        inMemoryDataBase.put(ticket.ticketId(), ticket);
        return ticket;
    }

    @Override
    public List<Ticket> findAllTicketsByDate(LocalDateTime drawDate) {
        return inMemoryDataBase.values()
                .stream()
                .filter(ticket -> ticket.drawData().equals(drawDate))
                .collect( Collectors.toList());
    }
}
