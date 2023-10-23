Design Information
Created by Jonathan Flores 

1. A list consisting of reminders the users want to be aware of. The application must allow users to add reminders to a list, delete reminders from a list, and edit the reminders in
the list.

- To complete this a Reminders class is made where the user can make, edit, and delte reminders from a list


2. The application must contain a database (DB) of reminders and corresponding data.

- To complete this a DB classes has been added to the UML diagram


3. Users must be able to add reminders to a list by picking them from a hierarchical list,
where the first level is the reminder type (e.g., Appointment), and the second level is the name of the actual reminder (e.g., Dentist Appointment). 

- To complete this when adding a reminder the user can select a reminderType if none is provided then the reminderType shall be set to default reminderType


4. Users must also be able to specify a reminder by typing its name. In this case, the
application must look in its DB for reminders with similar names and ask the user
whether that is the item they intended to add. If a match (or nearby match) cannot be
found, the application must ask the user to select a reminder type for the reminder, or
add a new one, and then save the new reminder, together with its type, in the DB.

- To complete this the ListOfReminders class has a search operation which can search the DB for reminders. There is also a class ReminderType that allows the user to make a new reminder type


5. The reminders must be saved automatically and immediately after they are modified.

- To complete this the DB has a storeList() operation which can store the lists of remiders into the DB


6. Users must be able to check off reminders in the list (without deleting them).

- To complete this the Reminder class has an isChecked attribute which takes a boolean and if set to true then the reminder is checked off. If set to false then the reminder is not checked off


7. Users must also be able to clear all the check-off marks in the reminder list at once.

- To complete this the ListOfReminders class has an operation clearChecked() where it can clear all checked off marks at once 


8. Check-off marks for the reminder list are persistent and must also be saved immediately.

- To complete this as long as the boolean stays true then it will stay checked and the DB will store the reminder 


9. The application must present the reminders grouped by type.

- To complete this the user inputs the reminderType and the reminders are added to the list of that type


10. The application must support multiple reminder lists at a time (e.g., “Weekly”, “Monthly”, “Kid’s Reminders”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete reminder lists.

- To complete this a ListOfReminders class is made and the user is able to selcet, add, delete, and edit an existing list. 


11. The application should have the option to set up reminders with day and time alert. If this option is selected allow option to repeat the behavior.

- To complete this the user is able to set isAlert to true or false. If true then the user can set a date and time and have the option for repeating. If false then no date, time, or option for repetition is shown

12. Extra Credit: Option to set up reminder based on location.

- To complete this the user is able to set isLocation to true or false. If true the location can be set and if false no location can be set.


13. The User Interface (UI) must be intuitive and responsive.

- Not considered because it does not affect the design directly.
