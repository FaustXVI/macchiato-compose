Feature: Project up

  This feature is all about being able to create the project related
  containers and start them, given a specified configuration file (composefiles).

  It is similar to the `docker-compose up` command.

  Background:
    Given a project name "myproject"

  Scenario: Up with a single service
    Given the following compose file
    """
    first:
      image: busybox
      command: top
    """
    When I run up on the project
    Then I should get a container with the specified attributes
      | attribute | value             |
      | Name      | myproject_first_1 |
      | Running   | true              |
      | Image     | busybox           |
      | Command   | top               |

  Scenario: Create with several services
    Given the following compose file
    """
    first:
      image: busybox
      command: top
    second:
      image: busybox
      command: sleep 10
    third:
      image: alpine
      command: cat /etc/hostname
    """
    When I run up on the project
    Then I should get a container with the specified attributes
      | attribute | value             |
      | Name      | myproject_first_1 |
      | Running   | true              |
      | Image     | busybox           |
      | Command   | top               |
    And I should get a container with the specified attributes
      | attribute | value              |
      | Name      | myproject_second_1 |
      | Running   | true               |
      | Image     | busybox            |
      | Command   | sleep 10           |
    And I should get a container with the specified attributes
      | attribute | value             |
      | Name      | myproject_third_1 |
      | Running   | true              |
      | Image     | alpine            |
      | Command   | cat /etc/hostname |


  Scenario: Up a single service, with a project that has multiple services

  It's possible to specify one or several services when calling up. By doing
  this, compose will only create the one that are specified.

    Given the following compose file
    """
    first:
      image: busybox
      command: top
    second:
      image: busybox
      command: sleep 10
    third:
      image: alpine
      command: cat /etc/hostname
    """
    When I run up on the project with the specified services
      | first |
      | third |
    Then I should get a container with the specified attributes
      | attribute | value             |
      | Name      | myproject_first_1 |
      | Running   | true              |
      | Image     | busybox           |
      | Command   | top               |
    And I should get a container with the specified attributes
      | attribute | value             |
      | Name      | myproject_third_1 |
      | Running   | true              |
      | Image     | alpine            |
      | Command   | cat /etc/hostname |
    And I should not have a container named "myproject_second_1"