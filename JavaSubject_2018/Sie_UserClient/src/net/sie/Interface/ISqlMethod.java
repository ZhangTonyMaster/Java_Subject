package net.sie.Interface;

import net.sie.Model.Status;
import net.sie.Model.Trade;
import net.sie.Model.User;

import java.io.IOException;
import java.sql.SQLException;

public interface ISqlMethod {

    User getUserInfo(User user) throws IOException, ClassNotFoundException;

    User updateUserAccount(User user) throws IOException, ClassNotFoundException;

    User autoUpdateUserAccount(User user) throws IOException, ClassNotFoundException;

    User updateUserTetrisScore(User user) throws IOException, ClassNotFoundException;

    Status insertStatusInfo(Status status, User user) throws IOException, ClassNotFoundException;

    Status getStatusInfo(Status status) throws IOException, ClassNotFoundException;

    Status autoUpdateStatusConsumption(Status status) throws IOException, ClassNotFoundException;

    Status updateStatusInfo(Status status) throws IOException, ClassNotFoundException;

}
