1, ReminderList class manager reminders, users can add, delete and edit reminders
2, Database class stores reminders and corresponding data.
3, Users can select reminders from a hierarchical list managed by ReminderType
4, The database class allows users to search for reminders by name and add new reminder types
5, Database class ensure reminders are saved upon modification
6, The reminder class allows reminders to be checked off
7, ReminderList class allows all check-off marks to be cleared.
8, The check-off state(isChecked)of the reminder class is persistent and saved in the database
9, ReminderList groups reminders by their associated ReminderType.
10, User can manage multiple ReminderList instances, such as weekly or monthly
11, The Alert class allows reminder to be set with day, time and repetition options. The alert class has an option to set up reminders based on location.