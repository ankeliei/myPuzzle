import javax.swing.*;

public class Game {
    public Game(){
        JFrame frame = new JFrame("Game");
//        this.jpanel1.add(new JMenu());
        frame.setContentPane(this.jpanel1);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);       //设置窗口关闭时不关闭父窗口
        frame.pack();
        frame.setVisible(true);
    }

    private JButton previousButton;
    private JButton nextButton;
    private JPanel jpanel1;
}
