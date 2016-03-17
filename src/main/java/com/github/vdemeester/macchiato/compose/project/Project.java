package com.github.vdemeester.macchiato.compose.project;

import java.util.Map;

/**
 * Interface that defines the project behavior.
 * <p>
 * FIXME: To, somehow add : config, events, exec, logs, port, ps, run
 */
public interface Project {

    /**
     * Creates the different elements of the project :
     * <ul>
     * <li>services and containers</li>
     * <li>networks</li>
     * <li>volumes</li>
     * </ul>
     *
     * @param options
     * @param services
     */
    void create(CreateOptions options, String... services);

    /**
     * Starts specified services if they are already created.
     *
     * @param services
     */
    void start(String... services);

    /**
     * Creates and starts specified services.
     *
     * @param options
     * @param services
     */
    void up(CreateOptions options, String... services);
    
    /**
     * Stops specified services if the are running.
     *
     * @param options
     * @param services
     */
    void stop(StopOptions options, String... services);

    /**
     * Kills specified services.
     *
     * @param options
     * @param services
     */
    void kill(KillOptions options, String... services);

    /**
     * Removes specified services.
     *
     * @param options
     * @param services
     */
    void remove(RemoveOptions options, String... services);

    /**
     * Stops and removes specified services.
     *
     * @param options
     * @param services
     */
    void down(DownOptions options, String... services);

    /**
     * Restarts (stops and starts) the specified services.
     *
     * @param options
     * @param services
     */
    void restart(StopOptions options, String... services);

    /**
     * Build any of the specified services that can be built.
     *
     * @param options
     * @param services
     */
    void build(BuildOptions options, String... services);

    /**
     * Pull any of the specified services that can be pulled.
     *
     * @param options
     * @param services
     */
    void pull(PullOptions options, String... services);

    /**
     * Pauses the specified services.
     *
     * @param services
     */
    void pause(String... services);

    /**
     * Unpauses the specified services.
     *
     * @param services
     */
    void unpause(String... services);

    /**
     * Scale the specified services using the given numbers.
     *
     * @param numberOfContainerByService
     */
    void scale(Map<String, Integer> numberOfContainerByService);
}
