package net.sie.Main;

import net.sie.Impl.SqlMethodImpl;
import net.sie.Interface.ISqlMethod;
import net.sie.Model.Admin;
import net.sie.Model.User;
import net.sie.SocketClient.Client;
import net.sie.View.AdminView;
import net.sie.View.LoginView;

import java.io.IOException;
import java.util.Scanner;

public class AdminClientMain {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入服务端主机");
        String host = scanner.nextLine();
        System.out.println("请输入服务端端口");
        int port = scanner.nextInt();

        ISqlMethod sqlMethod = new SqlMethodImpl(new Client(host,port));
        LoginView loginView = new LoginView(new Admin(),sqlMethod);
        loginView.initView();

    }
}
