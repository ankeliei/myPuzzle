import javax.swing.*;

public class GamePanel extends JPanel {

    private Settings settings;
    private Steps steps;
    private Cell[] cells;
    private boolean isWine = false;

    public GamePanel() {

    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }

    public void setSteps(Steps steps) {
        this.steps = steps;
    }

    public Cell[] getCells(){
        return cells;
    }

    public void setIsWine(boolean isWine) {this.isWine = isWine;}

    public boolean getIsWin() {return isWine;}
}