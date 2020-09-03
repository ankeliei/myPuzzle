package View;

import Controller.Controller;
import Models.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Begin {
    private Controller controller;
    private JPanel mainArea;
    private GamePanel gamePanel;
    private Settings settings;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton newGameButton;
    private JLabel difficultyLabel;
    private JLabel numberPictureLabel;
    private JComboBox spinner1;
    private JRadioButton numberRadioButton;
    private JComboBox comboBox1;
    private JButton choosePictureButton;
    private JButton nextButton;
    private JButton preButton;

    public Begin() {
        JFrame frame = new JFrame("拼图游戏");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        settings = new Settings();
        controller = new Controller(settings);
        gamePanel = new GamePanel();
        controller.setGamePanel(gamePanel);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击了开始游戏按钮");
                controller.init();
                mainArea.removeAll();
                mainArea.add(gamePanel);
                mainArea.revalidate();
            }
        });
        choosePictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.chooseFile();
            }
        });
        preButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.preStep();
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("你按下了“前进一步”按键");
                controller.nextStep();
            }
        });
        comboBox1.addItemListener(new ItemListener() {              //选图事件处理区
            @Override
            public void itemStateChanged(ItemEvent e) {
                controller.changePicture(e);
            }
        });
        spinner1.addItemListener(new ItemListener() {               //阶数切换侦听器
            @Override
            public void itemStateChanged(ItemEvent e) {
                controller.changeOrder(e);
            }
        });
        numberRadioButton.addItemListener(new ItemListener() {          //数字标签选择侦听器
            @Override
            public void itemStateChanged(ItemEvent e) {
                controller.changeTag(e);
            }
        });
    }
}
