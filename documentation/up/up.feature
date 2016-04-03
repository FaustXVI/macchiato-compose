Feature: Macchiato up

  Usually, the first thing you want to do is start the services described in your compose file.
  It is similar to the `docker-compose up` command.

  Scenario: Start a single service
  This is a very simple use case : you just want to start your image.
    Given you have a compose file called "my-awesome-compose-file.yml" in a folder named "sample" like :
    """
    my-nginx:
      image: nginx
    """
    When you execute the following code :
    """
    new Project("my-awesome-compose-file.yml").up()
    """
    Then mocchiato starts a container named "sample_my-nginx" that runs the "nginx" image


  Scenario: Start several services
  Macchiato can start several services. The java code stays the same, only the compose file changes.
    Given you have a compose file called "my-awesome-compose-file.yml" in a folder named "sample" like :
    """
    my-nginx:
      image: nginx
    my-postgres:
      image: postgres
    """
    When you execute the following code :
    """
    new Project("my-awesome-compose-file.yml").up()
    """
    Then mocchiato starts a container named "sample_my-nginx" that runs the "nginx" image
    Then mocchiato starts a container named "sample_my-postgres" that runs the "postgres" image

  Scenario: Start a specific services in a multi-service file
  Of course, if you don't want to start all services, you can select one (or several) service(s) by passing it to
  the up method as argument.
    Given you have a compose file called "my-awesome-compose-file.yml" in a folder named "sample" like :
    """
    my-nginx:
      image: nginx
    my-postgres:
      image: postgres
    """
    When you execute the following code :
    """
    new Project("my-awesome-compose-file.yml").up("my-nginx")
    """
    Then mocchiato starts a container named "sample_my-nginx" that runs the "nginx" image
    Then mocchiato does not starts a container for "my-postgres".