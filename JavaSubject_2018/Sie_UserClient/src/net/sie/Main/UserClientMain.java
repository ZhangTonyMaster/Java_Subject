package net.sie.Main;

import net.sie.Impl.SqlMethodImpl;
import net.sie.Interface.ISqlMethod;
import net.sie.Model.Status;
import net.sie.Model.User;
import net.sie.SocketClient.Client;
import net.sie.View.LoginView;
import net.sie.View.TetrisView;
import net.sie.View.VipUserView;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.IOException;
import java.util.Scanner;


public class UserClientMain {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入服务端主机");
        String host = scanner.nextLine();
        System.out.println("请输入服务端端口号");
        int port = scanner.nextInt();

        ISqlMethod sqlMethod = new SqlMethodImpl(new Client(host, port));
        new LoginView(new User(), sqlMethod).initView();

//        new TetrisView();

    }
}
