package br.capitolio.engine.core.time;

public class Clock {

    public static final long NANOSECOND = 1000000000L;
    private long mark;

    public Clock() {
        mark();
    }

    public void mark() {
        mark = System.nanoTime();
    }

    public long delta() {
        return System.nanoTime() - mark;
    }
}
