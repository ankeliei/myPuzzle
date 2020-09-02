import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

public class Begin {

    public Begin() {

        JFrame frame = new JFrame("Begin");
        frame.setContentPane(this.panel1);
        settings = new Settings();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击了开始游戏按钮");
                steps = new Steps();
                gamePanel = new GamePanel(settings, steps);
                mainArea.removeAll();
                mainArea.add(gamePanel);
                //https://stackoverflow.com/questions/9639017/intellij-gui-creator-jpanel-gives-runtime-null-pointer-exception-upon-adding-an
                mainArea.revalidate();
            }
        });
        choosePictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击了文件选择按钮");
                JFileChooser fileChooser = new JFileChooser();
//                fileChooser.setCurrentDirectory(new File("C:\\Users\\Administrator\\Desktop"));
                fileChooser.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));
                int result = fileChooser.showOpenDialog(fileChooser);
                if (result == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                }
                settings.setFile(file);
                System.out.println("图片切换为："+file);
            }
        });
        preButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("你按下了“后退一步”按键");
                gamePanel.preStep();
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("你按下了“前进一步”按键");
                gamePanel.nextStep();
            }
        });
        comboBox1.addItemListener(new ItemListener() {              //选图事件处理区
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    if(e.getItem().toString()=="蒙娜丽莎（默认）"){
                        settings.setFile(new File("src/pictures/蒙娜丽莎（默认）.jpg"));
                        System.out.println("图片切换为：蒙娜丽莎（默认）");
                    }
                    if(e.getItem().toString()=="山水图"){
                        settings.setFile(new File("src/pictures/山水图.jpg"));
                        System.out.println("图片切换为：山水图");
                    }
                    if(e.getItem().toString()=="动漫图"){
                        settings.setFile(new File("src/pictures/动漫图.jpg"));
                        System.out.println("图片切换为：动漫图");
                    }
                }
            }
        });
        spinner1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    if(e.getItem().toString()=="3"){
                        settings.setOrder(3);
                        System.out.println("切换为三阶");
                    }
                    if(e.getItem().toString()=="4"){
                        settings.setOrder(4);
                        System.out.println("切换为四阶");
                    }
                    if(e.getItem().toString()=="5"){
                        settings.setOrder(5);
                        System.out.println("切换为五阶");
                    }
                    if(e.getItem().toString()=="6"){
                        settings.setOrder(6);
                        System.out.println("切换为六阶");
                    }
                    if(e.getItem().toString()=="7"){
                        settings.setOrder(7);
                        System.out.println("切换为七阶");
                    }
                }
            }
        });
    }
    private Steps steps;
    private JPanel mainArea;
    private GamePanel gamePanel;
    private Settings settings;
    private File file;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton newGameButton;
    private JLabel difficultyLable;
    private JLabel numberPictureLable;
    private JComboBox spinner1;
    private JRadioButton pictureRadioButton;
    private JRadioButton numberRadioButton;
    private JComboBox comboBox1;
    private JButton choosePictureButton;
    private JButton nextButton;
    private JButton preButton;
}
