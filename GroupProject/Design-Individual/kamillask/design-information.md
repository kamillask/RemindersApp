1. A list consisting of reminders the users want to be aware of. The application must allow
users to add reminders to a list, delete reminders from a list, and edit the reminders in
the list.

-->Satisfied by the class ReminderList, having methods to add, delete, and edit reminders in a list.

2. The application must contain a database (DB) of reminders and corresponding data.
-->Satisfied by the object Database. Database takes in data from other classes, and stores it.

3. Users must be able to add reminders to a list by picking them from a hierarchical list,
where the first level is the reminder type (e.g., Appointment), and the second level is the
name of the actual reminder (e.g., Dentist Appointment).

-->Satisfied by User, ReminderType and ReminderList. The reminder is first created by User, 
then a type is assigned in ReminderType, and then is assigned to a list in ReminderList.

4. Users must also be able to specify a reminder by typing its name. In this case, the
application must look in its DB for reminders with similar names and ask the user
whether that is the item they intended to add. If a match (or nearby match) cannot be
found, the application must ask the user to select a reminder type for the reminder, or
add a new one, and then save the new reminder, together with its type, in the DB.

-->Satisfied by ReminderType. It uses the method searchForSimilar(), that compares the given input with the stored 
reminder types.

5. The reminders must be saved automatically and immediately after they are modified.

-->Satisfied by the connections between the classes and the database.

6. Users must be able to check off reminders in the list (without deleting them).

-->Satisfied by Reminder and ReminderList. In reminder, it checks off inidivudal reminders, with the functionality of 
checking off a reminder, and also clearing off one or all checks. In ReminderList, you can check the whole list,
remove a single check from a reminder in a list, or uncheck the whole list.

7. Users must also be able to clear all the check-off marks in the reminder list at once.

-->Satisfied by ReminderList, with removeAllChecksFromList().

8. Check-off marks for the reminder list are persistent and must also be saved immediately.

-->Satisifed by connection to database from Reminder and ReminderList.

9. The application must present the reminders grouped by type.

-->Satisfied by ReminderList with the groupByType() method.

10. The application must support multiple reminder lists at a time (e.g., “Weekly”, “Monthly”,
“Kid’s Reminders”). Therefore, the application must provide the users with the ability to
create, (re)name, select, and delete reminder lists.

--> Satisfied by ReminderList , User, and Database. Database stores a list of all reminder lists, ReminderList 
has the method to edit the list, and user has the method to create and delete the list.

11. The application should have the option to set up reminders with day and time alert. If this
option is selected allow option to repeat the behavior.

-->Satisfied by Reminder and ReminderAlert. ReminderAlert gives the user the option to add an alert to the reminder
with reminderAlert(), and then ReminderAlert checks the time of the reminder and sends an alert accordingly,
and also has the option of repeating the reminder.

12. Extra Credit: Option to set up reminder based on location.

-->Satisfied by ReminderAlert, that takes in the location set by the user and then checks the location with 
checkLocation().

13. The User Interface (UI) must be intuitive and responsive

-->Not implemented in the design.
