package Views;

import Modles.Subject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Begin extends Observer{
    @Override
    public void update() {                                              //得到更新通知时处理更新
        //TODO:视图更新写在这里
    }

    public Begin(Subject subject) {

        this.subject = subject;                                         //向modles添加订阅
        this.subject.attach(this);

        JFrame frame = new JFrame("拼图游戏");                      //显示窗体
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        fileChooseButton.addActionListener(new ActionListener() {       //linstener注册
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:写一个调出文件选择器的东西在这里
            }
        });

        BeginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:开始游戏写在这里
            }
        });
    }

    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton BeginButton;
    private JTextPane nodeTextPane;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JRadioButton numberRadioButton;
    private JRadioButton pictureRadioButton;
    private JButton fileChooseButton;
}