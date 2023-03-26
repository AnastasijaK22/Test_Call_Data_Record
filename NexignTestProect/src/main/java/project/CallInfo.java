package project;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CallInfo {
    private String numberPhone;
    private Date startCall;
    private Date finishCall;
    private Rate rate;
    private CallType callType;

    public String getNumberPhone() {
        return this.numberPhone;
    }

    public Date getStartCall() {
        return this.startCall;
    }

    public Date getFinishCall() {
        return this.finishCall;
    }

    public Rate getRate() {
        return this.rate;
    }

    public CallType getCallType() {
        return this.callType;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public void setStartCall(Date startCall) {
        this.startCall = startCall;
    }

    public void setFinishCall(Date finishCall) {
        this.finishCall = finishCall;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public void setCallType(CallType callType) {
        this.callType = callType;
    }

    public long getPeriodSeconds() {
        if (startCall == null || finishCall == null) {
            return 0;
        }
        long diff = finishCall.getTime() - startCall.getTime();
        return TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public long getPeriodMinute() {
        if (startCall == null || finishCall == null) {
            return 0;
        }
        long diff = finishCall.getTime() - startCall.getTime();
        return TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);
    }

    public String getPeriod() {
        if (startCall == null || finishCall == null) {
            return "00:00:00";
        }
        long diff = finishCall.getTime() - startCall.getTime();
        long seconds =  TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS);
        long hour = (seconds / 60) / 60;
        long minute = (seconds / 60) % 60;
        long second = seconds % 60;
        String result = "";
        if (hour < 10) {
            result += "0";
        }
        result += Long.toString(hour) + ":";
        if (minute < 10) {
            result += "0";
        }
        result += Long.toString(minute) + ":";
        if (second < 10) {
            result += "0";
        }
        result += Long.toString(second);
        return result;
    }

}
