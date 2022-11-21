package com.slobx.cra.infrastructure.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Message code constants used for exception messages.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageCodeConstants {
    public static final String RESERVATION_START_OVERDUE = "The reservation can be taken only up to 24 hours ahead";
    public static final String RESERVATION_START_TIME_AFTER_END_TIME = "The reservation start time cannot be after end time";
    public static final String RESERVATION_DURATION_OVERDUE = "The reservation cannot last longer then two hours";
    public static final String CAR_NOT_AVAILABLE = "The car is currently unavailable";
}
