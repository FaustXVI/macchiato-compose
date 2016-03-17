package com.github.vdemeester.macchiato.compose.project;

/**
 * Remove options value objecs
 */
public class RemoveOptions {

    private final boolean volumes;

    public RemoveOptions(boolean volumes) {
        this.volumes = volumes;
    }

    /**
     * Remove volumes associated with containers.
     */
    public boolean volumes() {
        return this.volumes;
    }
}
