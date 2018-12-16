package net.sie.View;

import net.sie.Interface.ISqlMethod;
import net.sie.Model.Status;
import net.sie.Model.User;
import net.sie.Monitor.AutoNormalThread;
import net.sie.Monitor.AutoVipThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginView {

    private User user;
    private ISqlMethod sqlMethod;
    private JFrame Frame;

    public LoginView(User user, ISqlMethod sqlMethod) {
        this.user = user;
        this.sqlMethod = sqlMethod;
    }

    public void initView(){

        Frame = new JFrame("用户登陆界面");
        Frame.setBounds(400, 400, 800, 500);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setVisible(true);
        Container container = Frame.getContentPane();
        JPanel panel = new JPanel();
        panel.add(new JLabel("用户名"));
        JTextField userName = new JTextField(10);
        panel.add(userName);
        panel.add(new JLabel("密码"));
        JTextField passWord = new JTextField(10);
        panel.add(passWord);
        JButton login = new JButton("登陆");
        panel.add(login);
        JButton reset = new JButton("重置");
        panel.add(reset);

        JPanel panel2 = new JPanel();
        JButton recharge = new JButton("进入充值页面");
        panel2.add(recharge);

        container.add(panel, BorderLayout.NORTH);
        container.add(panel2, BorderLayout.SOUTH);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    user.setUserName(userName.getText());
                    user = sqlMethod.getUserInfo(user);
                    if(user.getTag() == 1 && user.getPassWord().equals(passWord.getText())){
                        if(user.getAccount() == 0){
                            JOptionPane.showMessageDialog(null, "余额不足");
                        }else {
                            if(user.getAttribute().equals("normal")){
                                Frame.dispose();
                                Frame = null;
                                NormalUserView normalUserView = new NormalUserView(user,new Status(),sqlMethod);
                                normalUserView.initView();
                                new Thread(new AutoNormalThread(normalUserView)).start();
                            }else if(user.getAttribute().equals("viper")){
                                Frame.dispose();
                                Frame = null;
                                VipUserView vipUserView = new VipUserView(user,new Status(),sqlMethod);
                                vipUserView.initView();
                                new Thread(new AutoVipThread(vipUserView)).start();
                            }
                        }
                    }else if(user.getTag() == 0){
                        JOptionPane.showMessageDialog(null, "不存在此用户");
                    }else if(user.getTag() == 1 && !user.getPassWord().equals(passWord.getText())){
                        JOptionPane.showMessageDialog(null, "密码错误");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        recharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RechargeView(user,sqlMethod).initView();
                Frame.dispose();
                Frame = null;
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName.setText("");
                passWord.setText("");
            }
        });
    }

}
