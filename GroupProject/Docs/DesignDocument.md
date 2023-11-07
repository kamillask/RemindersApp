# Design Document

**Author**: Group 5


## 1 Design Considerations




### 1.1 Assumptions

The reminder application must allow users to create a reminder list or create a new reminder. Earlier, the design only portrayed one list which consisted of multiple reminder types. This affects the well designed structure and usability of app since users would have to store all reminder types within a shared list. This approach won’t keep all reminders well-organized based on their type. 

### 1.2 Constraints

In order to provide more flexibility to the user, users can create multiple reminder lists that consist of a reminder type. The earlier design needed to be altered in order to suit this new change. 


### 1.3 System Environment

The software must operate in devices that support Android API level 24 or above.

## 2 Architectural Design


### 2.1 Component Diagram
<img src="https://i.ibb.co/zR0z0hY/component.png">

This diagram consists of five classes, which represents the overall structure of the reminders application. The reminderList will require the reminderType which consists of the reminders class. This is illustrated by having the socket(require) and lollipop(provide) stem from reminderList to reminderType. The three classes: repeat, GPS and alert will be used by the reminders class. 


### 2.2 Deployment Diagram
<img src="https://i.ibb.co/JKgTLz4/deployment.png" width=50% height=50%>

This diagram shows the structure of the reminder application. This diagram showcases three nodes which includes Android device, Web Server and Reminder App DB. Inside the application file of the Android device we have the RemindersApp.apk. Format apk is an android package which allows the device to install and distribute files of an app. This is an archive file and it holds other secondary files such as UI. The Android Device node is connected to the Web Server node. It is connected using a “HTTPS” to represent communication protocols as well as safety features. The Web Server consists of a component called “Methods” which represents a resource where the app is executed. The Web Server node is connected to the Reminder App DB. The SQL is used to connect both nodes. This is used to represent the communication between software components and the reminder app database. 


## 3 Low-Level Design

**System Component of Component Diagram**

In the UML diagram, we have a reminderList class which contains a reminder type. Likewise, in the component diagram, we require the ReminderList component to gather information for the reminderType class. This is done by providing the socket(require) interface. Inside the ReminderType component we have a Reminder class which indicates that reminders resides within the reminder type. This is done similarly in the UML diagram, were the reminderType class takes information from the reminder class. Finally, we have three separate classes(GPS, Repeat, and Alert) which are dependent on the reminder class. 

**System Component of Deployment Diagram**

Within the deployment diagram, there is SQL (Structured Query Language) which allows to perform operations on databases. In our UML diagram, we are assuming that the database is already implemented and the data is stored and received by and from the reminder application. This constant communication is needed to fully run the app because application searches for reminders in the database. 



### 3.1 Class Diagram
<img src="https://i.ibb.co/JQ38dgX/design-team.png">

### 3.2 Other Diagrams

<img src="https://i.ibb.co/vvdfgsd/re-design.png">

This diagram provides the sequential steps or the behavioral flow of the reminder application. It documents the use case where a user opens the reminder app and has the option to create a reminder type or a reminder. The reminder list is organized based on the exclusive reminder types. The user has the option to set alert, GPS, and repeat behavior. After the user finishes setting up the reminder, they have the option to put inside a reminder type. 

User: opens the reminder app, has the option to create a new reminder list or create a new reminder.
If the user wants to create a new reminder list, they have to select a reminder type and add reminders into it. 
If the user wants to create a new reminder, they are prompted to search for the reminder type. Once the reminder type is added, they will be stored inside the reminder list of that type.

Components inside the sequence behavioral diagram

**ReminderList**

Collection of reminders, sorted by type. Operations include to add, remove, display, organize reminders in lists. It would need methods to handle the sorting of reminders based on the reminder type.

**ReminderType**

Categorizing reminders allows for the management of the categories. Operations include add, delete, edit, reminder types. This class has a string attribute for the type name.

**Reminder**

Showing an individual reminder with details, such as name, data etc, Operations include setting the reminder status, edit, and delete. Utilizing a data class for “DateCreated” and boolean for “isCheckedOff”.

**Repeat**

Controls the repetition aspect of reminders. Operations enable setting and scheduling reminders on a repeating basis. 

**Alert**

Handles the alerting functionality for reminders, with properties to store alert timing. Operations have to check the location or time for triggering alerts. Sending notifications. The structure might include a timer for “timeAlert”.

**GPS**

Manages location data for location-based reminders. Operation is to set the location for the reminders. This might use the GPS APIs.



## 4 User Interface Design

Homepage           |  List View        | Edit View
:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://i.ibb.co/DYZpjBV/Reminder-Homepage.png" width="200">|  <img src="https://i.ibb.co/hZRK2W9/Reminder-List.png" width="200">|  <img src="https://i.ibb.co/4fVcVr0/Reminder-Edit.png" width="200">