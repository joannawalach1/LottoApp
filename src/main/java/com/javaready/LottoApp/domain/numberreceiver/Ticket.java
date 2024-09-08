package com.javaready.LottoApp.domain.numberreceiver;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.Set;
@Builder
public record Ticket(String ticketId, LocalDateTime drawData, Set<Integer> numbersFromUser) {
}
