import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel {

    private Settings settings;
    private Cell cells[];
    private boolean isWine = false;
    private Steps steps;

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