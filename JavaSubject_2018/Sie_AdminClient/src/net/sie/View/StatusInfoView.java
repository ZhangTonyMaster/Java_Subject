package net.sie.View;

import net.sie.Interface.ISqlMethod;
import net.sie.Model.Status;
import net.sie.Model.User;
import net.sie.SocketClient.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class StatusInfoView {

    private JFrame Frame;
    private ISqlMethod sqlMethod;
    private Status status;
    private ArrayList<Status> statusList;

    public StatusInfoView(Status status, ISqlMethod sqlMethod){
        this.status = status;
        this.sqlMethod = sqlMethod;
        try {
            this.statusList = sqlMethod.getOnLineInfo(status,new ArrayList<Status>());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initView(){

        Frame = new JFrame("在线用户信息");
        Frame.setBounds(200,300,1500,600);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Vector rowData = new Vector();
        Vector columnNames = new Vector();

        columnNames.add("账户类型");
        columnNames.add("账号学号");
        columnNames.add("真实姓名");
        columnNames.add("上线时间");
        columnNames.add("消费金额");

        for(int i = 0;i < statusList.size();i++){
            Vector data = new Vector();
            data.add(statusList.get(i).getAttribute());
            data.add(statusList.get(i).getUserName());
            data.add(statusList.get(i).getRealName());
            data.add(statusList.get(i).getOnLineTime());
            data.add(statusList.get(i).getConsumption());
            rowData.add(data);
        }

        JTable jTable = new JTable(rowData,columnNames);
        JPanel panel1 = new JPanel();
        panel1.add(jTable);
        JScrollPane scrollPane = new JScrollPane(jTable);

        JPanel panel = new JPanel();
        JButton reflesh = new JButton("刷新");
        panel.add(reflesh);
        JButton back = new JButton("后退");
        panel.add(back);

        Frame.getContentPane().add(scrollPane, BorderLayout.NORTH);
        Frame.getContentPane().add(panel,BorderLayout.SOUTH);
        Frame.setVisible(true);

        reflesh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.dispose();
                Frame = null;
                new StatusInfoView(status,sqlMethod).initView();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.dispose();
                Frame = null;
                new AdminView(new User(),sqlMethod).initView();
            }
        });
    }

}
