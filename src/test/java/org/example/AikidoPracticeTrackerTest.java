package org.example;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AikidoPracticeTrackerTest {
    @Test
    public void testAddPracticeSession() {
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        tracker.addSession("2024-02-01", 90);
        assertEquals(90, tracker.getTotalPracticeTime());
    }

    @Test
    public void testTotalPracticeTime() {
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        tracker.addSession("2024-02-01", 60);
        tracker.addSession("2024-02-02", 30);
        assertEquals(90, tracker.getTotalPracticeTime());
    }

    @Test
    public void testGraduationEligibility() {
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        for (int i = 0; i < 100; i++) {
            tracker.addSession("2024-02-" + (i + 1), 60);
        }
        assertTrue(tracker.checkGraduationEligibility());
    }

    @Test
    public void testNotEligibleForGraduation() {
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        tracker.addSession("2024-02-01", 50);
        assertFalse(tracker.checkGraduationEligibility());
    }
}