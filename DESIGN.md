# Macchiato design

This document aims to document a little bit how `macchiato` should be
designed. It will probably be a temporary file that will be moved to
some sort of documentation once `macchiato` reaches the level of
possible usage.

## Compatibility with `docker-compose`

The main goal of `macchiato` is to be fully compatible with the
`docker-compose` files and *behaviours* while being implemented
on the JVM. This means :

- It should support all the version of the `docker-compose` YAML files
- It should support the set of *commands* (through methods as `macchiato`
is a library) — `create`, `start`, `up`, `run`, `config`, `down`, `stop`,
 `rm`, …

## *Compose* main notions

The main notions that comes with `docker-compose` are :

1. project

    Compose uses a project name to isolate environments from each other.
    A project is tied to one or more compose yaml file.

2. service

    A compose project has one or more services. Each service is tied to
    a docker image, either built or pulled from a docker registry.
    A service have a set of options that will be passed to docker through
    the docker API.

3. container

    Each compose service will create one or more container (if scaling for
    example). A container can be seen as the docker instance of a service.

There is also other citizens with the `v2` of the compose yaml files :
- volumes : volume definitions
- logging : logging definitions — which driver to use, etc…
- networks : network definitions

See the `docker-compose` yaml file [reference](https://docs.docker.com/compose/compose-file/).

## Macchiato specifics

`macchiato` should support some event and/or hook mechanism like
[libcompose](https://github.com/docker/libcompose) does for example.

The basic idea is to be able to react to event when using `macchiato`.
The code using `macchiato` could execute something in case of project
starting event, or container created, etc…