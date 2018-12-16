package net.sie.SocketClient;

import net.sie.Model.Status;
import net.sie.Model.Trade;
import net.sie.Model.User;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private String host;
    private int port;

    public Client(String host,int port){
        this.host = host;
        this.port = port;
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

    public Status transferStatus(Status status) throws IOException, ClassNotFoundException {

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

            Object obj = ois.readObject();
            status = (Status) obj;
            System.out.println(status.toString());
            return status;
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

    public Trade transferGoods(Trade trade) throws IOException, ClassNotFoundException {

        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            socket = new Socket(host, port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

            System.out.println(trade.getMethod());
            oos.writeObject(trade);
            oos.flush();

            Object obj = ois.readObject();
            trade = (Trade) obj;
            System.out.println(trade.getMethod());
            return trade;
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
