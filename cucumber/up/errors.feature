Feature: Project up errors

  This feature documents the error cases for up.

  Up uses create to create the container, so the errors specified in create/errors
  are not there (it would be too much redundant).

  Scenario: Up with an invalid command.
    Given the following compose file
    """
    first:
      image: busybox
      command: /a/command/that/does/not/exists
    """
    When I run up on a project
    Then I should get an error