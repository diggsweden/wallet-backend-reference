<!--
SPDX-FileCopyrightText: 2025 Digg - Agency for Digital Government

SPDX-License-Identifier: CC0-1.0
-->

# Secondary Adapter Test (Draft)

A secondary (or driven) adapter translates application needs into a particular technology or framework.
Let's consider our `SpringUserStore`.
It adapts the need of the application to retrieve and store users persistently
by leveraging the Spring data framework to store users in a database.
To test this code,
it makes sense to have an integrated test against a real database.
This way we can gain confidence that our use of the framework actually works the way we think.
Should we instead use a mocked database
and only check that the expected calls are made to the database,
our tests would be
more tightly coupled to the implementation,
more likely to break on changes to the production code
and also give us less confidence that the production code actually works.

```mermaid
---
  config:
    class:
      hideEmptyMembersBox: true
---
classDiagram
    direction TB

    note "Here we test that we can correctly
    store and retreive User domain
    objects in a database via our
    Spring Data implementation.
    By testing the database integration
    thoroughly here, we can safely use a test
    double for the UserStore in other tests.
    "

    note for UserStore "By defining an application specific interface,
    the UserStore, instead of using the
    UserRepository directly in the application
    logic, we minimize the interface between the
    core application and the storage mechanism.
    This in turn reduces the amount of cases we
    need to test"

    namespace com.example.demo.application {
        class UserStore
        class User
    }
    namespace com.example.demo.spring.data {
        class SpringUserStore
        class UserRepository
        class UserEntity
    }
    namespace org.springframework.data.repository {
        class CrudRepository
    }
    <<interface>> UserStore
    UserStore <|-- SpringUserStore
    User <-- SpringUserStore
    UserEntity <|-- SpringUserStore
    UserRepository <-- SpringUserStore
    UserEntity <-- UserRepository

    UserStore --> User
    UserStore : get(id) User
    UserStore : save(User user)

    UserRepository : findById(id) Optional~UserEnity~
    UserRepository : save(UserEntity user)

    CrudRepository <|-- UserRepository

    class SpringUserStore
    SpringUserStoreTest --> UserStore
    SpringUserStoreTest --> SpringUserStore
    SpringUserStoreTest --> User
    SpringUserStoreTest --> TestDatabase : Manages lifecycle of

    CrudRepository --> TestDatabase

    class TestDatabase
    <<testcontainer>> TestDatabase
```
