package net.sie.Interface;

import net.sie.Model.Admin;
import net.sie.Model.Status;
import net.sie.Model.User;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ISqlMethod {

    Admin getAdminInfo(Admin admin) throws SQLException, ClassNotFoundException;

    User getUserInfo(User user) throws SQLException, ClassNotFoundException;

    User insertUserInfo(User user) throws SQLException, ClassNotFoundException;

    User updateUserAccount(User user) throws SQLException, ClassNotFoundException;

    User autoUpdateUserAccount(User user) throws SQLException, ClassNotFoundException;

    User updateUserTetrisScore(User user) throws SQLException, ClassNotFoundException;

    Status insertStatusInfo(Status status) throws SQLException, ClassNotFoundException;

    Status getStatusInfo(Status status) throws SQLException, ClassNotFoundException;

    Status autoUpdateStatusConsumption(Status status) throws SQLException, ClassNotFoundException;

    Status updateStatusInfo(Status status) throws SQLException, ClassNotFoundException;

    ArrayList<Status> getOnLineInfo(Status status, ArrayList<Status> statusList) throws SQLException, ClassNotFoundException;

    ArrayList<User> getAllUserInfo(User user, ArrayList<User> userList) throws SQLException, ClassNotFoundException;


}
