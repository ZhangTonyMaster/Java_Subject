package net.sie.Impl;

import net.sie.Interface.ISqlMethod;
import net.sie.Model.Admin;
import net.sie.Model.Status;
import net.sie.Model.User;
import net.sie.SqLConnectUtil.SqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqlMethodImpl implements ISqlMethod {

    private SqlConnector sqlConnector;

    public SqlMethodImpl(SqlConnector sqlConnector) {
        this.sqlConnector = sqlConnector;
    }

    @Override
    public Admin getAdminInfo(Admin admin) throws SQLException, ClassNotFoundException {

        Connection conn = sqlConnector.getConnector();
        String sql = "select * from admin_info where _AdminName = '" + admin.getAdminName() + "'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        admin.setTag(0);
        if (rs.first()) {
            admin.setPassWord(rs.getString("_PassWord"));
            admin.setRealName(rs.getString("_RealName"));
            admin.setTag(1);
        }
        ps.close();
        conn.close();
        return admin;
    }

    @Override
    public User getUserInfo(User user) throws SQLException, ClassNotFoundException {

        Connection conn = sqlConnector.getConnector();
        String sql = "select * from user_info where _UserName = '" + user.getUserName() + "'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        user.setTag(0);
        if (rs.first()) {
            user.setTag(1);
            user.setPassWord(rs.getString("_PassWord"));
            user.setRealName(rs.getString("_RealName"));
            user.setAttribute(rs.getString("_Attribute"));
            user.setAccount(rs.getInt("_Account"));
            user.setTetrisLastScore(rs.getInt("_TetrisLastScore"));
            user.setTetrisOptimalScore(rs.getInt("_TetrisOptimalScore"));
        }
        ps.close();
        conn.close();
        return user;
    }

    @Override
    public User insertUserInfo(User user) throws SQLException, ClassNotFoundException {

        Connection conn = sqlConnector.getConnector();
        String sql = "insert into user_info(_UserName,_PassWord,_RealName,_Attribute,_Account,_TetrisLastScore,_TetrisOptimalScore) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getUserName());
        ps.setString(2, user.getUserName().substring(4, user.getUserName().length()));     //PassWord
        ps.setString(3, user.getRealName());
        ps.setString(4, user.getAttribute());
        ps.setInt(5, 0);
        ps.setInt(6, 0);
        ps.setInt(7, 0);
        int i = ps.executeUpdate();
        user.setTag(i);
        ps.close();
        conn.close();
        return user;
    }

    @Override
    public User updateUserAccount(User user) throws SQLException, ClassNotFoundException {

        int money = user.getAccount();
        user = getUserInfo(user);
        user.setAccount(user.getAccount() + money);

        Connection conn = sqlConnector.getConnector();
        String sql = "update user_info set _Account = '" + user.getAccount() + "' where _UserName = '" + user.getUserName() + "'";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = ps.executeUpdate();
        user.setTag(i);
        ps.close();
        conn.close();
        return user;
    }

    @Override
    public User autoUpdateUserAccount(User user) throws SQLException, ClassNotFoundException {

        user = getUserInfo(user);
        user.setAccount(user.getAccount() - 5);

        Connection conn = sqlConnector.getConnector();
        String sql = "update user_info set _Account = '" + user.getAccount() + "' where _UserName = '" + user.getUserName() + "'";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = ps.executeUpdate();
        user.setTag(i);
        ps.close();
        conn.close();
        return user;
    }

    @Override
    public User updateUserTetrisScore(User user) throws SQLException, ClassNotFoundException {

        int newOptimalScore = user.getTetrisOptimalScore();
        int newLastScore = user.getTetrisLastScore();
        user = getUserInfo(user);
        String sql = null;
        if(user.getTetrisOptimalScore() < newOptimalScore){
            sql = "update user_info set _TetrisLastScore = '" + newLastScore + "',_TetrisOptimalScore = + '" + newOptimalScore + "' where _UserName = '" + user.getUserName() + "'";
        }else {
            sql = "update user_info set _TetrisLastScore = '" + newLastScore + "' where _UserName = '" + user.getUserName() + "'";
        }

        Connection conn = sqlConnector.getConnector();
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = ps.executeUpdate();
        user.setTag(i);
        ps.close();
        conn.close();
        return user;
    }

    @Override
    public Status insertStatusInfo(Status status) throws SQLException, ClassNotFoundException {

        Connection conn = sqlConnector.getConnector();
        String sql = "insert into status_info(_UserName,_RealName,_OnLineTime,_DownLineTime,_Consumption,_Attribute,_IsOnLine) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, status.getUserName());
        ps.setString(2, status.getRealName());
        ps.setString(3, status.getOnLineTime());
        ps.setString(4, status.getOnLineTime());
        ps.setInt(5, 0);
        ps.setString(6, status.getAttribute());
        ps.setInt(7, status.getIsOnLine());
        int i = ps.executeUpdate();
        status.setTag(i);
        ps.close();
        conn.close();
        return status;
    }

    @Override
    public Status getStatusInfo(Status status) throws SQLException, ClassNotFoundException {

        Connection conn = sqlConnector.getConnector();
        String sql = "select * from status_info where _OnLineTime = '" + status.getOnLineTime() + "'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        status.setTag(0);
        if (rs.first()) {
            status.setTag(1);
            status.setConsumption(rs.getInt("_Consumption"));
            status.setAttribute(rs.getString("_Attribute"));
            status.setRealName(rs.getString("_RealName"));
            status.setOnLineTime(rs.getTimestamp("_OnLineTime"));
            status.setDownLineTime(rs.getTimestamp("_DownLineTime"));
            status.setUserName(rs.getString("_UserName"));
        }
        ps.close();
        conn.close();
        return status;
    }

    @Override
    public Status autoUpdateStatusConsumption(Status status) throws SQLException, ClassNotFoundException {

        status = getStatusInfo(status);
        status.setConsumption(status.getConsumption() + 5);
        Connection conn = sqlConnector.getConnector();
        String sql = "update status_info set _Consumption = '" + status.getConsumption() + "' where _OnLineTime = '" + status.getOnLineTime() + "'";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = ps.executeUpdate();
        status.setTag(i);
        ps.close();
        conn.close();
        return status;
    }

    @Override
    public Status updateStatusInfo(Status status) throws SQLException, ClassNotFoundException {

        Connection conn = sqlConnector.getConnector();
        String sql = "update status_info set _DownLineTime = '" + status.getDownLineTime() + "',_IsOnLine = '" + status.getIsOnLine() + "' where _OnLineTime = '" + status.getOnLineTime() + "'";
        PreparedStatement ps = conn.prepareStatement(sql);
        int i = ps.executeUpdate();
        status.setTag(i);
        ps.close();
        conn.close();
        return status;
    }

    @Override
    public ArrayList<Status> getOnLineInfo(Status status, ArrayList<Status> statusList) throws SQLException, ClassNotFoundException {

        Connection conn = sqlConnector.getConnector();
        String sql = "select * from status_info where _IsOnLine = 1";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            status = new Status();
            status.setRealName(rs.getString("_RealName"));
            status.setOnLineTime(rs.getTimestamp("_OnLineTime"));
            status.setAttribute(rs.getString("_Attribute"));
            status.setUserName(rs.getString("_UserName"));
            status.setConsumption(rs.getInt("_Consumption"));
            statusList.add(status);
        }
        for (int i = 0; i < statusList.size(); i++) {
            System.out.println(statusList.get(i).toString());
        }
        ps.close();
        conn.close();
        return statusList;
    }

    @Override
    public ArrayList<User> getAllUserInfo(User user, ArrayList<User> userList) throws SQLException, ClassNotFoundException {

        Connection conn = sqlConnector.getConnector();
        String sql = "select * from user_info";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            user = new User();
            user.setAttribute(rs.getString("_Attribute"));
            user.setUserName(rs.getString("_UserName"));
            user.setPassWord(rs.getString("_PassWord"));
            user.setAccount(rs.getInt("_Account"));
            user.setRealName(rs.getString("_RealName"));
            userList.add(user);
        }
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i).toString());
        }
        ps.close();
        conn.close();
        return userList;
    }

}
