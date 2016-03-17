package com.github.vdemeester.macchiato.compose.project;

/**
 * Creation value object
 */
public class CreateOptions {

    private final boolean forceRecreate;

    private final boolean noRecreate;

    private final boolean build;

    private final boolean noBuild;

    public CreateOptions(boolean forceRecreate, boolean noRecreate,
                         boolean build, boolean noBuild) {
        this.forceRecreate = forceRecreate;
        this.noRecreate = noRecreate;
        this.build = build;
        this.noBuild = noBuild;
    }

    /**
     * Recreate containers even if their configuration and
     * image haven't changed. Incompatible with --no-recreate.
     */
    public boolean forceRecreate() {
        return this.forceRecreate;
    }

    /**
     * If containers already exist, don't recreate them.
     * Incompatible with --force-recreate.
     */
    public boolean noRecreate() {
        return this.noRecreate;
    }

    /**
     * Don't build an image, even if it's missing.
     */
    public boolean build() {
        return this.build;
    }

    /**
     * Build images before creating containers.
     */
    public boolean noBuild() {
        return this.noBuild;
    }
}
