package net.sie.View;

import net.sie.Interface.ISqlMethod;
import net.sie.Model.User;
import net.sie.SocketClient.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RechargeView {

    private User user;
    private ISqlMethod sqlMethod;
    private JFrame Frame;

    public RechargeView(User user, ISqlMethod sqlMethod) {
        this.user = user;
        this.sqlMethod = sqlMethod;
    }

    public void initView() {
        Frame = new JFrame("充值界面");
        Frame.setBounds(400, 400, 800, 500);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setVisible(true);
        Container container = Frame.getContentPane();

        JPanel panel = new JPanel();
        panel.add(new JLabel("账号"));
        JTextField userName = new JTextField(10);
        panel.add(userName);

        panel.add(new JLabel("充值金额"));
        JTextField money = new JTextField(5);
        panel.add(money);

        JPanel panel2 = new JPanel();
        JButton recharge = new JButton("充值");
        panel2.add(recharge);
        JButton reset = new JButton("重置");
        panel2.add(reset);
        JButton back = new JButton("返回登陆界面");
        panel2.add(back);

        container.add(panel, BorderLayout.WEST);
        container.add(panel2, BorderLayout.SOUTH);

        recharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                user.setUserName(userName.getText());
                try {
                    user = sqlMethod.getUserInfo(user);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                if (user.getTag() == 1) {
                    user.setAccount(Integer.parseInt(money.getText()));
                    try {
                        user = sqlMethod.updateUserAccount(user);
                        if (user.getTag() == 1) {
                            JOptionPane.showMessageDialog(null, "充值成功");
                        } else {
                            JOptionPane.showMessageDialog(null, "充值失败");
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName.setText("");
                money.setText("");
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginView(user, sqlMethod).initView();
                Frame.dispose();
                Frame = null;
            }
        });
    }
}
