<!--
SPDX-FileCopyrightText: 2025 Digg - Agency for Digital Government

SPDX-License-Identifier: CC0-1.0
-->

# Component Test (Draft)

In the component test
we deploy and run the application as a separate process
and test it through its external interface.
For a REST service,
this means testing by sending HTTP requests
and inspecting the responses.

For persistence,
we are free to choose between a real database
(potentially running in a test container)
or using an in memory implementation of the corresponding adapter.
In the diagram below
we are using a database running in a test container.

```mermaid
---
  config:
    class:
      hideEmptyMembersBox: true
---
classDiagram
    direction LR

    note "Here we test with the whole service deployed locally
    and running as a separate process. We have a database
    running in test container managed by the test
    instance. The test interacts with the service through
    its API and can also inspect the database directly."

    class ComponentTest
    ComponentTest --> UserController : HTTP
    ComponentTest --> TestDatabase : JDBC

    class TestDatabase
    <<testcontainer>> TestDatabase
    TestDatabase <-- UserRepository : JDBC

    namespace com.example.demo.application {
        class UserService
        class UserStore
    }
    namespace com.example.demo.spring.web {
        class UserController
    }
    namespace com.example.demo.spring.data {
        class SpringUserStore
        class UserRepository
    }
    UserController --> UserService
    <<interface>> UserStore
    UserStore <-- UserService
    UserStore <|-- SpringUserStore
    UserRepository <-- SpringUserStore

    UserService : get(id) UserData
    UserService : save(UserData user)

    UserStore : get(id) User
    UserStore : save(User user)

    UserRepository : findById(id) Optional~UserEnity~
    UserRepository : save(UserEntity user)
```
