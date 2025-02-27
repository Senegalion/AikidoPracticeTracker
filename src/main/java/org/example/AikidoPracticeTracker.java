package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AikidoPracticeTracker {
    private final List<PracticeSession> practiceSessions;

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

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();

            while (!scanner.hasNextInt());

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addPracticeSession(scanner);
                    break;
                case 2:
                    viewTotalPracticeTime();
                    break;
                case 3:
                    checkGraduationEligibilityChoice();
                    break;
                case 4:
                    closeProgram(scanner);
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void closeProgram(Scanner scanner) {
        System.out.println("Exiting...");
        scanner.close();
    }

    private void checkGraduationEligibilityChoice() {
        if (this.checkGraduationEligibility()) {
            System.out.println("You are eligible for Kyu graduation!");
        } else {
            System.out.println("Not yet eligible for Kyu graduation.");
        }
    }

    private void viewTotalPracticeTime() {
        System.out.println("Total practice time: " + this.getTotalPracticeTime() + " minutes");
    }

    private void addPracticeSession(Scanner scanner) {
        System.out.print("Enter session date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter session duration (minutes): ");
        int duration = scanner.nextInt();
        this.addSession(date, duration);
        System.out.println("Session added!");
    }

    private void displayMenu() {
        System.out.println("===== Aikido Practice Tracker =====");
        System.out.println("1. Add Practice Session");
        System.out.println("2. View Total Practice Time");
        System.out.println("3. Check Graduation Eligibility");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }
}
