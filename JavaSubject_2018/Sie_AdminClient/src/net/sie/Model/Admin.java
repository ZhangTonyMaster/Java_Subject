package net.sie.Model;

import java.io.Serializable;

public class Admin implements Serializable {

    private int tag;
    private String method;
    private String adminName;
    private String passWord;
    private String realName;

    public Admin(){
        super();
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

    public String getAdminName() {
        return adminName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getRealName() {
        return realName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Admin{");
        sb.append("tag=").append(tag);
        sb.append(", method='").append(method).append('\'');
        sb.append(", adminName='").append(adminName).append('\'');
        sb.append(", passWord='").append(passWord).append('\'');
        sb.append(", realName='").append(realName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
