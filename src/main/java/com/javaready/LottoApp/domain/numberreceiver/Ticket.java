package com.javaready.LottoApp.domain.numberreceiver;

import java.time.LocalDateTime;
import java.util.Set;

public record Ticket(String ticketId, LocalDateTime drawData, Set<Integer> numbersFromUser) {
}
