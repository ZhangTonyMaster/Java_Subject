package net.sie.Monitor;

import net.sie.Interface.ISqlMethod;
import net.sie.Model.Status;
import net.sie.Model.User;
import net.sie.View.TetrisView;
import net.sie.View.VipUserView;

import javax.swing.*;
import java.io.IOException;

public class AutoVipThread implements Runnable{

    private VipUserView vipUserView;
    private User user;
    private ISqlMethod sqlMethod;
    private JFrame Frame;
    private Status status;
    private TetrisView tetrisView;

    public AutoVipThread(VipUserView vipUserView) {
        this.vipUserView = vipUserView;
        this.user = vipUserView.getUser();
        this.Frame = vipUserView.getFrame();
        this.status = vipUserView.getStatus();
        this.sqlMethod = vipUserView.getSqlMethod();
    }

    public JFrame getFrame() {
        return Frame;
    }

    @Override
    public void run() {

        try {
            status = sqlMethod.insertStatusInfo(status, user);
            while (user.getAccount() >= 0 && vipUserView.getFrame() != null) {
                user = sqlMethod.autoUpdateUserAccount(user);
                status = sqlMethod.autoUpdateStatusConsumption(status);
                System.out.println("账号学号:" + user.getUserName() + "-----账号余额:" + user.getAccount());
                System.out.println("账号学号:" + status.getUserName() + "-----消费金额:" + status.getConsumption());
                if (user.getAccount() == 10) {
                    vipUserView.optionPanl();
                } else if (user.getAccount() == 5) {
                    vipUserView.optionPanl2();
                } else if (user.getAccount() == 0) {
                    vipUserView.closeView();
                    status = sqlMethod.updateStatusInfo(status);
                    System.out.print(status.toString());
                }
                Thread.sleep(20000);
            }
            if (vipUserView.getFrame() == null) {
                status = sqlMethod.updateStatusInfo(status);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
