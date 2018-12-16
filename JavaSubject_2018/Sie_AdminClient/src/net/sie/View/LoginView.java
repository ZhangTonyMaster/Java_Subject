package net.sie.View;

import net.sie.Interface.ISqlMethod;
import net.sie.Model.Admin;
import net.sie.Model.User;
import net.sie.SocketClient.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginView {

    private Admin admin;
    private ISqlMethod sqlMethod;

    public LoginView(Admin admin,ISqlMethod sqlMethod) throws IOException {
        this.admin = admin;
        this.sqlMethod = sqlMethod;
    }

    public void initView(){

        JFrame loginFrame = new JFrame("管理员登陆界面");
        loginFrame.setBounds(400, 400, 800, 500);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
        Container container = loginFrame.getContentPane();
        JPanel panel = new JPanel();
        panel.add(new JLabel("用户名"));
        JTextField adminName = new JTextField(10);
        panel.add(adminName);
        panel.add(new JLabel("密码"));
        JTextField passWord = new JTextField(10);
        panel.add(passWord);
        JButton login = new JButton("登陆");
        panel.add(login);
        JButton cancel = new JButton("重置");
        panel.add(cancel);
        container.add(panel, BorderLayout.NORTH);

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.setVisible(false);
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    admin.setAdminName(adminName.getText());
                    admin = sqlMethod.getAdminInfo(admin);
                    if(admin.getTag() == 1 && admin.getPassWord().equals(passWord.getText())){
                        new AdminView(new User(),sqlMethod).initView();
                        loginFrame.setVisible(false);
                    }else if(admin.getTag() == 0){
                        JOptionPane.showMessageDialog(null, "不存在此用户");
                    }else if(admin.getTag() == 1 && !admin.getPassWord().equals(passWord.getText())){
                        JOptionPane.showMessageDialog(null, "密码错误");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }
}
