package com.github.vdemeester.macchiato.compose.project;

/**
 * Stop options value object
 */
public class StopOptions {
    private static final int DEFAULT_TIMEOUT = 10;

    private final int timeout;

    public StopOptions(int timeout) {
        this.timeout = timeout;
    }

    public StopOptions() {
        this.timeout = DEFAULT_TIMEOUT;
    }

    /**
     * Specify a shutdown timeout in seconds (default: 10).
     */
    public int timeout() {
        return this.timeout;
    }
}
