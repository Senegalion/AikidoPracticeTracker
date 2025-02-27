package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AikidoPracticeTracker {
    private final List<PracticeSession> practiceSessions;

    public static void main(String[] args) {
        AikidoPracticeTracker tracker = new AikidoPracticeTracker();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== Aikido Practice Tracker =====");
            System.out.println("1. Add Practice Session");
            System.out.println("2. View Total Practice Time");
            System.out.println("3. Check Graduation Eligibility");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter session date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter session duration (minutes): ");
                    int duration = scanner.nextInt();
                    tracker.addSession(date, duration);
                    System.out.println("Session added!");
                    break;
                case 2:
                    System.out.println("Total practice time: " + tracker.getTotalPracticeTime() + " minutes");
                    break;
                case 3:
                    if (tracker.checkGraduationEligibility()) {
                        System.out.println("You are eligible for Kyu graduation!");
                    } else {
                        System.out.println("Not yet eligible for Kyu graduation.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public AikidoPracticeTracker() {
        this.practiceSessions = new ArrayList<>();
    }

    public void addSession(String date, int duration) {
        practiceSessions.add(new PracticeSession(date, duration));
    }

    public int getTotalPracticeTime() {
        return practiceSessions.stream().mapToInt(PracticeSession::getDuration).sum();
    }

    public boolean checkGraduationEligibility() {
        return practiceSessions.size() >= 100 || getTotalPracticeTime() >= 6 * 30 * 60;
    }
}
