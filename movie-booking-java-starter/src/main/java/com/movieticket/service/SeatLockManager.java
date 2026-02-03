package com.movieticket.service;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple in-memory seat lock manager. For prod, move to Redis with TTLs.
 */
@Component
public class SeatLockManager {
    private static final long LOCK_MINUTES = 5;

    // showId -> seat -> lockUntil
    private final Map<Long, Map<String, Instant>> locks = new ConcurrentHashMap<>();

    public synchronized boolean tryLock(Long showId, Collection<String> seats) {
        cleanupExpired(showId);
        Map<String, Instant> m = locks.computeIfAbsent(showId, k -> new ConcurrentHashMap<>());
        // if any seat already locked and not expired -> reject
        Instant now = Instant.now();
        for (String s : seats) {
            Instant until = m.get(s);
            if (until != null && until.isAfter(now)) {
                return false;
            }
        }
        // lock all
        Instant until = now.plus(LOCK_MINUTES, ChronoUnit.MINUTES);
        for (String s : seats) {
            m.put(s, until);
        }
        return true;
    }

    public synchronized void release(Long showId, Collection<String> seats) {
        Map<String, Instant> m = locks.get(showId);
        if (m == null) return;
        for (String s : seats) m.remove(s);
    }

    public synchronized void cleanupExpired(Long showId) {
        Map<String, Instant> m = locks.get(showId);
        if (m == null) return;
        Instant now = Instant.now();
        m.entrySet().removeIf(e -> e.getValue().isBefore(now));
    }
}