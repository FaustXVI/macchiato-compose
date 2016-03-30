Feature: Project create

  This feature is all about being able to create the project related
  containers, given a specified configuration file (composefiles).

  It is similar to the `docker-compose create` command.

  Background:
    Given a project name "myproject"

  Scenario: Create with a single service
    Given the following compose file
    """
    first:
      image: busybox
      command: top
    """
    When I run create on the project
    Then I should get a container with the specified attributes
      | attribute | value              |
      | Name      | /myproject_first_1 |
      | Running   | false              |
      | Image     | busybox            |
      | Command   | top                |


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
    When I run create on the project
    Then I should get a container with the specified attributes
      | attribute | value             |
      | Name      | myproject_first_1 |
      | Running   | false             |
      | Image     | busybox           |
      | Command   | top               |
    And I should get a container with the specified attributes
      | attribute | value              |
      | Name      | myproject_second_1 |
      | Running   | false              |
      | Image     | busybox            |
      | Command   | sleep 10           |
    And I should get a container with the specified attributes
      | attribute | value             |
      | Name      | myproject_third_1 |
      | Running   | false             |
      | Image     | alpine            |
      | Command   | cat /etc/hostname |

  Scenario: Create with several services, but with more complex image reference
    Given the following compose file
    """
    first:
      image: busybox:latest
      command: top
    second:
      image: sha256:47bcc53f74dc94b1920f0b34f6036096526296767650f223433fe65c35f149eb
      command: sleep 10
    third:
      image: alpine:3.3
      command: cat /etc/hostname
    """
    When I run create on the project
    Then I should get a container with the specified attributes
      | attribute | value             |
      | Name      | myproject_first_1 |
      | Running   | false             |
      | Image     | busybox:latest    |
      | Command   | top               |
    And I should get a container with the specified attributes
      | attribute | value                                                                   |
      | Name      | myproject_second_1                                                      |
      | Running   | false                                                                   |
      | Image     | sha256:47bcc53f74dc94b1920f0b34f6036096526296767650f223433fe65c35f149eb |
      | Command   | sleep 10                                                                |
    And I should get a container with the specified attributes
      | attribute | value             |
      | Name      | myproject_third_1 |
      | Running   | false             |
      | Image     | alpine:3.3        |
      | Command   | cat /etc/hostname |

  Scenario: Create a single service, with a project that has multiple services

  It's possible to specify one or several services when calling create. By doing
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
    When I run create on the project with the specified services
      | first |
      | third |
    Then I should get a container with the specified attributes
      | attribute | value             |
      | Name      | myproject_first_1 |
      | Running   | false             |
      | Image     | busybox           |
      | Command   | top               |
    And I should get a container with the specified attributes
      | attribute | value             |
      | Name      | myproject_third_1 |
      | Running   | false             |
      | Image     | alpine            |
      | Command   | cat /etc/hostname |
    And I should not have a container named "myproject_second_1"