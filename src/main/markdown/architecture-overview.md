<!--
SPDX-FileCopyrightText: 2025 Digg - Agency for Digital Government

SPDX-License-Identifier: CC0-1.0
-->

# Architecture Overview (Draft)

The architecture proposed here is based on
[the Hexagonal (Ports & Adapters) Architecture](
    https://alistair.cockburn.us/hexagonal-architecture
)
introduced by Alistair Cockburn in 2005.

The main idea is to decouple the application from its external dependencies
so that it can be run and tested without the need of
a user interface, a database, message queues etc.

> Allow an application to equally be driven by
> users, programs, automated test or batch scripts,
> and to be developed and tested in isolation from its eventual run-time devices and databases.


## Ports

To decouple the application from its dependencies,
a set of *ports* (or interfaces) are defined by the application.
These ports are defined in a technology agnostic way
and speaks the language of the appplication.
In other words,
it does not use the terms or defifintions of any technology framework or tool like Spring, JPA or JDBC.


## Adapters

With these ports defined,
it is the responsibility of *adapters* to translate,
or adapt,
the application needs into a particular technology,
e.g. using Spring Data to implemenent persistence
or Spring MVC to map HTTP requests into application logic.


## Primary and secondary adapters

The Ports and Adapters architecture defines two kind of adapters:
*Primary* (or driving) adapters
and *secondary* (or driven) adapters.

> A primary actor is an actor that drives the application
> (takes it out of quiescent state to perform one of its advertised functions).
> A secondary actor is one that the application drives,
> either to get answers from or to merely notify.
> The distinction between primary and secondary lies in who triggers
> or is in charge of the conversation.


## Example architecture

```mermaid
---
  config:
    class:
      hideEmptyMembersBox: true
---
classDiagram
    direction LR

    note "In a ports and adapters (hexagonal) architecture,
    all dependencies point towards the application core.
    That is, the application core has no dependencies.
    Instead, the application defines interfaces (ports)
    that are implemented by adapters that translate the
    application specifics into infrastructure details."

    note for UserStore "This is a port for storing users persistently.
    It is independent of any specific storage technology.
    It could be implemented by a Spring Data repository,
    an in memory database, a flat file or any other means."

    note for SpringUserStore "This is an implementation of the UserStore
    that adapts the application specific port
    to storage backed by a Spring Data repository."

    note for UserService "This class provides a technology agnostic interface (port)
    to the application logic. For simplicity, there is no
    distinct Java interface for this port. Instead, the
    public interface of the class defines the port. If
    needed, we could split the class into a UserService
    interface (port) and an implementing class
    'DiggUserService'. This would give us the ability to
    test the UserController using a UserService test
    double."

    namespace com.example.demo.application {
        class UserService
        class UserStore
        class User
        class UserData
    }
    namespace com.example.demo.spring.web {
        class UserController
        class UserResource
    }
    namespace com.example.demo.spring.data {
        class SpringUserStore
        class UserRepository
        class UserEntity
    }
    namespace org.springframework.data.repository {
        class CrudRepository
    }
    namespace org.springframework.web.bind.annotation {
        class RequestMapping
        class GetMapping
        class PostMapping
    }
    UserController --> UserService
    <<record>> UserData
    <<record>> UserResource
    <<interface>> UserStore
    UserStore <-- UserService
    UserStore <|-- SpringUserStore
    User <-- SpringUserStore
    UserEntity <|-- SpringUserStore
    UserRepository <-- SpringUserStore
    UserEntity <-- UserRepository

    UserService --> UserData
    UserService : get(id) UserData
    UserService : save(UserData user)

    UserStore --> User
    UserStore : get(id) User
    UserStore : save(User user)

    UserRepository : findById(id) Optional~UserEnity~
    UserRepository : save(UserEntity user)

    CrudRepository <|-- UserRepository

    <<annotation>> RequestMapping
    <<annotation>> GetMapping
    <<annotation>> PostMapping

    UserController --> UserData
    UserController --> UserResource

    RequestMapping <-- UserController
    GetMapping <-- UserController
    PostMapping <-- UserController
```


## Testing

With the architecture defined above,
a number of different types of automated tests are possible.
Each of the identified test types are defined in a separate document.

- [Component test](./component-test.md)
- [Application test](./application-test.md)
- [Primary adapter test](./primary-adapter-test.md)
- [Secondary adapter test](./secondary-adapter-test.md)
