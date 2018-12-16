package net.sie.Model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Status implements Serializable {

    private DateFormat dateFormat;
    private String userName;
    private String realName;
    private String onLineTime;
    private String downLineTime;
    private int consumption;
    private String attribute;
    private int isOnLine;
    private int tag;
    private String method;

    public Status() {
        super();
        this.dateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public int getIsOnLine() {
        return isOnLine;
    }

    public void setIsOnLine(int isOnLine) {
        this.isOnLine = isOnLine;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getOnLineTime() {
        return onLineTime;
    }

    public void setOnLineTime(Date onLineTime) {
        this.onLineTime = dateFormat.format(onLineTime);
    }

    public String getDownLineTime() {
        return downLineTime;
    }

    public void setDownLineTime(Date downLineTime) {
        this.downLineTime = dateFormat.format(downLineTime);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Status{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", realName='").append(realName).append('\'');
        sb.append(", onLineTime='").append(onLineTime).append('\'');
        sb.append(", downLineTime='").append(downLineTime).append('\'');
        sb.append(", consumption=").append(consumption);
        sb.append(", attribute='").append(attribute).append('\'');
        sb.append(", isOnLine=").append(isOnLine);
        sb.append(", tag=").append(tag);
        sb.append(", method='").append(method).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
