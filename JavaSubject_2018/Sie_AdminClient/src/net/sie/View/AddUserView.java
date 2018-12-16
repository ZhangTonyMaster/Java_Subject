package net.sie.View;

import net.sie.Interface.ISqlMethod;
import net.sie.Model.User;
import net.sie.SocketClient.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddUserView {

    private User user;
    private ISqlMethod sqlMethod;
    private JFrame Frame;

    public AddUserView(User user,ISqlMethod sqlMethod){
        this.user = user;
        this.sqlMethod = sqlMethod;
    }

    public void initView() {
        Frame = new JFrame("添加用户操作界面");
        Frame.setBounds(400, 400, 800, 500);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setVisible(true);
        Container container = Frame.getContentPane();
        JPanel panel = new JPanel();
        panel.add(new JLabel("账号"));
        JTextField userName = new JTextField(10);
        panel.add(userName);
        panel.add(new JLabel("姓名"));
        JTextField realName = new JTextField(5);
        panel.add(realName);
        panel.add(new JLabel("账户类型:"));
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Vip用户");
        comboBox.addItem("普通用户");
        comboBox.setVisible(true);
        panel.add(comboBox);

        JPanel panel2 = new JPanel();
        JButton addUser = new JButton("创建");
        panel2.add(addUser);
        JButton back = new JButton("返回");
        panel2.add(back);

        container.add(panel,BorderLayout.WEST);
        container.add(panel2,BorderLayout.SOUTH);

        addUser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                user.setUserName(userName.getText());
                try {
                    user = sqlMethod.getUserInfo(user);
                    if (user.getTag() == 1){
                        JOptionPane.showMessageDialog(null, "此用户已经存在");
                    }else {
                        if(comboBox.getSelectedItem().equals("Vip用户")){
                            user.setAttribute("viper");
                        }else if(comboBox.getSelectedItem().equals("普通用户")){
                            user.setAttribute("normal");
                        }
                        user.setRealName(realName.getText());
                        user = sqlMethod.insertUserInfo(user);
                        if(user.getTag() == 1){
                            JOptionPane.showMessageDialog(null, "创建账号成功");
                        }else {
                            JOptionPane.showMessageDialog(null, "创建账号失败");
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminView(user,sqlMethod).initView();
                Frame.dispose();
                Frame = null;
            }
        });

    }
}
