1.A list consisting of reminders the users want to be aware of. The application must allow users to add reminders to a list, delete reminders from a list, and edit the reminders in the list.

This requirement was implemented by using a ReminderLists class where there are methods to add reminder, delete reminder, edit reminder, checkOff reminder and clearing all check offs based on their reminder type. 

2.The application must contain a database (DB) of reminders and corresponding data. 

DB is shown in the class diagram to represent the retrieval and searching for data used by the reminder application.

3.Users must be able to add reminders to a list by picking them from a hierarchical list, where the first level is the reminder type (e.g., Appointment), and the second level is the name of the actual reminder (e.g., Dentist Appointment). 

The users can choose different types of reminders from the reminder list by invoking the addReminder(name) method. It will then go to the Reminder type class where it will be stored by type and the reminder class will produce new reminders. 

4.Users must also be able to specify a reminder by typing its name. In this case, the application must look in its DB for reminders with similar names and ask the user whether that is the item they intended to add. If a match (or nearby match) cannot be found, the application must ask the user to select a reminder type for the remind, or add a new one, and then save the new reminder, together with its type, in the DB. 

In the reminder application class, there is a method called remindersSearchinDB which will go to the DataBase to search for previous data inputted into the reminder app. The AddRemList(Reminder) inside this class will allow to create a new reminder. 

5.The reminders must be saved automatically and immediately after they are modified. 

The method called SaveRemindersinDB() in Reminder application will save the reminders inside the database.

6.Users must be able to check off reminders in the list (without deleting them). 

The checkoffReminders(name) method inside the reminders list class will allow the users to save check off reminders. 


7.Users must also be able to clear all the check-off marks in the reminder list at once. 

The clearAllCheckOFF() method inside the reminders list class will allow the users to clear all check off reminders.


8.Check-off marks for the reminder list are persistent and must also be saved immediately. 

The checkoffReminder method inside the reminder list will carry the information immediately into Database once it is done. 

9.The application must present the reminders grouped by type. 

When new reminders are created inside the reminders class, they are grouped accordingly to their reminder types inside the reminder type class. This way all reminders are stored by their type.

10.The application must support multiple reminder lists at a time (e.g., “Weekly”, “Monthly”, “Kid’s Reminders”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete reminder lists. 

In order to allow users to create and edit their reminder lists, a class called MultipleList is established. This will allow users to create, rename, select and delete reminder lists.

11.The application should have the option to set up reminders with day and time alert. If this option is selected allow option to repeat the behavior. 

Inside the reminders class, there is an implementation given for the selection of alerts. If the alert is set to false, the RepeatBehavior class won’t be invoked. If it is set to true, it will allow the option to repeat the behavior.

12.Extra Credit: Option to set up reminder based on location.

This requirement is fulfilled by using Google Maps which will allow users to set a location along with their reminders.The current location is set to null and valid location can be accessed through GPS. 

13.The User Interface (UI) must be intuitive and responsive

This diagram does not consists of UI.
