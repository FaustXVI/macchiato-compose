Feature: Project "create" labels

  In order to identify the container that belong to a project, the name of the
  container is *the old* way of doing it. In more recent version of docker-compose,
  we now use the container labels to know if the container is managed or not by
  docker-compose.

  Background:
    Given a project name "myproject"
    And the macchiato-compose version is 1.6.2

  Scenario: Default compose labels
    Given the following compose file
    """
    first:
      image: busybox
      command: top
    """
    When I run create on the project
    Then I should get a container name "myproject_first_1" with the following labels
      | key                                 | value                                                            |
      | com.docker.compose.container-number | 1                                                                |
      | com.docker.compose.oneoff           | false                                                            |
      | com.docker.compose.project          | myproject                                                        |
      | com.docker.compose.service          | first                                                            |
      | com.docker.compose.version          | 1.6.2                                                            |
#      | com.docker.compose.config-hash      | 794c52c393018dd2d943b53df11ab038c3b2af7137d938f45bb84f3f404ef468 |

