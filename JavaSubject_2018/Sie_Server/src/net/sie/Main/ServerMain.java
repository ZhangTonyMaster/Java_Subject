package net.sie.Main;

import net.sie.Impl.SqlMethodImpl;
import net.sie.Interface.ISqlMethod;
import net.sie.SocketServer.Server;
import net.sie.SqLConnectUtil.SqlConnector;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.Scanner;


public class ServerMain {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入服务端监听的端口");
        int port = scanner.nextInt();

        ISqlMethod sqlMethod = new SqlMethodImpl(new SqlConnector());
        Server server = new Server(new ServerSocket(port), sqlMethod);
        new Thread(server).start();

    }
}
