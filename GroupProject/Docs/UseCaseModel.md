# Use Case Model

**Author**: Group 5

#### Version 2: Added, deleted, and edited scenarios based on the *current* app model. The use cases expand the further into the app the user goes as shown in the use case diagram. 


## 1 Use Case Diagram
<img src="https://i.ibb.co/n7xv4K6/Use-Case-Diagram-1.png">




## 2 Use Case Descriptions




## Create List

- Requirements: Allows the user to make a list that will be added to a list of lists

- Pre-conditions: User must have the app open on the main screen

- Post-conditions: The user should see the added to the list of lists and can now add reminders into that list 

**Scenarios:**

- Normal Scenario:
	- The user selects the option to create a new list
  
	- The user is prompted to name the list
  
	- The list is added to the list of lists

- Alternate Scenario: (No list)
	- The user does not want to create a new reminder list
	- The user does nothing but if there is no list then the user is not able to add reminders
	- If the user wants to add reminders there needs to be a list first 

- Exceptional Scenario: (No list name)
	- The user selects the option to create a list
	- The user does not want to name the list and wants to save the list with no name
	- There is an error message not allowing the user to enter an empty name as a list name
	- The user is sent back to the main screen
	- The user can choose to create a list again or not


## Selecting List

- Requirements: Allows the user to select a list and many things with that selected list

- Pre-conditions: User must have app open and be in the main screen

- Post-conditions: User will have either added a reminder to a list, deleted a list, deleted a reminder, checked off reminder, clear checked off, or view contents in selected list.

**Scenarios:**

- Normal Scenario: (Select a list to view)
	- The user wants to view the contents in a list so they choose the list 
	- Once the list is chosen they can see the reminders inside the selected list 
	
- Normal Scenario(Select a list to Delete)
	- The user wants to delete a list so they choose the list they want to delete by tapping the three dots 
	- Once the list is selected the user will choose the option to delete the list 
	- The list will then be deleted 
	- Once the option to delete is pressed the list will be deleted from the list of lists
	
- Alternate Scenario: (Select a list to view the contents to delete a reminder)
	- The user wants to delete a reminder from a list so they select the list 
	- The user will be shown the reminders of the selected list 
	- The user will choose the reminder by tapping the three dots
	- The user will then choose the option to delete the reminder 
	- Once the option to delete is pressed the reminder will be deleted from the list of reminders

- Alternate Scenario: (Select a list to view the contents to check-off reminder)
	- The user wants to check off a reminder
	- The user will select the list in which the reminder belongs to 
	- The user will see the reminders in that list  
	- The user will check off the reminder box
	- The reminder will not be deleted so it stays in the list but it is now checked off

- Alternate Scenario: (Select a list to view the contents to clear checked-off marks)
	- The user wants to clear all checked-off marks so they select the list they want to do that in 
	- The user will then be shown the reminders which include both checked-off reminders and reminders that are not checked-off
	- The user will select the option to clear check-off reminders
	- The reminders with the check-off will no longer have the check 
	- All reminders in that list will not be checked-off 

- Alternate Scenario: (Select a list to add a reminder)
	- The user selects the list they want to add a reminder in 
	- User selects the option to create a reminder
	- User will be prompted to add a name 
	- User will save the reminder and the reminder will be added to the list 

- Alternate Scenario: (Selecting a list to add reminder with reminder type)
	- User will select the list they want to add a reminder with a reminder type to 
	- User selects the option to create a reminder
	- User is prompted to write the reminder name and select a reminder type
	- After selecting the user will save the reminder and it will be displayed in the list

- Alternate Scenario: (Selecting a list to add reminder with reminder type, and date)
	- User will select the list they want to add a reminder with a reminder type to 
	- User selects the option to create a reminder
	- User is prompted to write the reminder name and select a reminder type
	- User wants to add a date so they toggle on the day alert feature 
	- User will select the date 
	- After selecting the user will save the reminder and it will be displayed in the list

- Alternate Scenario: (Selecting a list to add reminder with reminder type, date, and time)
	- User will select the list they want to add a reminder with a reminder type to 
	- User selects the option to create a reminder
	- User is prompted to write the reminder name and select a reminder type
	- User wants to add a date so they toggle on the day alert feature 
	- User will select the date 
	- After selecting the user will save the reminder and it will be displayed in the list

- Exceptional Scenario: (No name for reminder)
	- The user wants to create a reminder so they select the list in which they want the reminder in
	- The user selects the option to create a reminder
	- The user will select what they want but will not add a reminder name
	- The user will then save the reminder with no name 
	- The user will be prompted with an error that states the reminder name can not be empty 
	- The user is then sent back to the list of reminders screen and has the option to add a reminder or not