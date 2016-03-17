package com.github.vdemeester.macchiato.compose.project;

/**
 * Build options value object
 */
public class BuildOptions {

    private final boolean forceRemove;

    private final boolean noCache;

    private final boolean pull;

    public BuildOptions(boolean forceRemove, boolean noCache, boolean pull) {
        this.forceRemove = forceRemove;
        this.noCache = noCache;
        this.pull = pull;
    }

    /**
     * Always remove intermediate containers.
     */
    public boolean forceRemove() {
        return this.forceRemove;
    }

    /**
     * Do not use cache when building the image.
     */
    public boolean noCache() {
        return this.noCache;
    }

    /**
     * Always attempt to pull a newer version of the image.
     */
    public boolean pull() {
        return this.pull;
    }
}
