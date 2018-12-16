package net.sie.Monitor;

import net.sie.Interface.ISqlMethod;
import net.sie.Model.Status;
import net.sie.Model.User;
import net.sie.View.NormalUserView;
import net.sie.View.VipUserView;

import javax.swing.*;
import java.io.IOException;

public class AutoNormalThread implements Runnable {

    private NormalUserView normalUserView;
    private User user;
    private ISqlMethod sqlMethod;
    private JFrame Frame;
    private Status status;

    public AutoNormalThread(NormalUserView normalUserView) {
        this.normalUserView = normalUserView;
        this.user = normalUserView.getUser();
        this.Frame = normalUserView.getFrame();
        this.status = normalUserView.getStatus();
        this.sqlMethod = normalUserView.getSqlMethod();
    }

    @Override
    public void run() {

        try {
            status = sqlMethod.insertStatusInfo(status, user);
            while (user.getAccount() >= 0 && normalUserView.getFrame() != null) {
                user = sqlMethod.autoUpdateUserAccount(user);
                status = sqlMethod.autoUpdateStatusConsumption(status);
                System.out.println("账号学号:" + user.getUserName() + "-----账号余额:" + user.getAccount());
                System.out.println("账号学号:" + status.getUserName() + "-----消费金额:" + status.getConsumption());
                if (user.getAccount() == 10) {
                    normalUserView.optionPanl();
                } else if (user.getAccount() == 5) {
                    normalUserView.optionPanl2();
                } else if (user.getAccount() == 0) {
                    normalUserView.closeView();
                    status = sqlMethod.updateStatusInfo(status);
                    System.out.print(status.toString());
                }
                Thread.sleep(10000);
            }
            if (normalUserView.getFrame() == null) {
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
