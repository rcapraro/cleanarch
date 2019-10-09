# Clean Architecture example 
[![Build Status](https://travis-ci.org/rcapraro/cleanarch.svg?branch=master)](https://travis-ci.org/rcapraro/cleanarch)


An example web app structured with a [Clean Architecture][clean-arch], implemented using [Spring Boot][springboot] and [Kotlin][kotlin].

![Robert C Martin - Clean Architecture](https://bit.ly/2vhBWz7)
![Robert C Martin - Clean Architecture Typical implementation](http://i.imgur.com/WkBAATy.png)

Following the control flow, we have an HTTP Request that reaches the Controller. The controller will then:

- Dismantle the Request;
- Create a Request Model with the relevant data;
- Execute a method in the Interactor (which was injected into the Controller using the Interactor’s interface, the Boundary), passing it the Request Model;
- The Interactor:
  - Uses the Entity Gateway Implementation (which was injected into the Interactor using the Entity Gateway Interface) to find the relevant Entities;
  - Orchestrates interactions between Entities;
  - Creates a Response Model with the data result of the Operation;
  - Populates the Presenter (which was injected into the Interactor) giving it the Response Model;
  - Returns the Presenter to the Controller;
- Uses the Presenter to generate a ViewModel;
- Binds the ViewModel to the View;
- Returns the View to the client.

![Clean Architecture Control Flow](https://bit.ly/2P4s0RG)

Clean Architecture push us to separate __stable__ business rules (higher-level abstractions) from __volatile__ technical details (lower-level details), defining clear boundaries. 

The main building block is the Dependency Rule : source code dependencies must point only __inward,__ toward __higher-level policies__.

It should have the following characteristics:

- Testable
- Independent of frameworks
- Independent of the UI
- Independent of the database
- Independent of any external agency


[clean-arch]: https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html
[springboot]: https://spring.io/projects/spring-boot
[kotlin]: https://kotlinlang.org/