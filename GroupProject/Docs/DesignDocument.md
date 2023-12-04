# Design Document

**Author**: Group 5

#### Version 2: Added the new component diagram, consists of four classes. This new diagram reflects the changes in our app. Added the assumptions(1.1) is updated based on our new changes. Some changes we made include some of our project issues such as deleting reminders/reminder lists. Constraints(1.2) is also updated regarding storing reminders in a hierarchical order. 



## 1 Design Considerations




### 1.1 Assumptions


The reminder application must allow users to create a reminder list and a new reminder. To provide more flexibility to the user, users can create multiple reminder lists that consist of many reminder types. Earlier, the design only allowed the creation of a reminder list which consisted of one reminder type. This approach won’t result in a well-designed structure because we are limiting the users to only input one reminder type within a reminder list. As we progressed with implementing our app, sometimes reminders may not appear after adding them to the database. This can be resolved by refreshing the app. One other issue we dealt with was deleting the reminders and reminder list. Even though the user inputs get deleted from the screen, it is not getting deleted from the database. The reminder will get deleted from the recycler view upon choosing the delete option. Implementing the SQLite Database helped us to store the data that was inputted by the user and we were able to completely add reminder type, reminder name, day, and time alert inside the database.



### 1.2 Constraints

In our design requirements, we were asked to implement a hierarchical list where users can pick a reminder list on the first level and the second level is the name of the actual reminders. Currently, in our design, we allow the user to pick a reminder type and add any types that they wish to add using a spinner. The new types that are added are stored inside the spinner. The spinner already consists of two default reminder types, “meeting” and “appointment”. After selecting a reminder type, users can write a reminder name that is associated with the reminder type. To implement this, we had to store the data in a spinner using ArrayList and store it inside the reminder type inside the database. It was a constraint because we were not able to store previous data inside the spinner. Every time we restarted, the data would disappear. However, storing the data separately and then connecting it to the database helps to resolve this issue.



### 1.3 System Environment

The software must operate in devices that support Android API level 28 or above.

## 2 Architectural Design


### 2.1 Component Diagram
<img src="https://i.ibb.co/3h8HH1D/componentdiagram.png">

This diagram consists of four classes, which represent the overall structure of the reminders application. The reminderList will require the reminderType which consists of the reminder class. This is illustrated by having the socket(require) and lollipop(provide) stem from reminderList to reminderType. The Alert class is used by the reminder class. This is because it is needed for the day and time alert setting. 



### 2.2 Deployment Diagram
<img src="https://i.ibb.co/qDv6W4W/deployment-1.png" width=50% height=50%>

This diagram shows the structure of the reminder application. This diagram showcases two nodes which include the Android device and the Reminder App DB. Inside the application file of the Android device we have the RemindersApp.apk. Format apk is an android package which allows the device to install and distribute files of an app. This is an archive file and it holds other secondary files such as UI. The Android Device node is connected to the Reminder App DB. It is connected with SQL, using SQLite. The app sends information to the DB to be stored, and retrieves information from the DB when it is needed.


## 3 Low-Level Design

**System Component of Component Diagram**

In the UML diagram, we have a reminderList class which contains a reminder type. Likewise, in the component diagram, we require the ReminderList component to gather information for the reminderType class. This is done by providing the socket(required) interface. Inside the ReminderType component, we have a Reminder class which indicates that reminders reside within the reminder type. This is done similarly in the UML diagram, where the reminderType class takes information from the reminder class. Finally, we have the Alert class which is dependent on the reminder class. 

**System Component of Deployment Diagram**

Within the deployment diagram, there is SQL (Structured Query Language) which allows to perform operations on databases. In our UML diagram, we are assuming that the database is already implemented and the data is stored and received by and from the reminder application. This constant communication is needed to fully run the app because the application searches for reminders in the database. 



### 3.1 Class Diagram
<img src="https://i.ibb.co/HDThkw8/UML-class.png">

### 3.2 Other Diagrams

<img src="https://i.ibb.co/hHjNyJh/sequencediagram.png">

This diagram provides the sequential steps or the behavioral flow of the reminder application. It documents the use case where a user opens the reminder app and has the option to create a reminder type or a reminder. The reminder list is organized based on the exclusive reminder types. The user has the option to set an alert. After the user finishes setting up the reminder, they have the option to put inside a reminder type.

User: opens the reminder app, has the option to create a new reminder list or create a new reminder.
If the user wants to create a new reminder list, they have to select a reminder type and add reminders into it. 
If the user wants to create a new reminder, they are prompted to search for the reminder type. Once the reminder type is added, they will be stored inside the reminder list of that type.


### Components inside the sequence behavioral diagram

**ReminderList**

Collection of reminders, sorted by type. Operations include to add, remove, display, organize reminders in lists. It would need methods to handle the sorting of reminders based on the reminder type.

**ReminderType**

Categorizing reminders allows for the management of the categories. Operations include add, delete, edit, reminder types. This class has a string attribute for the type name.

**Reminder**

Showing an individual reminder with details, such as name, data etc, Operations include setting the reminder status, edit, and delete. Utilizing a data class for “DateCreated” and boolean for “isCheckedOff”.

**Alert**

Handles the alerting functionality for reminders, with properties to store alert timing. Operations have to check the location or time for triggering alerts. Sending notifications. The structure might include a timer for “timeAlert”.



## 4 User Interface Design

Homepage           |  List View        | Edit View
:-------------------------:|:-------------------------:|:-------------------------:
<img src="https://i.ibb.co/DYZpjBV/Reminder-Homepage.png" width="200">|  <img src="https://i.ibb.co/hZRK2W9/Reminder-List.png" width="200">|  <img src="https://i.ibb.co/4fVcVr0/Reminder-Edit.png" width="200">