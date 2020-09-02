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

public class GamePanel extends JPanel implements MouseListener, KeyListener {

    private Settings settings;
    private Cell cells[];
    private int totalSize;
    private boolean isWine = false;
    private Steps steps;

    public GamePanel(Settings settings, Steps steps) {
        this.settings = settings;
        this.steps = steps;
        cells = new Cell[settings.getOrder()*settings.getOrder()];
        GridLayout gy = new GridLayout(settings.getOrder(),settings.getOrder());        //设置一个网格布局
        this.setLayout(gy);
        this.setFocusable(true);
        this.addKeyListener(this);
        init();
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

        float n = (float)Math.min(ImageWidth, ImageHeight)/420;                  //取得缩放比

        ImageWidth = (int) (ImageWidth/n);                  //拿到目标尺寸
        ImageHeight = (int) (ImageHeight/n);

        BufferedImage tmp = new BufferedImage(ImageWidth, ImageHeight, BufferedImage.TYPE_INT_ARGB);        //设置变换后的图片模板
        tmp.getGraphics().drawImage(buf.getScaledInstance(ImageWidth, ImageHeight, Image.SCALE_SMOOTH),0,0,null);   //将原图按比例填入模板

        buf = tmp;                              //拿到变化后的图片

        singleSize = (ImageWidth >= ImageHeight) ? ImageHeight/settings.getOrder() : ImageWidth/settings.getOrder();
        System.out.println("Cell_Size--->"+singleSize);

        if(ImageWidth >= ImageHeight)           //划定图片的使用范围
            startX = ( ImageWidth-ImageHeight )/2;
        else
            startY = ( ImageHeight-ImageWidth )/2;


        for (int i=0; i<settings.getOrder(); i++){
            for (int j=0; j<settings.getOrder(); j++){
                bufnew = buf.getSubimage(startX+j*singleSize, startY+i*singleSize, singleSize, singleSize);     //这里注意，i,j与X，Y的方向存在转换问题
                icon = new ImageIcon(bufnew);
                cells[i*settings.getOrder()+j] = new Cell(icon, i*settings.getOrder()+j, singleSize, settings.getTag(), j, i);
                cells[i*settings.getOrder()+j].addMouseListener(this);
//                cells[i*settings.getOrder()+j].addKeyListener(this);
                if(i==j && i==(settings.getOrder()-1)){         //如果到了右下角，按钮的背景图设置为纯白，标签置空。
                    ImageIcon iconWhite = new ImageIcon("src/pictures/white.jpg");
                    cells[i*settings.getOrder()+j].setIcon(iconWhite);
                    cells[i*settings.getOrder()+j].setText("");
                }
            }
        }
        OutofOrder();       //打乱顺序
        viewCells();        //显示游戏面板
        totalSize = singleSize*settings.getOrder();
    }

    public void viewCells() {
        this.removeAll();
        for (int i=0; i<settings.getOrder(); i++) {
            for (int j=0; j<settings.getOrder(); j++) {
                for(int k=0; k<cells.length; k++) {
                    if(cells[k].getPositionX()==j && cells[k].getPositionY()==i){
                        this.add(cells[k]);
                        break;
                    }
                }
            }
        }
        this.revalidate();
    }

    public void OutofOrder(){
        Random random = new Random();           //第一步先随机发生一些步数
        for(int i=0; i<settings.getOrder()*settings.getOrder()*settings.getOrder();){
            int s = random.nextInt(4);
            i = i + move(s);
        }
            //第二部将不在右下角的白块归位
        int x =settings.getOrder()-1 - cells[cells.length-1].getPositionX();
        int y =settings.getOrder()-1 - cells[cells.length-1].getPositionY();

        for(int i=0; i<x; i++){
            move(2);
        }
        for(int i=0; i<y; i++){
            move(3);
        }
    }

    public int move(int n){                                             //单步移动方法
        if(n==0 && cells[cells.length-1].getPositionX()!=0){       //左侧块右移
            for (Cell cell : cells) {
                if (cell.getPositionX() == cells[cells.length - 1].getPositionX() - 1 && cell.getPositionY() == cells[cells.length - 1].getPositionY()) {
                    cell.setPositionX(cell.getPositionX() + 1);
                    cells[cells.length - 1].setPositionX(cells[cells.length - 1].getPositionX() - 1);
                    return 1;
                }
            }
            return 0;
        }
        if(n==1 && cells[cells.length-1].getPositionY()!=0){       //上侧块下移
            for (Cell cell : cells) {
                if (cell.getPositionX() == cells[cells.length - 1].getPositionX() && cell.getPositionY() == cells[cells.length - 1].getPositionY() - 1) {
                    cell.setPositionY(cell.getPositionY() + 1);
                    cells[cells.length - 1].setPositionY(cells[cells.length - 1].getPositionY() - 1);
                    return 1;
                }
            }
            return 0;
        }
        if(n==2 && cells[cells.length-1].getPositionX()!=settings.getOrder()-1){       //右侧块左移
            for (Cell cell : cells) {
                if (cell.getPositionX() == cells[cells.length - 1].getPositionX() + 1 && cell.getPositionY() == cells[cells.length - 1].getPositionY()) {
                    cell.setPositionX(cell.getPositionX() - 1);
                    cells[cells.length - 1].setPositionX(cells[cells.length - 1].getPositionX() + 1);
                    return 1;
                }
            }
            return 0;
        }
        if(n==3 && cells[cells.length-1].getPositionY()!=settings.getOrder()-1){       //下侧块上移
            for (Cell cell : cells) {
                if (cell.getPositionX() == cells[cells.length - 1].getPositionX() && cell.getPositionY() == cells[cells.length - 1].getPositionY() + 1) {
                    cell.setPositionY(cell.getPositionY() - 1);
                    cells[cells.length - 1].setPositionY(cells[cells.length - 1].getPositionY() + 1);
                    return 1;
                }
            }
            return 0;
        }
        else return 0;
    }

    public boolean IsWin(){
        for (int i=0; i< cells.length; i++) {
            if ((cells[i].getPositionX() + cells[i].getPositionY() * settings.getOrder()) != cells[i].getID()) {
                System.out.println("第"+ i +"个位置不对");
                return false;
            }
        }
        isWine = true;
        return true;
    }

    public void preStep(){
        System.out.println("开始处理上一步");
        if(isWine) return;
        if(steps.hasPre()){
            int t = steps.getPre();
            System.out.println("原步骤是"+t);
            switch (t){
                case 0: t = 2; break;
                case 1: t = 3; break;
                case 2: t = 0; break;
                case 3: t = 1; break;
                default:
                    throw new IllegalStateException("Unexpected value: " + t);
            }
            int r = move(t);
            System.out.println("移动步骤是"+t+"   还原返回值"+r);
            viewCells();
            steps.printAll();
        }
    }

    public void nextStep(){
        System.out.println("开始处理下一步");
        if(isWine) return;
        if(steps.hasNext()){
            int t = steps.getNext();
            System.out.println("原步骤是"+t);
            int r = move(t);
            System.out.println("移动步骤是"+t+"   还原返回值"+r);
            viewCells();
            steps.printAll();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(isWine) return;
        Cell t = (Cell)e.getSource();
        if(t.getPositionY() ==cells[cells.length-1].getPositionY() && t.getPositionX()==cells[cells.length-1].getPositionX()-1) {
            int r = this.move(0);
            if(r == 1) steps.add(0);
            viewCells();
            if (IsWin()) JOptionPane.showMessageDialog(null,"恭喜您游戏胜利！","提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(t.getPositionY() ==cells[cells.length-1].getPositionY() -1 && t.getPositionX()==cells[cells.length-1].getPositionX()) {
            int r = this.move(1);
            if(r == 1) steps.add(1);
            viewCells();
            if (IsWin()) JOptionPane.showMessageDialog(null,"恭喜您游戏胜利！","提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(t.getPositionY() ==cells[cells.length-1].getPositionY()  && t.getPositionX()==cells[cells.length-1].getPositionX()+1) {
            int r = this.move(2);
            if(r == 1) steps.add(2);
            viewCells();
            if (IsWin()) JOptionPane.showMessageDialog(null,"恭喜您游戏胜利！","提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(t.getPositionY() ==cells[cells.length-1].getPositionY() +1 && t.getPositionX()==cells[cells.length-1].getPositionX()) {
            int r = this.move(3);
            if(r == 1) steps.add(3);
            viewCells();
            if (IsWin()) JOptionPane.showMessageDialog(null,"恭喜您游戏胜利！","提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(isWine) return;
        if(e.getKeyCode()==KeyEvent.VK_D) {
            this.move(0);
            viewCells();
            if (IsWin()) JOptionPane.showMessageDialog(null,"恭喜您游戏胜利！","提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(e.getKeyCode()==KeyEvent.VK_S) {
            this.move(0);
            viewCells();
            if (IsWin()) JOptionPane.showMessageDialog(null,"恭喜您游戏胜利！","提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(e.getKeyCode()==KeyEvent.VK_A) {
            this.move(0);
            viewCells();
            if (IsWin()) JOptionPane.showMessageDialog(null,"恭喜您游戏胜利！","提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(e.getKeyCode()==KeyEvent.VK_W) {
            this.move(0);
            viewCells();
            if (IsWin()) JOptionPane.showMessageDialog(null,"恭喜您游戏胜利！","提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}