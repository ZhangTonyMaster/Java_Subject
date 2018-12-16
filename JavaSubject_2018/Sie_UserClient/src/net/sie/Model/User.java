package net.sie.Model;

import java.io.Serializable;

public class User implements Serializable {

    private String userName;
    private String passWord;
    private String realName;
    private String attribute;
    private int account;
    private int tetrisLastScore;
    private int tetrisOptimalScore;
    private int tag;
    private String method;

    public User(){
        super();
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getTetrisLastScore() {
        return tetrisLastScore;
    }

    public void setTetrisLastScore(int tetrisLastScore) {
        this.tetrisLastScore = tetrisLastScore;
    }

    public int getTetrisOptimalScore() {
        return tetrisOptimalScore;
    }

    public void setTetrisOptimalScore(int tetrisOptimalScore) {
        this.tetrisOptimalScore = tetrisOptimalScore;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", passWord='").append(passWord).append('\'');
        sb.append(", realName='").append(realName).append('\'');
        sb.append(", attribute='").append(attribute).append('\'');
        sb.append(", account=").append(account);
        sb.append(", tetrisLastScore=").append(tetrisLastScore);
        sb.append(", tetrisOptimalScore=").append(tetrisOptimalScore);
        sb.append(", tag=").append(tag);
        sb.append(", method='").append(method).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
