# Test Plan
**Author**: Group 5

## 1 Testing Strategy

### 1.1 Overall strategy

Our testing strategy for the Reminder App will encompass multiple levels of testing to ensure that each component functions correctly both in isolation and in conjunction with other components. The testing levels will include:

- Unit Testing: Individual components will be tested to ensure that they perform as expected. This will include testing classes, methods, and functions for the core functionality such as list creation, reminder instantiation, alert setup, and system acknowledgment.

- Integration Testing: After unit testing, we will perform integration tests to verify that the components work together. This will involve testing the interactions between the 'New Reminder List' creation feature, 'Individual Reminder' creation feature, 'Alert Repeat' function, and the acknowledgment signals.

- System Testing: The entire application will be subjected to system testing to ensure that it meets the requirements specified in the sequence behavioral description. This will test the end-to-end functionality of the app, including user interaction flows and system responses.

- Regression Testing: Whenever a new feature is added or an existing feature is modified, regression tests will be performed to ensure that the change has not adversely affected the existing functionality.

The testing will be carried out iteratively, with each release cycle going through the unit, integration, system, and regression tests as needed. The testing team will include software testers for manual testing and software developers for automated test script creation.


### 1.2 Test Selection

The selection of test cases will be based on both black-box and white-box testing techniques:
* Black-box Testing: Test cases will be derived from the software requirements and specifications without considering the internal workings of the application. This will include:

    * Functional Testing: To verify that each function of the software application behaves as specified in the sequence behavioral description.

    * Non-functional Testing: To assess the application’s performance, usability, reliability, and responsiveness.

* White-box Testing: Test cases will be based on the internal structure of the application. This will include:

    * Path Testing: To ensure all possible paths through the code are executed at least once.

    * Statement and Branch Coverage: To ensure that all possible branches in the code have been tested and that every statement in the code has been executed.

Both techniques will be employed at different testing levels. For example, unit testing will lean more towards white-box testing, while system testing will primarily utilize black-box testing.

Then we will proceed to define specific test cases based on the features and behaviors described.


### 1.3 Adequacy Criterion

**Code Coverage:** Aiming for at least 80% code coverage for unit tests.

**Requirements:** Coverage; make sure all the functional requirements are covered by one test case for system testing.


### 1.4 Bug Tracking

We will use the [Bug Tracking System] tool for bug and enhancement tracking. All bugs and issues discovered during testing will be recorded and tracked through this system until solved.

### 1.5 Technology

**JUnit:** The testing framework for writing and running repeatable tests in Java, used for unit-level testing.

**AndroidJUniRunner:** The default test runner on Android which supports both JUnit4 and JUnit3 tests.

**Android Profiler:** Integrated into Android Studio, it provides real-time statistics for CPU usage to see the performance.

**Crashlytics:** Real-time reporting is critical for monitoring the app stability.


## 2 Test Cases



|Test Case ID|Purpose   |Steps   |Expected Result   |Actual Result   |Pass/Fail   |Comments   |
|---|---|---|---|---|---|---|
|TC01   |Check reminder creation   |1.Navigate to reminders. <br />2. Create a new reminder   |New reminder is added to the list   |   |   |   |
|TC02   |Test Reminder Notification   |1.Create a reminder with an alert. <br />2. Wait for the alert time|Notification is received at the correct time   |   |   |Test with phone locked and unlocked.   |
|TC03   |Verify Data Sync Across Devices   |1.Create a reminder on Device A.<br />2. Login on Device B. <br />3. Check for reminder on Device B.|Same reminder appears on Device B|   |   |Must be logged in with the same user account|
|TC04   |Assess GPS Reminder Trigger|1.Set a location-based reminder. <br />2. Approach the location. <br />3. Verify reminder alert|Reminder alert is triggered when the location is reached.|   |   |GPS accuracy may affect the result.|

