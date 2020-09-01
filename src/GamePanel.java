import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel implements MouseListener {

    private Settings settings;
    private Cell cells[];
    private int totalSize;

    public GamePanel(Settings settings) {
        this.settings = settings;
        cells = new Cell[settings.getOrder()*settings.getOrder()];
        GridLayout gy = new GridLayout(settings.getOrder(),settings.getOrder());        //设置一个网格布局
        this.setLayout(gy);
        init();
        this.setPreferredSize(new Dimension(totalSize,totalSize));
        this.setMaximumSize(new Dimension(totalSize,totalSize));
    }

    public void init() {
        int ImageHeight;
        int ImageWidth;
        int order = 0;
        BufferedImage buf = null;               //原始图片读入
        BufferedImage bufnew = null;            //单个按钮图片读入
        ImageIcon icon = null;                  //icon对象
        int startX = 0;                         //裁切区在整张图片中的定位
        int startY = 0;
        int singleSize = 0;                     //单个小图的尺寸

        try {                                   //读取主体图片
            buf = ImageIO.read(settings.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert buf != null;
        ImageWidth = buf.getWidth();
        ImageHeight = buf.getHeight();
        System.out.println("ImageWidth--->"+ImageWidth+"    ImageHeight--->"+ImageHeight);

        if(ImageWidth >= ImageHeight)           //划定图片的使用范围
            startX = ( ImageWidth-ImageHeight )/2;
        else
            startY = ( ImageHeight-ImageWidth )/2;
        singleSize = (ImageWidth >= ImageHeight) ? ImageHeight/settings.getOrder() : ImageWidth/settings.getOrder();
        System.out.println("Cell_Size--->"+singleSize);

        for (int i=0; i<settings.getOrder(); i++){
            for (int j=0; j<settings.getOrder(); j++){
                bufnew = buf.getSubimage(startX+j*singleSize, startY+i*singleSize, singleSize, singleSize);     //这里注意，i,j与X，Y的方向存在转换问题
                icon = new ImageIcon(bufnew);
                cells[i*settings.getOrder()+j] = new Cell(icon, i*settings.getOrder()+j, singleSize, settings.getLable(), j, i);
                if(i==j && i==(settings.getOrder()-1)){         //如果到了右下角，按钮的背景图设置为纯白，标签置空。
                    ImageIcon iconWhite = new ImageIcon("src/pictures/white.jpg");
                    cells[i*settings.getOrder()+j].setIcon(iconWhite);
                    cells[i*settings.getOrder()+j].setText("");
                }
            }
        }

        for(int i=0; i<cells.length-1; i++) {
            this.add(cells[i]);
            if(i<cells.length-2)
                cells[i].addMouseListener(this);
        }

//        ViewCells();


        totalSize = singleSize*settings.getOrder();
    }

    public void ViewCells() {
        for (int i=0; i<settings.getOrder(); i++) {
            for (int j=0; j<settings.getOrder(); j++) {
                for(int k=0; k<cells.length; k++) {
                    if(cells[k].getX()==j && cells[k].getY()==i){
                        this.add(cells[k]);
                    }
                }
            }
        }
    }

    public void OutofOrder(){

    }
    public int move(int n){                                             //单步移动方法
        if(n==0 && cells[cells.length-1].getX()!=0){       //左侧块右移
            for (Cell cell : cells) {
                if (cell.getX() == cells[cells.length - 1].getX() - 1 && cell.getY() == cells[cells.length - 1].getY()) {
                    cell.setX(cell.getX() + 1);
                    cells[cells.length - 1].setX(cells[cells.length - 1].getX() - 1);
                    return 1;
                }
            }
            return 0;
        }
        if(n==1 && cells[cells.length-1].getY()!=0){       //上侧块下移
            for (Cell cell : cells) {
                if (cell.getX() == cells[cells.length - 1].getX() && cell.getY() == cells[cells.length - 1].getY() - 1) {
                    cell.setY(cell.getY() + 1);
                    cells[cells.length - 1].setY(cells[cells.length - 1].getY() - 1);
                    return 1;
                }
            }
            return 0;
        }
        if(n==2 && cells[cells.length-1].getX()!=settings.getOrder()-1){       //右侧块左移
            for (Cell cell : cells) {
                if (cell.getX() == cells[cells.length - 1].getX() + 1 && cell.getY() == cells[cells.length - 1].getY()) {
                    cell.setX(cell.getX() - 1);
                    cells[cells.length - 1].setX(cells[cells.length - 1].getX() + 1);
                    return 1;
                }
            }
            return 0;
        }
        if(n==3 && cells[cells.length-1].getY()!=settings.getOrder()-1){       //下侧块上移
            for (Cell cell : cells) {
                if (cell.getX() == cells[cells.length - 1].getX() && cell.getY() == cells[cells.length - 1].getY() + 1) {
                    cell.setY(cell.getY() - 1);
                    cells[cells.length - 1].setY(cells[cells.length - 1].getY() + 1);
                    return 1;
                }
            }
            return 0;
        }
        else return 0;
    }

    public boolean IsWin(){
        for (Cell cell : cells) {
            if (cell.getX() * settings.getOrder() + cell.getY() != cell.getID()) return false;
        }
        return true;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
//        Cell t = (Cell)e.getSource();
//        if(t.getY()==cells[cells.length-1].getY() && t.getX()==cells[cells.length-1].getX()-1) this.move(0);
//        if(t.getY()==cells[cells.length-1].getY()-1 && t.getX()==cells[cells.length-1].getX()) this.move(1);
//        if(t.getY()==cells[cells.length-1].getY() && t.getX()==cells[cells.length-1].getX()+1) this.move(2);
//        if(t.getY()==cells[cells.length-1].getY()+1 && t.getX()==cells[cells.length-1].getX()) this.move(3);
    //    if(cells[t.getX()+1])
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}