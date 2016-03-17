package com.github.vdemeester.macchiato.compose.project;

/**
 * Down options value object
 */
public class DownOptions {

    private final StopOptions stopOptions;

    private final RemoveOptions removeOptions;

    private final RemoveImage removeImages;

    private final boolean removeOrphans;

    public DownOptions(StopOptions stopOptions, RemoveOptions removeOptions,
                       RemoveImage removeImages, boolean removeOrphans) {
        this.stopOptions = stopOptions;
        this.removeOptions = removeOptions;
        this.removeImages = removeImages;
        this.removeOrphans = removeOrphans;
    }

    public StopOptions stopOptions() {
        return this.stopOptions;
    }

    public RemoveOptions removeOptions() {
        return this.removeOptions;
    }

    /**
     * Remove images, type may be one of: 'all' to remove
     * all images, or 'local' to remove only images that
     * don't have an custom name set by the `image` field
     */
    public RemoveImage removeImages() {
        return this.removeImages;
    }

    /**
     * Remove containers for services not defined in the
     * Compose file
     */
    public boolean removeOrphans() {
        return this.removeOrphans;
    }
}
