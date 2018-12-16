package net.sie.View;

import net.sie.Interface.ISqlMethod;
import net.sie.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

public class TetrisView extends JFrame {

    private nextBlockJPanel p;
    private TetrisPanel a;
    private int set_delay = 1000;

    private User user;
    private ISqlMethod sqlMethod;

    private JMenu[] jmenu;
    private JMenuItem[][] jmenuItem;

    public void closeView(){
        dispose();
        setVisible(false);
    }

    /*
     * 动态监听菜单
     */
    private ActionListener jmenuListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("newGame")) {
                a.newGame();
                jmenuItem[0][2].setEnabled(false);
                jmenuItem[0][1].setEnabled(true);
                for (int i = 0; i < jmenuItem[1].length; i++) {
                    jmenuItem[1][i].setEnabled(false);
                }
                for (int i = 0; i < jmenuItem[2].length; i++) {
                    jmenuItem[2][i].setEnabled(false);
                }
            } else if (e.getActionCommand().equals("break")) {
                if (a.timer != null)
                    a.timer.stop();
                jmenuItem[0][1].setEnabled(false);
                jmenuItem[0][2].setEnabled(true);
                for (int i = 0; i < jmenuItem[1].length; i++) {
                    jmenuItem[1][i].setEnabled(true);
                }
            } else if (e.getActionCommand().equals("continue")) {
                if (a.timer != null)
                    a.timer.restart();
                jmenuItem[0][2].setEnabled(false);
                jmenuItem[0][1].setEnabled(true);
                for (int i = 0; i < jmenuItem[1].length; i++) {
                    jmenuItem[1][i].setEnabled(false);
                }
                p.score = p.now_score;
                for (int i = 0; i < a.map.length; i++) {
                    for (int j = 0; j < a.map[i].length; j++) {
                        a.map[i][j] = a.now_map[i][j];
                    }
                }
                a.repaint();
                p.repaint();
            } else if (e.getActionCommand().equals("exit")) {
                if (a.timer != null && a.timer.isRunning()) {
                    a.timer.stop();
                }
                //System.exit(0);
                dispose();
            } else if (e.getActionCommand().equals("last_score")) {
                if (a.timer != null)
                    a.timer.stop();
                p.score = p.last_score;
                for (int i = 0; i < a.map.length; i++) {
                    for (int j = 0; j < a.map[i].length; j++) {
                        a.map[i][j] = a.last_map[i][j];
                    }
                }
                a.repaint();
                p.repaint();
            } else if (e.getActionCommand().equals("optimal_score")) {
                if (a.timer != null)
                    a.timer.stop();
                p.score = p.optimal_score;
//                System.out.println(p.score);
//                System.out.println(p.optimal_score);
                for (int i = 0; i < a.map.length; i++) {
                    for (int j = 0; j < a.map[i].length; j++) {
                        a.map[i][j] = a.optimal_map[i][j];
                    }
                }
                a.repaint();
                p.repaint();
            } else if (e.getActionCommand().equals("hard")) {
                set_delay = 200;
            } else if (e.getActionCommand().equals("middle")) {
                set_delay = 600;
            } else if (e.getActionCommand().equals("low")) {
                set_delay = 1000;
            }

        }
    };
    /*
     * 俄罗斯方块形状
     */
    private int[][][][] shapes = new int[][][][]{
            //T
            {{{0, 1, 0}, {1, 1, 1}},
                    {{0, 0, 1}, {0, 1, 1}, {0, 0, 1}},
                    {{1, 1, 1}, {0, 1, 0}},
                    {{1, 0, 0}, {1, 1, 0}, {1, 0, 0}}},
            //L
            {{{0, 1, 0}, {0, 1, 0}, {0, 1, 1}},
                    {{0, 0, 1}, {1, 1, 1}},
                    {{1, 1, 0}, {0, 1, 0}, {0, 1, 0}},
                    {{1, 1, 1}, {1, 0, 0}}},
            //J
            {{{0, 1, 0}, {0, 1, 0}, {1, 1, 0}},
                    {{1, 1, 1}, {0, 0, 1}},
                    {{0, 1, 1}, {0, 1, 0}, {0, 1, 0}},
                    {{1, 0, 0}, {1, 1, 1}}},
            //S
            {{{0, 1, 1}, {1, 1, 0}},
                    {{0, 1, 0}, {0, 1, 1}, {0, 0, 1}}},
            //Z
            {{{1, 1, 0}, {0, 1, 1}},
                    {{0, 1, 0}, {1, 1, 0}, {1, 0, 0}}},
            //O
            {{{1, 1}, {1, 1}}},
            //I
            {{{1, 1, 1, 1}},
                    {{1}, {1}, {1}, {1}}}
    };

    public TetrisView(User user,ISqlMethod sqlMethod) {
        super("俄罗斯方块简易版");
        setBounds(200, 100, 700, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.user = user;
        this.sqlMethod = sqlMethod;
        //getContentPane().setLayout(new BorderLayout());

        //菜单
        JMenuBar bar = new JMenuBar();
        //for添加一系列菜单,
        String[] str = {"选项", "记录", "难度"};
        String[][] strs = {{"新游戏", "暂停", "继续", "退出"},
                {"上轮成绩", "最佳成绩"},
                {"难", "中", "低"}};
        String[][] strs_english = {{"newGame", "break", "continue", "exit"},
                {"last_score", "optimal_score"},
                {"hard", "middle", "low"}};
        jmenu = new JMenu[str.length];
        jmenuItem = new JMenuItem[strs.length][];
        for (int i = 0; i < jmenu.length; i++) {
            jmenu[i] = new JMenu(str[i]);
            jmenuItem[i] = new JMenuItem[strs[i].length];
            for (int j = 0; j < jmenuItem[i].length; j++) {
                jmenuItem[i][j] = new JMenuItem(strs[i][j]);
                jmenuItem[i][j].addActionListener(jmenuListener);
                jmenuItem[i][j].setActionCommand(strs_english[i][j]);
                if (jmenuItem[i][j].getActionCommand().equals("continue"))
                    jmenuItem[i][j].setEnabled(false);
                if (jmenuItem[i][j].getActionCommand().equals("break"))
                    jmenuItem[i][j].setEnabled(false);
                jmenu[i].add(jmenuItem[i][j]);
            }
            bar.add(jmenu[i]);
        }
        setJMenuBar(bar);

        //记分面板
        p = new nextBlockJPanel();
        //游戏绘画面板
        a = new TetrisPanel(p);
        addKeyListener(a.timerListeren);
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, a, p);
        sp.setDividerLocation(400);
        getContentPane().add(sp);

        setVisible(true);
    }

    public class TetrisPanel extends JPanel {
        private nextBlockJPanel p;

        private int blockType;
        private int turnState;

        private int local_x;
        private int local_y;

        private int delay;
        private TimerListeren timerListeren = new TimerListeren();

        private Timer timer;


        private int[][] map = new int[27][20];
        private int[][] now_map = new int[27][20];
        private int[][] last_map = new int[27][20];
        private int[][] optimal_map = new int[27][20];

        /*
         * 构造函数并map初始化，构造墙壁
         */
        public TetrisPanel(nextBlockJPanel p) {
            this.p = p;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (j == 0 || j == map[i].length - 1 || i == map.length - 1) {
                        map[i][j] = 3;
                    } else {
                        map[i][j] = 0;
                    }
                }

            }
        }

        /*
         * 游戏开始
         */
        private void newGame() {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (j == 0 || j == map[i].length - 1 || i == map.length - 1) {
                        map[i][j] = 3;
                    } else {
                        map[i][j] = 0;
                    }
                }

            }

            nextBlock();
            p.score = 0;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    now_map[i][j] = map[i][j];
                }
            }
            p.now_score = p.score;
            delay = set_delay;
            timer = new Timer(delay, timerListeren);
            timer.start();
        }

        /*
         * 下一方块形状
         */
        private void nextBlock() {
            blockType = p.blockType;
            turnState = p.turnState;
            local_x = 8;
            local_y = 0;
            p.nextBlock();
            repaint();
            isGameOver();
        }

        //下落
        private void down() {
            if (crash(blockType, turnState, local_x, local_y + 1) == 1) {
                local_y++;
            } else {
                add(blockType, turnState, local_x, local_y);
                nextBlock();

            }
            repaint();
        }

        //键盘方向键操作
        private void left() {
            local_x -= crash(blockType, turnState, local_x - 1, local_y);
            repaint();

        }

        private void right() {
            local_x += crash(blockType, turnState, local_x + 1, local_y);
            repaint();
        }

        private void trun() {
            turnState = (turnState + crash(blockType, (turnState + 1) % shapes[blockType].length, local_x, local_y)) % shapes[blockType].length;
            repaint();
        }

        //加方块
        private void add(int blockType, int turnState, int local_x, int local_y) {
            for (int i = 0; i < shapes[blockType][turnState].length; i++) {
                for (int j = 0; j < shapes[blockType][turnState][i].length; j++) {
                    if (shapes[blockType][turnState][i][j] != 0)
                        map[local_y + i][local_x + j] = shapes[blockType][turnState][i][j];
                }
            }
            Deline();
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    now_map[i][j] = map[i][j];
                }
            }
            p.now_score = p.score;
        }

        //是否游戏结束
        private void isGameOver() {
            if (crash(blockType, turnState, local_x, local_y) == 0) {
                //p.last_score = p.score;
                user.setTetrisLastScore(p.score);
                user.setTetrisOptimalScore(p.score);
                try {
                    user = sqlMethod.updateUserTetrisScore(user);
                    p.last_score = user.getTetrisLastScore();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        last_map[i][j] = map[i][j];
                    }
                }
                if (p.score > p.optimal_score) {
                    p.optimal_score = p.score;
                    for (int i = 0; i < map.length; i++) {
                        for (int j = 0; j < map[i].length; j++) {
                            optimal_map[i][j] = map[i][j];
                        }
                    }
                }
                timer.stop();
                JOptionPane.showMessageDialog(null, "游戏得分为：" + p.score);
                //
                int option = JOptionPane.showConfirmDialog(null, "是否再来一局？", "", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    newGame();
                } else {
                    //System.exit(0);
                    dispose();
                }
            }

        }

        //消方块
        private void Deline() {
            int count = 0;
            for (int i = 0; i < map.length - 1; i++) {
                int c = 1;
                for (int j = 1; j < map[i].length - 1; j++) {
                    c = c & map[i][j];
                }
                if (c == 1) {
                    for (int i2 = i; i2 > 0; i2--) {
                        for (int j = 1; j < map[i2].length - 1; j++) {
                            map[i2][j] = map[i2 - 1][j];
                        }
                    }
                    for (int j = 1; j < map[i].length - 1; j++) {
                        map[0][j] = 0;
                    }
                    count++;
                }
            }
            if (count != 0) {
                p.score += 10 * (int) Math.pow(2, count);
                p.repaint();
            }
            timer.setDelay(delay - p.score / 100);
        }

        //判断是否碰撞
        private int crash(int blockType, int turnState, int local_x, int local_y) {
            for (int i = 0; i < shapes[blockType][turnState].length; i++) {
                for (int j = 0; j < shapes[blockType][turnState][i].length; j++) {
                    try {
                        if ((shapes[blockType][turnState][i][j] & map[local_y + i][local_x + j]) == 1)
                            return 0;
                    } catch (Exception e) {
                        return 0;
                    }
                }
            }
            return 1;
        }

        @Override
        protected void printComponent(Graphics g) {
            super.printComponent(g);
            //画出游戏界面
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] == 3) {
                        g.setColor(Color.gray);
                        g.fillRect(j * 20, i * 20, 20, 20);
                    }
                    if (map[i][j] == 1) {
                        g.setColor(Color.blue);
                        g.fillRect(j * 20, i * 20, 20, 20);
                    }
                }
            }
            if (timer != null && timer.isRunning())
                for (int i = 0; i < shapes[blockType][turnState].length; i++) {
                    for (int j = 0; j < shapes[blockType][turnState][i].length; j++) {
                        if (shapes[blockType][turnState][i][j] == 1) {
                            g.setColor(Color.cyan);
                            g.drawRect((local_x + j) * 20, (local_y + i) * 20, 20, 20);
                            g.setColor(Color.blue);
                            g.fillRect((local_x + j) * 20 + 1, (local_y + i) * 20 + 1, 19, 19);
                        }
                    }
                }

        }

        @Override
        public void paint(Graphics g) {
            printComponent(g);
        }

        /*
         * 键盘控制
         */
        class TimerListeren extends KeyAdapter implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                down();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (timer == null || !timer.isRunning())
                    return;
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_DOWN:
                        down();
                        break;
                    case KeyEvent.VK_UP:
                        trun();
                        break;
                    case KeyEvent.VK_LEFT:
                        left();
                        break;
                    case KeyEvent.VK_RIGHT:
                        right();
                        break;
                }
            }

        }

    }

    public class nextBlockJPanel extends JPanel {

        private int blockType;
        private int turnState;

        private int score;
        private int now_score;
        private int last_score;
        private int optimal_score;

        public nextBlockJPanel() {
            nextBlock();
            score = 0;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawString("下一个方块形状：", 20, 20);

            for (int i = 0; i < shapes[blockType][turnState].length; i++) {
                for (int j = 0; j < shapes[blockType][turnState][i].length; j++) {
                    if (shapes[blockType][turnState][i][j] == 1) {
                        g.setColor(Color.cyan);
                        g.drawRect(40 + j * 20, 40 + i * 20, 20, 20);
                        g.setColor(Color.blue);
                        g.fillRect(40 + j * 20 + 1, 40 + i * 20 + 1, 19, 19);
                    }
                }
            }
            g.setColor(Color.red);
            g.drawString("Score:", 140, 20);
            g.setFont(new Font("score", Font.BOLD, 50));
            g.drawString("" + score, 140, 80);
            g.setFont(new Font("aa", Font.PLAIN, 32));
            g.drawString("拒绝盗版游戏，", 20, 320);
            g.drawString("注意自我保护。", 20, 350);
            g.drawString("谨防受骗上当。", 20, 380);
            g.drawString("适度游戏益脑，", 20, 410);
            g.drawString("沉迷游戏伤身。", 20, 440);
            g.drawString("合理安排时间，", 20, 470);
            g.drawString("享受健康生活。", 20, 500);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            paintComponent(g);
        }

        //下一个方块
        private void nextBlock() {
            blockType = (new Random()).nextInt(shapes.length);
            turnState = (new Random()).nextInt(shapes[blockType].length);
            repaint();

        }
    }
}
