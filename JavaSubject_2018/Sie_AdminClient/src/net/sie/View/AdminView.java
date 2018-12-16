package net.sie.View;

import net.sie.Interface.ISqlMethod;
import net.sie.Model.Status;
import net.sie.Model.User;
import net.sie.SocketClient.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class AdminView {

    private User user;
    private ISqlMethod sqlMethod;
    private JFrame Frame;

    public AdminView(User user,ISqlMethod sqlMethod){
        this.user = user;
        this.sqlMethod = sqlMethod;
    }

    public void initView(){
        Frame = new JFrame("管理员操作界面");
        Frame.setBounds(400, 400, 800, 500);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setVisible(true);
        Container container = Frame.getContentPane();

        JPanel panel = new JPanel();
        JButton addUser = new JButton("添加新用户");
        panel.add(addUser);
        JButton searchUser = new JButton("查询已注册用户");
        panel.add(searchUser);
        JButton searchStatus = new JButton("查询在线用户状态");
        panel.add(searchStatus);

        JPanel panel2 = new JPanel();
        ImageIcon cat1_gif = new ImageIcon("H:\\JavaSubject_2018\\Sie_AdminClient\\recourse\\Cat1.GIF");
        panel2.add(new JLabel(cat1_gif));

        container.add(panel,BorderLayout.NORTH);
        container.add(panel2,BorderLayout.CENTER);

        searchUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserInfoView(user,sqlMethod).initView();
                Frame.dispose();
                Frame = null;
            }
        });

        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddUserView(user,sqlMethod).initView();
                Frame.dispose();
                Frame = null;
            }
        });

        searchStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StatusInfoView(new Status(),sqlMethod).initView();
                Frame.dispose();
                Frame = null;
            }
        });
    }
}
