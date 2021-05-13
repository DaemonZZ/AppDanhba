package com.contact;

//CallLogs Model
public class Call_Log {
    private String number;
    private String duration;
    private String time;

    public Call_Log() {
    }

    public Call_Log(String number, String duration, String time) {
        this.number = number;
        this.duration = duration;
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
