package net.sie.Impl;

import net.sie.Interface.ISqlMethod;
import net.sie.Model.Status;
import net.sie.Model.Trade;
import net.sie.Model.User;
import net.sie.SocketClient.Client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class SqlMethodImpl implements ISqlMethod {

    private Client client;

    public SqlMethodImpl(Client client) {
        this.client = client;
    }

    @Override
    public User getUserInfo(User user) throws IOException, ClassNotFoundException {
        user.setMethod("getUserInfo");
        user = client.transferUser(user);
        return user;
    }

    @Override
    public User updateUserAccount(User user) throws IOException, ClassNotFoundException {
        user.setMethod("updateUserAccount");
        user = client.transferUser(user);
        return user;
    }

    @Override
    public User autoUpdateUserAccount(User user) throws IOException, ClassNotFoundException {
        user.setMethod("autoUpdateUserAccount");
        user = client.transferUser(user);
        return user;
    }

    @Override
    public User updateUserTetrisScore(User user) throws IOException, ClassNotFoundException {
        user.setMethod("updateUserTetrisScore");
        user = client.transferUser(user);
        return user;
    }

    @Override
    public Status insertStatusInfo(Status status, User user) throws IOException, ClassNotFoundException {

        user = getUserInfo(user);
        Date onLineTime = new Date();
        status.setMethod("insertStatusInfo");
        status.setUserName(user.getUserName());
        status.setRealName(user.getRealName());
        status.setOnLineTime(onLineTime);
        status.setDownLineTime(onLineTime);
        status.setAttribute(user.getAttribute());
        status.setIsOnLine(1);
        status = client.transferStatus(status);
        return status;
    }

    @Override
    public Status getStatusInfo(Status status) throws IOException, ClassNotFoundException {
        status.setMethod("getStatusInfo");
        status = client.transferStatus(status);
        return status;
    }

    @Override
    public Status autoUpdateStatusConsumption(Status status) throws IOException, ClassNotFoundException {
        status.setMethod("autoUpdateStatusConsumption");
        status = client.transferStatus(status);
        return status;
    }

    @Override
    public Status updateStatusInfo(Status status) throws IOException, ClassNotFoundException {
        status.setMethod("updateStatusInfo");
        Date downLineTime = new Date();
        status.setIsOnLine(0);
        status.setDownLineTime(downLineTime);
        status = client.transferStatus(status);
        return status;
    }

}
