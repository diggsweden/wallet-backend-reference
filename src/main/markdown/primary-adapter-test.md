<!--
SPDX-FileCopyrightText: 2025 Digg - Agency for Digital Government

SPDX-License-Identifier: CC0-1.0
-->

# Primary adapter test (Draft)

When testing a primary adapter
we can either write a regular unit test
backed by a mocked version of the primary port
or we could use the real thing
and have test doubles for the secondary adapters.
To have better control and reduce complexity,
it is prefereable to mock the primary port.

In the case of or example,
that means we want to mock the `UserService`.

```mermaid
---
  config:
    class:
      hideEmptyMembersBox: true
---
classDiagram
    direction LR

    note for MockUserService "By mocking the UserService
    we can easily test that the
    UserController translates
    application specific behaviour
    into expected API responses.
    "

    class UserControllerTest
    UserControllerTest --> UserController
    UserControllerTest --> MockUserService
    MockUserService --|> UserService

    namespace com.example.demo.application {
        class UserService
        class UserData
    }
    namespace com.example.demo.spring.web {
        class UserController
        class UserResource
    }
    namespace org.springframework.web.bind.annotation {
        class RequestMapping
        class GetMapping
        class PostMapping
    }
    <<interface>> UserService
    UserController --> UserService
    <<record>> UserData
    <<record>> UserResource

    UserService --> UserData
    UserService : get(id) UserData
    UserService : save(UserData user)

    <<annotation>> RequestMapping
    <<annotation>> GetMapping
    <<annotation>> PostMapping

    UserController --> UserData
    UserController --> UserResource

    RequestMapping <-- UserController
    GetMapping <-- UserController
    PostMapping <-- UserController
```
