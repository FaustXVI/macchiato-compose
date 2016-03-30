Feature: Project stop timeout

  It is possible to specify a timeout (in seconds) when stopping a project
  or a service.

  The default value should be the same as docker.

  We can measure the timeout if we use a process that do not take care of signals,
  which is the case with a dumb sh and a long sleep in the container.

  Scenario: Stop with a single service, with the default timeout

    Given the following compose file
    """
    first:
      image: busybox
      command: sh -c "sleep 2m"
    """
    And the project is started
    When I run stop on the project
    Then the stop should take 10 second

  Scenario: Stop with a single service, with a different timeout

  If we run stop on a completely new project that have related containers running,
  we should stop them.

    Given the following compose file
    """
    first:
      image: busybox
      command: sh -c "sleep 2m"
    """
    And the project is started
    When I run stop on the project with a 3 second timeout
    Then the stop should take 10 second