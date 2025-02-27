package org.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class AikidoPracticeTrackerTest {
    private AikidoPracticeTracker tracker;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        tracker = new AikidoPracticeTracker();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testAddPracticeSession() {
        tracker.addSession("2025-02-27", 90);
        assertEquals(90, tracker.getTotalPracticeTime());
    }

    @Test
    public void testTotalPracticeTime() {
        tracker.addSession("2025-02-27", 60);
        tracker.addSession("2025-02-27", 30);
        assertEquals(90, tracker.getTotalPracticeTime());
    }

    @Test
    public void testGraduationEligibility() {
        for (int i = 0; i < 100; i++) {
            tracker.addSession("2025-02-27" + (i + 1), 60);
        }
        assertTrue(tracker.checkGraduationEligibility());
    }

    @Test
    void testAddSession() {
        tracker.addSession("2025-02-27", 60);
        assertEquals(60, tracker.getTotalPracticeTime(), "Total time should be 60 minutes after adding one session.");
    }

    @Test
    void testMultipleSessionsTotalTime() {
        tracker.addSession("2025-02-27", 60);
        tracker.addSession("2025-02-27", 30);
        assertEquals(90, tracker.getTotalPracticeTime(), "Total time should sum correctly.");
    }

    @Test
    void testGraduationEligibility_BySessionsCount() {
        for (int i = 0; i < 100; i++) {
            tracker.addSession("2025-02-27" + (i + 1), 30);
        }
        assertTrue(tracker.checkGraduationEligibility(), "Should be eligible with 100 sessions.");
    }

    @Test
    void testGraduationEligibility_ByTotalTime() {
        for (int i = 0; i < 50; i++) {
            tracker.addSession("2025-02-27" + (i + 1), 360);
        }
        assertTrue(tracker.checkGraduationEligibility(), "Should be eligible with 6 months of total practice time.");
    }

    @Test
    void testNotEligibleForGraduation() {
        tracker.addSession("2025-02-27", 50);
        assertFalse(tracker.checkGraduationEligibility(), "Should not be eligible with only 50 minutes.");
    }

    @Test
    void testEmptyTracker() {
        assertEquals(0, tracker.getTotalPracticeTime(), "Total time should be 0 with no sessions.");
        assertFalse(tracker.checkGraduationEligibility(), "Should not be eligible with no sessions.");
    }


    @Test
    void testMenuDisplay() {
        simulateUserInput("4\n");

        tracker.run();

        String output = outputStream.toString();
        assertTrue(output.contains("===== Aikido Practice Tracker ====="), "Menu should be displayed");
        assertTrue(output.contains("1. Add Practice Session"), "Menu option 1 missing");
        assertTrue(output.contains("2. View Total Practice Time"), "Menu option 2 missing");
        assertTrue(output.contains("3. Check Graduation Eligibility"), "Menu option 3 missing");
        assertTrue(output.contains("4. Exit"), "Menu option 4 missing");
    }

    @Test
    void testSimulatedAddPracticeSession() {
        simulateUserInput("1\n2025-02-27\n90\n4\n");

        tracker.run();

        String output = outputStream.toString();
        assertTrue(output.contains("Enter session date"), "Should be visible prompt to enter session date");
        assertTrue(output.contains("Enter session duration"), "Should be visible prompt to enter session duration");
        assertTrue(output.contains("Session added!"), "Should be visible confirmation that session has been added");
        assertEquals(90, tracker.getTotalPracticeTime(), "Total practice time should be updated");
    }

    @Test
    void testViewTotalPracticeTime() {
        tracker.addSession("2025-02-27", 90);
        simulateUserInput("2\n4\n");

        tracker.run();

        String output = outputStream.toString();
        assertTrue(output.contains("Total practice time: 90 minutes"), "Total practice time should be displayed");
    }

    @Test
    void testGraduationEligibility_NotEligible() {
        simulateUserInput("3\n4\n");

        tracker.run();

        String output = outputStream.toString();
        assertTrue(output.contains("Not yet eligible for Kyu graduation"), "Should indicate ineligibility");
    }

    @Test
    void testGraduationEligibility_Eligible() {
        for (int i = 0; i < 100; i++) {
            tracker.addSession("2025-02-27" + (i + 1), 30);
        }
        simulateUserInput("3\n4\n");

        tracker.run();

        String output = outputStream.toString();
        assertTrue(output.contains("You are eligible for Kyu graduation!"), "Should indicate eligibility");
    }

    @Test
    void testInvalidOptionHandling() {
        simulateUserInput("9\n4\n");

        tracker.run();

        String output = outputStream.toString();
        assertTrue(output.contains("Invalid option. Try again."), "Should handle invalid input");
    }

    @Test
    void testExitProgram() {
        simulateUserInput("4\n");

        tracker.run();

        String output = outputStream.toString();
        assertTrue(output.contains("Exiting..."), "Should confirm exit");
    }

    private void simulateUserInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
}