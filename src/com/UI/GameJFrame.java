package com.UI;

import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class GameJFrame extends JFrame implements KeyListener,ActionListener{
    // 创建一个二维数组，目的是为了记录每张图片对应的数字
    int[][] data = new int[4][4];
    // 创建x，y用来记录0图片所在二维数组的坐标
    int x = 0;
    int y = 0;
    // 定义一个变量用来记录路径
    String path = "C:\\Users\\lenovo\\Desktop\\image\\girl\\girl3\\";
    // 再定义一个二维数组用来存放胜利时的数组
    int[][] win = {
            { 1, 2, 3, 4 },
            { 5, 6, 7, 8 },
            { 9, 10, 11, 12 },
            { 13, 14, 15, 0 }
    };
    // 定义一个计数器
    int stepCounter = 0;

    // 创建选项下的条目对象，因为下面方法要用到，所以定义在全局位置
    JMenuItem repaly = new JMenuItem("重新游戏");
    JMenuItem reLogin = new JMenuItem("重新登录");
    JMenuItem exit = new JMenuItem("退出游戏");
    JMenuItem hao = new JMenuItem("欢迎打赏");

    public GameJFrame() {
        // 初始化界面
        initFrame();

        // 初始化菜单
        initJMenuBar();

        // 初始化数据（打乱图片顺序）
        initData();

        // 初始化图片
        initImage();

        // 设置显示界面
        this.setVisible(true);
    }

    private void initData() {
        // 创建一个一维数组用来记录图片
        int[] tempArr = new int[16];
        for (int i = 0; i < tempArr.length; i++) {
            tempArr[i] = i;
        }

        // 打乱一维数组中的内容
        Random r = new Random();
        // 设置一个中间变量用来进行交换
        int temp = 0;
        for (int i = 0; i < tempArr.length; i++) {
            int randomIndex = r.nextInt(16);
            // 开始交换
            temp = tempArr[i];
            tempArr[i] = tempArr[randomIndex];
            tempArr[randomIndex] = temp;
        }

        // 将一维数组添加到二维数组中
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = tempArr[i * 4 + j];
                // 获取空白图片的位置
                if (data[i][j] == 0) {
                    x = i;
                    y = j;
                }
            }
        }

    }

    private void initImage() {
        // 清空
        this.getContentPane().removeAll();

        // 判断是否胜利，胜利则弹出胜利界面
        if (victory()) {
            JLabel winJLabel = new JLabel(new ImageIcon("C:\\Users\\lenovo\\Desktop\\image\\win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        // 显示计数器
        JLabel step = new JLabel("步数：" + stepCounter);
        step.setBounds(50, 30, 100, 20);
        this.getContentPane().add(step);

        // 进行换行，循环四次，添加四行
        for (int i = 0; i < 4; i++) {
            // 对每一行都添加4张图片
            for (int j = 0; j < 4; j++) {
                // 定义一个计数器用来代表图片对应的数字
                int num = data[i][j];
                // 创建一个图片ImageIcon对象
                ImageIcon icon = new ImageIcon(
                        path + num + ".jpg"); // 这里的路径必须是绝对路径,要想使用相对路径，则需要换模块
                // 创建一个JLabel（容器）用来存放图片
                JLabel jb = new JLabel(icon);
                // 指定该容器（JLabel）的位置
                jb.setBounds(105 * j + 83, 105 * i + 134, 105, 105); // 对图片进行偏移，使其更加美观
                // 给图片添加边框
                // 0:代表凹进去
                // 1：让其凸出来
                jb.setBorder(new BevelBorder(1));
                // 把管理容器放到界面中
                this.getContentPane().add(jb); // this代表调用者的地址值,也就是该对象的地址值
            }
        }
        // 细节：先加载的图片在上方，后加载的在下方
        // 添加背景图片使其更加美观
        ImageIcon bg = new ImageIcon("C:\\Users\\lenovo\\Desktop\\image\\background.png");
        JLabel j = new JLabel(bg);
        // 设置背景图片的位置
        j.setBounds(40, 40, 508, 560);
        // 将背景图添加到窗口中
        this.getContentPane().add(j);

        // 刷新界面
        this.getContentPane().repaint();

    }

    private void initFrame() {
        // 设置游戏界面的宽高
        this.setSize(603, 680);
        // 设置界面标题
        this.setTitle("拼图游戏v1.0");
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 取消隐藏容器默认的对JLabel居中放置，这样才会让JLabel按照x，y的坐标方式进行放置
        this.setLayout(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 要给整个界面都添加键盘监听
        this.addKeyListener(this);

    }

    private void initJMenuBar() {
        // 创建整个菜单的对象
        JMenuBar jBar = new JMenuBar();

        // 创建菜单上的两个选项对象（功能 关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        // 开始将子条目添加JMenu中
        functionJMenu.add(repaly);
        functionJMenu.add(reLogin);
        functionJMenu.add(exit);
        aboutJMenu.add(hao);

        //添加动作监听
        repaly.addActionListener(this);
        reLogin.addActionListener(this);
        exit.addActionListener(this);
        hao.addActionListener(this);

        // 将两个功能添加到JMenuBar中
        jBar.add(functionJMenu);
        jBar.add(aboutJMenu);

        // 最后将JMenuBar的内容添加到界面中
        this.setJMenuBar(jBar);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            // 把页面中所有的图片先删除
            this.getContentPane().removeAll();
            // 加载一张完整的图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            // 添加背景图片使其更加美观
            ImageIcon bg = new ImageIcon("C:\\Users\\lenovo\\Desktop\\image\\background.png");
            JLabel j = new JLabel(bg);
            // 设置背景图片的位置
            j.setBounds(40, 40, 508, 560);
            // 将背景图添加到窗口中
            this.getContentPane().add(j);
            // 刷新界面
            this.getContentPane().repaint();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 如果游戏胜利就直接结束方法
        if (victory()) {
            return;
        }

        int code = e.getKeyCode();
        switch (code) {
            // 向左移动：将空白图片的右方的图片向左移
            case 37 -> {
                if (y == 3)
                    break;
                data[x][y] = data[x][y + 1];
                data[x][y + 1] = 0;
                // 改变空白图片位置
                y++;
                // 每移动一次计数器就增加一次
                stepCounter++;
                // 根据调整后的图片进行加载
                initImage();
            }
            // 向上移动：将空白图片的下方的图片向上移
            case 38 -> {
                if (x == 3)
                    break;
                data[x][y] = data[x + 1][y];
                data[x + 1][y] = 0;
                // 改变空白图片位置
                x++;
                // 每移动一次计数器就增加一次
                stepCounter++;
                // 根据调整后的图片进行加载
                initImage();
            }
            // 向右移动：将空白图片的左方的图片右移
            case 39 -> {
                if (y == 0)
                    break;
                data[x][y] = data[x][y - 1];
                data[x][y - 1] = 0;
                // 改变空白图片位置
                y--;
                // 每移动一次计数器就增加一次
                stepCounter++;
                // 根据调整后的图片进行加载
                initImage();
            }
            // 向下移动：将空白图片的上方的图片下移
            case 40 -> {
                if (x == 0)
                    break;
                data[x][y] = data[x - 1][y];
                data[x - 1][y] = 0;
                // 改变空白图片位置
                x--;
                // 每移动一次计数器就增加一次
                stepCounter++;
                // 根据调整后的图片进行加载
                initImage();
            }
            // 松开A键后重新回到原来的界面
            case 65 -> {
                initImage();
            }
            // 作弊码：w键按下后就直接变成胜利数组
            case 87 -> {
                data = new int[][] {
                        { 1, 2, 3, 4 },
                        { 5, 6, 7, 8 },
                        { 9, 10, 11, 12 },
                        { 13, 14, 15, 0 }
                };
                initImage();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                // 如果出现有一个不相等就直接返回false，结束方法
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        // 全部正确才返回true
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        //重新游戏
        if (obj==repaly) {
            //计数器清零
            stepCounter=0;
            //重新打乱二维数组
            initData();
            //重新加载页面
            initImage();
        }else if (obj==reLogin) {
            //关闭游戏窗口
            this.setVisible(false);
            //创建登陆界面的对象
            new LoginJFrame();
        }else if (obj==exit) {
            //直接关闭虚拟机
            System.exit(0);
        }else if (obj==hao) {
            //创建一个弹窗
            JDialog jd=new JDialog();
            //创建容器存放图片
            JLabel jl=new JLabel(new ImageIcon("C:\\Users\\lenovo\\Desktop\\image\\ma.jpg"));
            //设置位置
            jl.setBounds(0,0,530,530);
            //把容器添加到弹窗里
            jd.add(jl);
            //给弹窗设置大小
            jd.setSize(560,560);
            //让弹窗置顶
            jd.setAlwaysOnTop(true);
            //让弹窗居中
            jd.setLocationRelativeTo(null);
            //让弹窗不关闭就无法操作下面的界面
            jd.setModal(true);
            //让弹窗显示出来
            jd.setVisible(true);
        }
    }
}
