# Use Case Model

**Author**: Group 5

## 1 Use Case Diagram
<img src="https://i.ibb.co/f82dY1q/Use-Case-Diagram.png">




## 2 Use Case Descriptions



## Create Reminder

- Requirements: Allows the user to create a reminder that will be added to a list

- Pre-conditions: User must have the app open on the main screen

- Post-conditions: The user should see the reminder added to the list of their choice

**Scenarios:**
- Normal Scenario: (Bare minimum)
    - User selects the option to create a reminder

    - User is prompted to search for the type of reminder/ which list to place the reminder in

    - If no option is found the user can select an option to create a type

    - Once the type is chosen the user can then name the reminder

    - This is the bare minimum for a reminder to be added

- Alternate Scenario: (Adding Optional features)

    - User selects the option to create a reminder

    - User is prompted to search for the type of reminder/ which list to place the reminder in

    - If no option is found the user can select an option to create a type

    - After selecting a type the user is prompted to name the reminder

    - The user can also add a location 

    - The user can also add an alert by choosing a day and time 

    - If the alert option is selected the user can also set the option to repeat the reminder

- Exceptional Scenario: (No reminder type chosen)

    - The user selects the option to create a reminder

    - The user is prompted to search for the type of reminder

    - If the user wants to move to the next step the default reminder type is chosen for the user 

    - The user will then name the reminder

    - The user will have the option to add the additional features 

    - The reminder is now added to the default reminder list 

- Exceptional Scenario: (No name for reminder)
    - The user selects the option to create a reminder

    - The user is prompted to search for the type of reminder

    - Once the user selects the type they are prompted for the name of the reminder

    - If the user wants to skip this option there will be an error message not allowing the user to move on to the next step

## Create List

- Requirements: Allows the user to make a list that will be added to a list of lists

- Pre-conditions: User must have the app open on the main screen

- Post-conditions: The user should see the added to the list of lists and can now add reminders into that list 

**Scenarios:**

- Normal Scenario: 
    - The user selects the option to create a new list

    - The user is prompted to name the list which will be its type 

    - The list is added to the list of lists

- Alternate Scenario: (creating a list through a reminder)

    - The user chooses to create a new reminder

    - The user is prompted to select a reminder type but does not find one they are looking for 

    - The user is then prompted to make a reminder type

    - The reminder type is added which makes a list for the reminder to be in 

- Exceptional Scenario: (No list name)

    - The user selects the option to create a list 

    - The user does not want to name the list and wants to proceed to the next step

    - There is an error message not allowing the user to continue until they have named the reminder

## Selecting List

- Requirements: Allows the user to select a list and many things with that selected list

- Pre-conditions: User must have app open and be in the list of lists screen 
- Post-conditions: User will have accomplished what their goal was for selected list(s)
**Scenarios:**

- Normal Scenario: (Select a list to edit)
    - The user wants to edit a list so they chose the list they want to edit

    - Once the list is selected the user will choose the option to edit the list

    - The user will be able to edit the name of the list 

- Normal Scenario(Select a list to Delete)
    - The user wants to delete a list so they choose the list they want to delete 

    - Once the list is selected the user will choose the option to delete the list 

    - The list will then be deleted 

- Normal Scenario: (Select a list to view)
    - The user wants to view the contents in a list so they choose the list 

    - Once the list is chosen they can see the contents inside the list 

- Alternate Scenario: (Selecting multiple lists to delete)
    - The user wants to delete multiple lists so they select the option to select multiple

    - The user selects the lists they wish to delete 

    - Once all lists the user wants to delete are selected they press the delete button 

    - The lists are now deleted

- Alternate Scenario: (Select a list to view the contents to edit a reminder)

    - The user wants to edit a reminder from a list

    - The user will select a list to view its content 

    - The user will be shown reminders in that list 

    - The user will choose the reminder they want to edit 

    - The user will chose the option to edit the reminder 

    - The user will choose the from the options displayed such as change alert, change location, change name, change type it belongs to, or change repeat

    - Once the user edits what they need to they save the reminder

- Alternate Scenario: (Select a list to view the contents to delete a reminder)
    - The user wants to delete a reminder form a list so they select the list 

    - The user will be shown the contents of the list 

    - The user will choose the reminder 

    - The user will then choose the option to delete the reminder 

    - Once the option to delete is pressed the reminder will be deleted

- Alternate Scenario: (Select a list to view the contents to check-off reminder)
    - The user wants to check off a reminder when it has been completed 

    - The user will select the list in which the reminder belongs to 

    - The user will see the reminders in that list 

    - The user will choose the reminder  

    - The user will check off the reminder 

    - The reminder will not be deleted so it stays in the list but it is now checked off

- Alternate Scenario: (Select a list to view the contents to clear checked-off marks)
    - The user wants to clear all checked off marks so they select the list they want to do that in 

    - The user will then be shown the reminders which include both checked-off reminders and reminders that are not checked-off

    - The user will select the option to clear check-off reminders

    - The reminders will be cleared away from the list

    - The only remaining reminders will be the ones that are not checked-off
