package net.sie.SocketClient;

import net.sie.Model.Admin;
import net.sie.Model.Status;
import net.sie.Model.User;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    private String host;
    private int port;

    public Client(String host,int port){
        this.host = host;
        this.port = port;
    }

    public Admin transferAdmin(Admin admin) throws IOException, ClassNotFoundException {

        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            socket = new Socket(host, port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

            System.out.println(admin.toString());
            oos.writeObject(admin);
            oos.flush();

            Object obj = ois.readObject();
            admin = (Admin) obj;
            System.out.println(admin.toString());
            System.out.println("-----------------------");
            return admin;
        } finally {
            try {
                ois.close();
            } catch (Exception e) {}
            try {
                oos.close();
            } catch (Exception e) {}
            try {
                socket.close();
            } catch (Exception e) {}
        }
    }

    public User transferUser(User user) throws IOException, ClassNotFoundException {

        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            socket = new Socket(host, port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

            System.out.println(user.toString());
            oos.writeObject(user);
            oos.flush();

            Object obj = ois.readObject();
            user = (User) obj;
            System.out.println(user.toString());
            System.out.println("-----------------------");
            return user;
        } finally {
            try {
                ois.close();
            } catch (Exception e) {}
            try {
                oos.close();
            } catch (Exception e) {}
            try {
                socket.close();
            } catch (Exception e) {}
        }
    }

    public ArrayList<Status> transferStatusList(Status status) throws IOException, ClassNotFoundException {

        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            socket = new Socket(host, port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

            System.out.println(status.toString());
            oos.writeObject(status);
            oos.flush();

            ArrayList<Status> arrayList = (ArrayList<Status>) ois.readObject();
            return arrayList;
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
            try {
                oos.close();
            } catch (Exception e) {
            }
            try {
                socket.close();
            } catch (Exception e) {
            }
        }
    }

    public ArrayList<User> transferUserList(User user) throws IOException, ClassNotFoundException {

        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            socket = new Socket(host, port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

            System.out.println(user.toString());
            oos.writeObject(user);
            oos.flush();

            ArrayList<User> arrayList = (ArrayList<User>) ois.readObject();
            return arrayList;
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
            try {
                oos.close();
            } catch (Exception e) {
            }
            try {
                socket.close();
            } catch (Exception e) {
            }
        }
    }
}
