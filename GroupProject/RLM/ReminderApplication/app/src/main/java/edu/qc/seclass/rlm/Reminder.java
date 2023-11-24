package edu.qc.seclass.rlm;

public class Reminder {
    private long id;
    private String content;
    private String listName;
    private String reminderType;
    private String reminder;
    private String day;
    private String time;


    public Reminder(String day, String time, String listName, String reminderType, String reminder){
        this.day = day;
        this.time = time;
        this.listName = listName;
        this.reminderType = reminderType;
        this.reminder = reminder;
    }

    public Reminder() {

    }

    public String getDay(){
        return day;
    }

    public String setDay(String day){
        this.day = day;
        return day;
    }

    public String getTime(){
        return time;
    }
    public String setTime(String time){
        this.time = time;
        return time;
    }

    public String getListName(){
        return listName;
    }

    public String setListName(String listName){
        this.listName = listName;
        return listName;
    }

    public String getReminderType(){
        return reminderType;
    }

    public String setReminderType(String reminderType){
        this.reminderType = reminderType;
        return reminderType;
    }

    public String getReminder(){
        return reminder;
    }
    public String setReminder(String reminder){
        this.reminder = reminder;
        return reminder;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getReminderTime() {
        return time;
    }

    public void setReminderTime(String reminderTime) {
        this.time = reminderTime;
    }

}
