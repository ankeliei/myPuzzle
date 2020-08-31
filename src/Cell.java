import javax.swing.*;

public class Cell extends JButton {
    private static int buttonSize;;
    private static int ID;
    private static String lable;

    public Cell(Icon icon, int id, int buttonSize, boolean lable){
        this.setIcon(icon);
        this.ID = id;
        this.buttonSize = buttonSize;
        this.setSize(buttonSize, buttonSize);
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