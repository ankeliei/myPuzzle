import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Begin {
    public Begin() {
        JFrame frame = new JFrame("Begin");
        frame.setContentPane(this.panel1);
        mainArea.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击了开始游戏按钮");
                new Game();
            }
        });
        choosePictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击了文件选择按钮");
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("C:\\Users\\Administrator\\Desktop"));
                fileChooser.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));
                int result = fileChooser.showOpenDialog(fileChooser);
                if (result == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                }
                System.out.println(file);
            }
        });
    }
    private GamePanel gamePanel;
    private File file;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton newGameButton;
    private JLabel difficultyLable;
    private JLabel numberPictureLable;
    private JSpinner spinner1;
    private JRadioButton pictureRadioButton;
    private JRadioButton numberRadioButton;
    private JComboBox comboBox1;
    private JButton choosePictureButton;
    private JPanel mainArea;

}
