import javax.swing.*;
import java.awt.*;

public class Cell extends JLabel {
    private int buttonSize;
    private int ID;
    private String tag;
    private int positionX;
    private int positionY;

    public Cell(Icon icon, int id, int buttonSize, boolean tag, int positionX, int positionY){
        this.setIcon(icon);
        ID = id;
        this.buttonSize = buttonSize;
        Dimension preferredSize = new Dimension(buttonSize,buttonSize);
        this.setPreferredSize(preferredSize);       //设置按钮预期大小
        //this.setBorderPainted(false);
        this.setBorder(null);                       //设置按钮不显示边框
        if (tag) {
            this.setText(""+id);
            this.setFont(new Font("宋体",Font.PLAIN,22));
            this.setForeground(Color.RED);
        }
        this.setHorizontalTextPosition(this.CENTER);
        this.positionX = positionX;
        this.positionY = positionY;
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

    public int getPositionX() { return positionX; }

    public int getPositionY() { return positionY; }

    public void setPositionX(int positionX) { this.positionX = positionX; }

    public void setPositionY(int positionY) { this.positionY = positionY; }
}