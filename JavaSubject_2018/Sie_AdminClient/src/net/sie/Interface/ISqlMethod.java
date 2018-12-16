package net.sie.Interface;

import net.sie.Model.Admin;
import net.sie.Model.Status;
import net.sie.Model.User;

import java.io.IOException;
import java.util.ArrayList;

public interface ISqlMethod {

    Admin getAdminInfo(Admin admin) throws ClassNotFoundException, IOException;

    User getUserInfo(User user) throws ClassNotFoundException, IOException;

    User insertUserInfo(User user) throws ClassNotFoundException, IOException;

    ArrayList<Status> getOnLineInfo(Status status, ArrayList<Status> statusList) throws ClassNotFoundException, IOException;

    ArrayList<User> getAllUserInfo(User user, ArrayList<User> userList) throws ClassNotFoundException, IOException;
}
