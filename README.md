#  Developer Test Task

Purpose: test basic software development skill with Java, Spring Boot.

# System Requirements

One would need 
* Jdk v11 
* `maven`
* Docker service running

# Test Task

## Application Requirements

When JSON data arrives at the topic specified in the application.yml, the service must submit the data to a remote endpoint as JSON. 

When the remote endpoint responds:
* with HTTP Status = 200 OK, the commit the offset of the message,
* with HTTP Status = 400 Bad Request, must log the body of the data with the ERROR level and commit the consumption of the message,
* with any other HTTP Status — the service should retry indefinitely with backoff for 1 second until the payload is delivered

## Implementation Requirements

* You are expected to complete the implementation of the `KafkaDataListener` and `SubmitterImplementation` classes in order to implement the behaviour in the test task. However, you are free to modify the design of the application to achieve the goals as specified in the task.  
* There's no limitation on how implementation, methods signatures, classes, test, configuration can be modified.
* You are free to use any library that you consider useful for this task.
* One integration test is provided for illustration purposes only.  It can also be modified (but not necessarily). 
* Solution should include all required tests: both unit and integration tests for all possible scenarios.



# Submission Requirements

* When working on your solution, create a new branch `solution` branching from the `master` branch. Do not commit to the `master` branch.  
* Keep all you commits as per your typical commit cycle
* Send back a ZIP archive with the project with the following requirements satisfied:
  * Name of the archive should be `Java-Developer-2024-$YouName.zip`
  * Archive should contain full git repository with your work
  * Archive should contain only source code (no compiled classes)

# Task Evaluation

## Evaluation Criteria

Your submission will be evaluated on the following criteria
* Completeness of the solution and its compliance with the requirements
* Test coverage
* Clarity of the code, including clarity of the test code
* Clarity of the design: separation of concern, separation of logic layers, etc.
* Clarity of the commit messages.

## Assessment Method

* The reviewer will unpack your project to a separate folder in an environment that satisfies the system requirement above
* The reviewer will run the following command: `mvn clean test` and observe the tests pass
* The reviewer will read your code and grade it against the criteria above.

# git-test-sunil
# git-test-sunil
# git-test-sunil
