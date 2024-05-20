package com.UI;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    public RegisterJFrame(){
        //设置注册界面的宽高
        this.setSize(488, 500);
         //设置界面标题
         this.setTitle("拼图登陆界面");
         //设置界面置顶
         this.setAlwaysOnTop(true);
         //设置界面居中
         this.setLocationRelativeTo(null);
         //设置界面退出
         this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 
 
        //设置显示界面
        this.setVisible(true);
    }
}
