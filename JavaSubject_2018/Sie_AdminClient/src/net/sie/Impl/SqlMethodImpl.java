package net.sie.Impl;

import net.sie.Interface.ISqlMethod;
import net.sie.Model.Admin;
import net.sie.Model.Status;
import net.sie.Model.User;
import net.sie.SocketClient.Client;

import java.io.IOException;
import java.util.ArrayList;

public class SqlMethodImpl implements ISqlMethod {

    private Client client;

    public SqlMethodImpl(Client client) {
        this.client = client;
    }

    @Override
    public Admin getAdminInfo(Admin admin) throws ClassNotFoundException, IOException {
        admin.setMethod("getAdminInfo");
        admin = client.transferAdmin(admin);
        return admin;
    }

    @Override
    public User getUserInfo(User user) throws ClassNotFoundException, IOException {
        user.setMethod("getUserInfo");
        user = client.transferUser(user);
        return user;
    }

    @Override
    public User insertUserInfo(User user) throws ClassNotFoundException, IOException {
        user.setMethod("insertUserInfo");
        user = client.transferUser(user);
        return user;
    }

    @Override
    public ArrayList<Status> getOnLineInfo(Status status, ArrayList<Status> statusList) throws ClassNotFoundException, IOException {
        status.setMethod("getOnLineInfo");
        statusList = client.transferStatusList(status);
        return statusList;
    }

    @Override
    public ArrayList<User> getAllUserInfo(User user, ArrayList<User> userList) throws ClassNotFoundException, IOException {
        user.setMethod("getAllUserInfo");
        userList = client.transferUserList(user);
        return userList;
    }
}
