package org.example;

public class PracticeSession {
    private final String date;
    private final int duration;

    public PracticeSession(String date, int duration) {
        this.date = date;
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}
