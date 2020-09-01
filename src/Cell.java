import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {
    private int buttonSize;
    private int ID;
    private String tag;
    private int x;
    private int y;

    public Cell(Icon icon, int id, int buttonSize, boolean tag, int x, int y){
        this.setIcon(icon);
        ID = id;
        this.buttonSize = buttonSize;
        Dimension preferredSize = new Dimension(buttonSize,buttonSize);
        this.setPreferredSize(preferredSize);       //设置按钮预期大小
        //this.setBorderPainted(false);
        this.setBorder(null);                       //设置按钮不显示边框
        if (tag) {
            this.setText(""+id);
        }
        this.x = x;
        this.y = y;
    }

    public int getButtonSize() {
        return buttonSize;
    }

    public int getID() {
        return ID;
    }

    public String getTag() {
        return tag;
    }

    public void setButtonSize(int buttonSize) { this.buttonSize = buttonSize; }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }
}