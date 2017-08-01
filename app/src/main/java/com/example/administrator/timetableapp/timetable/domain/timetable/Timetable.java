package com.example.administrator.timetableapp.timetable.domain.timetable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/07/25.
 */

public class Timetable implements Serializable {
    private String scheduledActivity1;
    private String scheduledActivity2;
    private String scheduledActivity3;
    private String scheduledActivity4;
    private String scheduledActivity5;
    private Long timeID;

    private String Slot1 = "08:30";
    private String Slot2 = "09:40";
    private String Slot3 = "10:50";
    private String Slot4 = "12:00";
    private String Slot5 = "14:20";

    public Timetable(TimetableBuilder timetableBuilder){
        this.timeID = timetableBuilder.timeID;
        this.scheduledActivity1 = timetableBuilder.scheduledActivity1;
        this.scheduledActivity2 = timetableBuilder.scheduledActivity2;
        this.scheduledActivity3 = timetableBuilder.scheduledActivity3;
        this.scheduledActivity4 = timetableBuilder.scheduledActivity4;
        this.scheduledActivity5 = timetableBuilder.scheduledActivity5;
    }

    public String getScheduledActivity1() {
        return scheduledActivity1;
    }

    public String getScheduledActivity2() {
        return scheduledActivity2;
    }

    public String getScheduledActivity3() {
        return scheduledActivity3;
    }

    public String getScheduledActivity4() {
        return scheduledActivity4;
    }

    public String getScheduledActivity5() {
        return scheduledActivity5;
    }

    public String getSlot1() {
        return Slot1;
    }

    public String getSlot2() {
        return Slot2;
    }

    public String getSlot3() {
        return Slot3;
    }

    public String getSlot4() {
        return Slot4;
    }

    public String getSlot5() {
        return Slot5;
    }

    public Long getTimeID() {
        return timeID;
    }

    public static class TimetableBuilder{
        String scheduledActivity1;
        String scheduledActivity2;
        String scheduledActivity3;
        String scheduledActivity4;
        String scheduledActivity5;
        Long timeID;

        String Slot1 = "08:30";
        String Slot2 = "09:40";
        String Slot3 = "10:50";
        String Slot4 = "12:00";
        String Slot5 = "14:20";

        public TimetableBuilder setTimeID(Long timeID) {
            this.timeID = timeID;
            return this;
        }

        public TimetableBuilder setScheduledActivity1(String scheduledActivity1) {
            this.scheduledActivity1 = scheduledActivity1;
            return this;
        }

        public TimetableBuilder setScheduledActivity2(String scheduledActivity2) {
            this.scheduledActivity2 = scheduledActivity2;
            return this;
        }

        public TimetableBuilder setScheduledActivity3(String scheduledActivity3) {
            this.scheduledActivity3 = scheduledActivity3;
            return this;
        }

        public TimetableBuilder setScheduledActivity4(String scheduledActivity4) {
            this.scheduledActivity4 = scheduledActivity4;
            return this;
        }

        public TimetableBuilder setScheduledActivity5(String scheduledActivity5) {
            this.scheduledActivity5 = scheduledActivity5;
            return  this;
        }
        public TimetableBuilder copy(Timetable timetable){
            this.timeID = timetable.timeID;
            this.scheduledActivity1 = timetable.scheduledActivity1;
            this.scheduledActivity2 = timetable.scheduledActivity2;
            this.scheduledActivity3 = timetable.scheduledActivity3;
            this.scheduledActivity4 = timetable.scheduledActivity4;
            this.scheduledActivity5 = timetable.scheduledActivity5;
            return this;
        }
        public Timetable build(){

            return new Timetable(this);
        }






    }


}
