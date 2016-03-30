Feature: Project create errors

  This feature documents the error cases for create.

  Scenario: Create on an empty file
    Given an empty compose file
    When I run create on a project
    Then I should get an error

  Scenario: Create with an empty image name
    Given the following compose file
    """
    first:
      image:
      command: top
    """
    When I run create on a project
    Then I should get an error

  Scenario: Create with an invalid image name
    Given the following compose file
    """
    first:
      image: :justtag
      command: top
    """
    When I run create on a project
    Then I should get an error

  Scenario: Create with an inexistent image
    Given the following compose file
    """
    first:
      image: vdemeester/animagethatshouldnotexists
      command: top
    """
    When I run create on a project
    Then I should get an error