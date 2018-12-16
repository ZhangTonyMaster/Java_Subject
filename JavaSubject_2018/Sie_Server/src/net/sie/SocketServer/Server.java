package net.sie.SocketServer;

import net.sie.Interface.ISqlMethod;
import net.sie.Model.Admin;
import net.sie.Model.Status;
import net.sie.Model.User;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class Server implements Runnable {

    private ServerSocket serverSocket;
    private ISqlMethod sqlMethod;

    public Server(ServerSocket serverSocket, ISqlMethod sqlMethod) {
        this.serverSocket = serverSocket;
        this.sqlMethod = sqlMethod;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                dealRequsetObject(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dealRequsetObject(Socket socket) throws IOException, ClassNotFoundException, SQLException {

        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;

        try {
            ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            oos = new ObjectOutputStream(socket.getOutputStream());
            Object object = ois.readObject();

            if (object.getClass().getName().equals(Admin.class.getName())) {
                Admin admin = (Admin) object;
                System.out.println("传入服务端" + admin.toString());
                if(admin.getMethod().equals("getAdminInfo")){
                    admin = sqlMethod.getAdminInfo(admin);
                    System.out.println("服务端传出" + admin.toString());
                    oos.writeObject(admin);
                    oos.flush();
                }
            } else if (object.getClass().getName().equals(User.class.getName())) {
                User user = (User) object;
                System.out.println("传入服务端" + user.toString());
                if (user.getMethod().equals("getUserInfo")) {
                    user = sqlMethod.getUserInfo(user);
                    System.out.println("服务端传出" + user.toString());
                    oos.writeObject(user);
                    oos.flush();
                } else if (user.getMethod().equals("insertUserInfo")) {
                    user = sqlMethod.insertUserInfo(user);
                    System.out.println("服务端传出" + user.toString());
                    oos.writeObject(user);
                    oos.flush();
                }else if(user.getMethod().equals("updateUserAccount")){
                    user = sqlMethod.updateUserAccount(user);
                    System.out.println("服务端传出" + user.toString());
                    oos.writeObject(user);
                    oos.flush();
                } else if(user.getMethod().equals("autoUpdateUserAccount")){
                    user = sqlMethod.autoUpdateUserAccount(user);
                    System.out.println("服务端传出" + user.toString());
                    oos.writeObject(user);
                    oos.flush();
                }else if(user.getMethod().equals("getAllUserInfo")){
                    ArrayList<User> arrayList = new ArrayList<>();
                    arrayList = sqlMethod.getAllUserInfo(user,arrayList);
                    oos.writeObject(arrayList);
                    oos.flush();
                }else if(user.getMethod().equals("updateUserTetrisScore")){
                    user = sqlMethod.updateUserTetrisScore(user);
                    System.out.println("服务端传出" + user.toString());
                    oos.writeObject(user);
                    oos.flush();
                }
            }else if(object.getClass().getName().equals(Status.class.getName())){
                Status status = (Status) object;
                System.out.println("传入服务端" + status.toString());
                if(status.getMethod().equals("insertStatusInfo")){
                    status = sqlMethod.insertStatusInfo(status);
                    System.out.println("服务端传出" + status.toString());
                    oos.writeObject(status);
                    oos.flush();
                }else if(status.getMethod().equals("updateStatusInfo")){
                    status = sqlMethod.updateStatusInfo(status);
                    System.out.println("服务端传出" + status.toString());
                    oos.writeObject(status);
                    oos.flush();
                }else if(status.getMethod().equals("getStatusInfo")){
                    status = sqlMethod.getStatusInfo(status);
                    System.out.println("服务端传出" + status.toString());
                    oos.writeObject(status);
                    oos.flush();
                }else if(status.getMethod().equals("getOnLineInfo")){
                    ArrayList<Status> arrayList = new ArrayList<>();
                    arrayList = sqlMethod.getOnLineInfo(status,arrayList);
                    oos.writeObject(arrayList);
                    oos.flush();
                }else if(status.getMethod().equals("autoUpdateStatusConsumption")){
                    status = sqlMethod.autoUpdateStatusConsumption(status);
                    System.out.println("服务端传出" + status.toString());
                    oos.writeObject(status);
                    oos.flush();
                }
            }
        } finally {
            try {
                ois.close();
            } catch (Exception ex) {
            }
            try {
                oos.close();
            } catch (Exception ex) {
            }
            try {
                socket.close();
            } catch (Exception ex) {
            }
        }
    }
}
