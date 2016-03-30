Feature: Project stop

  This feature is all about being able to stop the project related containers,
  given a specified configuration file (composefile).

  Background:
    Given a project name "myproject"

  Scenario: Stop with a single service, that is not created

  If we run stop on a completely new project that have no related container
  created, we should do nothing really (no error either).

    Given the following compose file
    """
    first:
      image: busybox
      command: top
    """
    When I run stop on the project
    Then I should not have a container named "myproject_first_1"

  Scenario: Stop with a single service, that is created but not running

  If we run stop on a completely new project that have related containers created
  but not running, we should do nothing really (no error either).

    Given the following compose file
    """
    first:
      image: busybox
      command: top
    """
    And the project is created
    When I run stop on the project
    Then I should get a container with the specified attributes
      | attribute | value             |
      | Name      | myproject_first_1 |
      | Running   | false             |
      | Image     | busybox           |
      | Command   | top               |

  Scenario: Stop with a single service, that is running

  If we run stop on a completely new project that have related containers running,
  we should stop them.

    Given the following compose file
    """
    first:
      image: busybox
      command: top
    """
    And the project is started
    When I run stop on the project
    Then I should get a container with the specified attributes
      | attribute | value             |
      | Name      | myproject_first_1 |
      | Running   | false             |
      | Image     | busybox           |
      | Command   | top               |

  Scenario: Stop with a single service, with a project that has multiple services
  running

  It's possible to specify one or several services when calling create. By doing
  this, compose will only create the one that are specified.

    Given the following compose file
    """
    first:
      image: busybox
      command: top
    second:
      image: busybox
      command: top
    third:
      image: busybox
      command: top
    """
    And the project is started
    When I run stop on the project with the specified services
      | first |
      | third |
    Then I should get a container with the specified attributes
      | attribute | value             |
      | Name      | myproject_first_1 |
      | Running   | false             |
      | Image     | busybox           |
      | Command   | top               |
    Then I should get a container with the specified attributes
      | attribute | value              |
      | Name      | myproject_second_1 |
      | Running   | true               |
      | Image     | busybox            |
      | Command   | top                |
    Then I should get a container with the specified attributes
      | attribute | value             |
      | Name      | myproject_third_1 |
      | Running   | false             |
      | Image     | busybox           |
      | Command   | top               |
