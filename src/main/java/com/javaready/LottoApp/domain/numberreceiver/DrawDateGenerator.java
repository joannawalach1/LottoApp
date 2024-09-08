package com.javaready.LottoApp.domain.numberreceiver;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

class DrawDateGenerator {
    private static final LocalTime DRAW_TIME = LocalTime.of(12, 0, 0);
    private static final TemporalAdjuster NEXT_DRAW_DAY = TemporalAdjusters.next(DayOfWeek.SATURDAY);
    private final Clock clock;

    public DrawDateGenerator(Clock clock) {
        this.clock = clock;
    }

    public LocalDateTime generate() {
        LocalDateTime currentTime = LocalDateTime.now(clock);
        if (isSaturdayBeforeMoon(currentTime)) {
            return LocalDateTime.of(currentTime.toLocalDate(), DRAW_TIME);
        }
        LocalDateTime drawDate = currentTime.with(NEXT_DRAW_DAY);
        return LocalDateTime.of(drawDate.toLocalDate(), DRAW_TIME);
    }

    private boolean isSaturdayBeforeMoon(LocalDateTime currentTime) {
        return currentTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) && currentTime.toLocalTime().isBefore(DRAW_TIME);
    }
}
