package com.javaready.LottoApp.domain.numberreceiver;

import java.util.UUID;

public class HashGenerator implements HashGenerable {
    @Override
    public String generateHash() {
        return UUID.randomUUID().toString();
    }
}