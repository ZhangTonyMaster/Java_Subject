package net.sie.Monitor;

import net.sie.View.TetrisView;
import net.sie.View.VipUserView;

import javax.swing.*;

public class AutoTetrisThread implements Runnable{

    private JFrame vipFrame;
    private TetrisView tetrisView;

    public AutoTetrisThread(TetrisView tetrisView,JFrame vipFrame){
        this.tetrisView = tetrisView;
        this.vipFrame = vipFrame;
    }

    @Override
    public void run() {
        if(vipFrame == null){
            tetrisView.closeView();
        }
    }
}
