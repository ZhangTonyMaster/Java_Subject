package net.sie.View;

import net.sie.Interface.ISqlMethod;
import net.sie.Model.Status;
import net.sie.Model.User;
import net.sie.Monitor.AutoTetrisThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VipUserView {

    private User user;
    private ISqlMethod sqlMethod;
    private Status status;
    private JFrame Frame;

    public VipUserView(User user, Status status, ISqlMethod sqlMethod) {
        this.user = user;
        this.sqlMethod = sqlMethod;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public ISqlMethod getSqlMethod() {
        return sqlMethod;
    }

    public JFrame getFrame() {
        return Frame;
    }

    public Status getStatus() {
        return status;
    }

    public void initView() {

        Frame = new JFrame("会员用户界面");
        Frame.setBounds(400, 400, 800, 500);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setVisible(true);
        Container container = Frame.getContentPane();

        JPanel panel = new JPanel();
        panel.add(new JLabel("上网导航:"));
        JButton FireFox = new JButton("刷网页");
        panel.add(FireFox);
        JButton QQmusic = new JButton("听音乐");
        panel.add(QQmusic);
        JButton Thunder = new JButton("看电影");
        panel.add(Thunder);
        JButton Steam = new JButton("打游戏");
        panel.add(Steam);
        JButton Tetis = new JButton("俄罗斯方块");
        panel.add(Tetis);

        JPanel panel2 = new JPanel();
        JButton account = new JButton("查看余额");
        panel2.add(account);
        JButton exit = new JButton("退出登陆");
        panel2.add(exit);
        JButton renew = new JButton("续费");
        panel2.add(renew);

        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("续费金额"));
        JTextField money = new JTextField(5);
        panel3.add(money);

        JPanel panel4 = new JPanel();
        ImageIcon cat1_gif = new ImageIcon("H:\\JavaSubject_2018\\Sie_UserClient\\resource\\Cat1.GIF");
        panel4.add(new JLabel(cat1_gif));

        container.add(panel, BorderLayout.WEST);
        container.add(panel2, BorderLayout.SOUTH);
        container.add(panel3, BorderLayout.EAST);
        container.add(panel4,BorderLayout.CENTER);

        FireFox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("cmd /c start H:\\JavaSubject_2018\\Sie_UserClient\\recourse\\Firefox.lnk");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        QQmusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("cmd /c start H:\\JavaSubject_2018\\Sie_UserClient\\recourse\\QQ音乐.lnk");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        Thunder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("cmd /c start H:\\JavaSubject_2018\\Sie_UserClient\\recourse\\迅雷.lnk");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        Steam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("cmd /c start H:\\JavaSubject_2018\\Sie_UserClient\\recourse\\Steam.lnk");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        Tetis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TetrisView tetrisView = new TetrisView(user);
                new Thread(new AutoTetrisThread(tetrisView,Frame)).start();
            }
        });

        account.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    user = sqlMethod.getUserInfo(user);
                    JOptionPane.showMessageDialog(null, "余额:" + user.getAccount() + "元");
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginView(user, sqlMethod).initView();
                Frame.dispose();
                Frame = null;
            }
        });

        renew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    user.setAccount(Integer.parseInt(money.getText()));
                    user = sqlMethod.updateUserAccount(user);
                    if (user.getTag() == 1) {
                        JOptionPane.showMessageDialog(null, "续费成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "续费失败");
                    }
                    money.setText("");
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void closeView() {
        new LoginView(user, sqlMethod).initView();
        Frame.dispose();
        Frame = null;
    }

    public void optionPanl() {
        JOptionPane.showMessageDialog(null, "您的账户余额不足10元,请及时续费");
    }

    public void optionPanl2() {
        JOptionPane.showMessageDialog(null, "您即将被强制下线");
    }
}
