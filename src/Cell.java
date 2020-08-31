import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {
    private static int buttonSize;
    private static int ID;
    private static String lable;

    public Cell(Icon icon, int id, int buttonSize, boolean lable){
        this.setIcon(icon);
        ID = id;
        Cell.buttonSize = buttonSize;
        Dimension preferredSize = new Dimension(buttonSize,buttonSize);
        this.setPreferredSize(preferredSize);       //设置按钮预期大小
        //this.setBorderPainted(false);
        this.setBorder(null);                       //设置按钮不显示边框
        if (lable) {
            this.setText(""+id);
        }
    }

    public static int getButtonSize() {
        return buttonSize;
    }

    public static int getID() {
        return ID;
    }

    public static String getLable() {
        return lable;
    }

    public static void setButtonSize(int buttonSize) { Cell.buttonSize = buttonSize; }

    public static void setID(int ID) {
        Cell.ID = ID;
    }

    public static void setLable(String lable) {
        Cell.lable = lable;
    }
}