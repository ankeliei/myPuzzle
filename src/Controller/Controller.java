package Controller;

import Models.Cell;
import Models.Settings;
import Models.Steps;
import View.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Controller implements MouseListener {
    private GamePanel gamePanel;
    private Settings settings;
    private Steps steps;

    public Controller(Settings settings){
        this.settings = settings;
    }

    public void setGamePanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setSteps(Steps steps){
        this.steps = steps;
    }

    public void changeOrder(ItemEvent e){               //响应更改难度（阶数）的监听器
        if(e.getStateChange() == ItemEvent.SELECTED){
            if(e.getItem().toString()=="3"){
                settings.setOrder(3);
                System.out.println("切换为三阶");
            }
            if(e.getItem().toString()=="4"){
                settings.setOrder(4);
                System.out.println("切换为四阶");
            }
            if(e.getItem().toString()=="5"){
                settings.setOrder(5);
                System.out.println("切换为五阶");
            }
            if(e.getItem().toString()=="6"){
                settings.setOrder(6);
                System.out.println("切换为六阶");
            }
            if(e.getItem().toString()=="7"){
                settings.setOrder(7);
                System.out.println("切换为七阶");
            }
        }
    }

    public void changePicture(ItemEvent e){         //响应更改默认图片的监听器
        if(e.getStateChange() == ItemEvent.SELECTED) {
            if(e.getItem().toString()=="蒙娜丽莎（默认）"){
                settings.setFile(new File("src/pictures/蒙娜丽莎（默认）.jpg"));
                System.out.println("图片切换为：蒙娜丽莎（默认）");
            }
            if(e.getItem().toString()=="山水图"){
                settings.setFile(new File("src/pictures/山水图.jpg"));
                System.out.println("图片切换为：山水图");
            }
            if(e.getItem().toString()=="动漫图"){
                settings.setFile(new File("src/pictures/动漫图.jpg"));
                System.out.println("图片切换为：动漫图");
            }
        }
    }

    public void chooseFile(){      //响应自选图片
        System.out.println("点击了文件选择按钮");
        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setCurrentDirectory(new File("C:\\Users\\Administrator\\Desktop"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file != null){                  //选定图片后进行更改，避免对settings传入空值
                settings.setFile(file);
                System.out.println("图片切换为："+file);
            }
        }
    }

    public void changeTag(ItemEvent e){
        if(e.getStateChange()== ItemEvent.SELECTED){
            settings.setTag(true);
        }
        if (e.getStateChange()== ItemEvent.DESELECTED){
            settings.setTag(false);
        }
    }

    public void preStep(){
        System.out.println("开始处理上一步");
        if(gamePanel.getIsWin()) return;
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
        }
    }

    public void nextStep(){
        System.out.println("开始处理下一步");
        if(gamePanel.getIsWin()) return;
        if(steps.hasNext()){
            int t = steps.getNext();
            System.out.println("原步骤是"+t);
            int r = move(t);
            System.out.println("移动步骤是"+t+"   还原返回值"+r);
            viewCells();
        }
    }

    public int move(int n){                                             //单步移动方法
        if(n==0 && gamePanel.getCells()[gamePanel.getCells().length-1].getPositionX()!=0){       //左侧块右移
            for (Cell cell : gamePanel.getCells()) {
                if (cell.getPositionX() == gamePanel.getCells()[gamePanel.getCells().length - 1].getPositionX() - 1 && cell.getPositionY() == gamePanel.getCells()[gamePanel.getCells().length - 1].getPositionY()) {
                    cell.setPositionX(cell.getPositionX() + 1);
                    gamePanel.getCells()[gamePanel.getCells().length - 1].setPositionX(gamePanel.getCells()[gamePanel.getCells().length - 1].getPositionX() - 1);
                    return 1;
                }
            }
            return 0;
        }
        if(n==1 && gamePanel.getCells()[gamePanel.getCells().length-1].getPositionY()!=0){       //上侧块下移
            for (Cell cell : gamePanel.getCells()) {
                if (cell.getPositionX() == gamePanel.getCells()[gamePanel.getCells().length - 1].getPositionX() && cell.getPositionY() == gamePanel.getCells()[gamePanel.getCells().length - 1].getPositionY() - 1) {
                    cell.setPositionY(cell.getPositionY() + 1);
                    gamePanel.getCells()[gamePanel.getCells().length - 1].setPositionY(gamePanel.getCells()[gamePanel.getCells().length - 1].getPositionY() - 1);
                    return 1;
                }
            }
            return 0;
        }
        if(n==2 && gamePanel.getCells()[gamePanel.getCells().length-1].getPositionX()!=settings.getOrder()-1){       //右侧块左移
            for (Cell cell : gamePanel.getCells()) {
                if (cell.getPositionX() == gamePanel.getCells()[gamePanel.getCells().length - 1].getPositionX() + 1 && cell.getPositionY() == gamePanel.getCells()[gamePanel.getCells().length - 1].getPositionY()) {
                    cell.setPositionX(cell.getPositionX() - 1);
                    gamePanel.getCells()[gamePanel.getCells().length - 1].setPositionX(gamePanel.getCells()[gamePanel.getCells().length - 1].getPositionX() + 1);
                    return 1;
                }
            }
            return 0;
        }
        if(n==3 && gamePanel.getCells()[gamePanel.getCells().length-1].getPositionY()!=settings.getOrder()-1){       //下侧块上移
            for (Cell cell : gamePanel.getCells()) {
                if (cell.getPositionX() == gamePanel.getCells()[gamePanel.getCells().length - 1].getPositionX() && cell.getPositionY() == gamePanel.getCells()[gamePanel.getCells().length - 1].getPositionY() + 1) {
                    cell.setPositionY(cell.getPositionY() - 1);
                    gamePanel.getCells()[gamePanel.getCells().length - 1].setPositionY(gamePanel.getCells()[gamePanel.getCells().length - 1].getPositionY() + 1);
                    return 1;
                }
            }
            return 0;
        }
        else return 0;
    }

    public void viewCells() {
        gamePanel.removeAll();
        for (int i=0; i<settings.getOrder(); i++) {
            for (int j=0; j<settings.getOrder(); j++) {
                for(int k=0; k<gamePanel.getCells().length; k++) {
                    if(gamePanel.getCells()[k].getPositionX()==j && gamePanel.getCells()[k].getPositionY()==i){
                        gamePanel.add(gamePanel.getCells()[k]);
                        break;
                    }
                }
            }
        }
        gamePanel.revalidate();
    }

    public void OutofOrder(){
        Random random = new Random();           //第一步先随机发生一些步数
        for(int i=0; i<settings.getOrder()*settings.getOrder()*settings.getOrder();){
            int s = random.nextInt(4);
            i = i + move(s);
        }
        //第二部将不在右下角的白块归位
        int x =settings.getOrder()-1 - gamePanel.getCells()[gamePanel.getCells().length-1].getPositionX();
        int y =settings.getOrder()-1 - gamePanel.getCells()[gamePanel.getCells().length-1].getPositionY();

        for(int i=0; i<x; i++){
            move(2);
        }
        for(int i=0; i<y; i++){
            move(3);
        }
    }

    public boolean IsWin(){
        for (int i=0; i< gamePanel.getCells().length; i++) {
            if ((gamePanel.getCells()[i].getPositionX() + gamePanel.getCells()[i].getPositionY() * settings.getOrder()) != gamePanel.getCells()[i].getID()) {
                System.out.println("第"+ i +"个位置不对");
                return false;
            }
        }
        gamePanel.setIsWine(true);
        return true;
    }

    public void init() {
        gamePanel.setSettings(settings);
        steps = new Steps();
        gamePanel.setSteps(steps);
        gamePanel.setCells(new Cell[settings.getOrder()*settings.getOrder()]);
        GridLayout gy = new GridLayout(settings.getOrder(),settings.getOrder());        //设置一个网格布局
        gamePanel.setLayout(gy);
        gamePanel.setFocusable(true);

        int ImageHeight;
        int ImageWidth;
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

        int baseSize = (ImageWidth >= ImageHeight) ? ImageHeight : ImageWidth;      //选取基准尺寸

        if(ImageWidth >= ImageHeight)           //划定图片的使用范围
            startX = ( ImageWidth-ImageHeight )/2;
        else
            startY = ( ImageHeight-ImageWidth )/2;

        for (int i=0; i<settings.getOrder(); i++){
            for (int j=0; j<settings.getOrder(); j++){
                bufnew = buf.getSubimage(startX+j*singleSize, startY+i*singleSize, singleSize, singleSize);     //这里注意，i,j与X，Y的方向存在转换问题
                icon = new ImageIcon(bufnew);
                gamePanel.getCells()[i*settings.getOrder()+j] = new Cell(icon, i*settings.getOrder()+j, singleSize, settings.getTag(), j, i);
                gamePanel.getCells()[i*settings.getOrder()+j].addMouseListener(this);
                if(i==j && i==(settings.getOrder()-1)){         //如果到了右下角，按钮的背景图设置为纯白，标签置空。
                    ImageIcon iconWhite = new ImageIcon("src/pictures/white.jpg");
                    gamePanel.getCells()[i*settings.getOrder()+j].setIcon(iconWhite);
                    gamePanel.getCells()[i*settings.getOrder()+j].setText("");
                }
            }
        }
        OutofOrder();       //打乱顺序
        viewCells();        //显示游戏面板
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(gamePanel.getIsWin()) return;
        Cell t = (Cell)e.getSource();
        if(t.getPositionY() ==gamePanel.getCells()[gamePanel.getCells().length-1].getPositionY() && t.getPositionX()==gamePanel.getCells()[gamePanel.getCells().length-1].getPositionX()-1) {
            int r = this.move(0);
            if(r == 1) steps.add(0);
            viewCells();
            if (IsWin()) JOptionPane.showMessageDialog(null,"恭喜您游戏胜利！","提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(t.getPositionY() ==gamePanel.getCells()[gamePanel.getCells().length-1].getPositionY() -1 && t.getPositionX()==gamePanel.getCells()[gamePanel.getCells().length-1].getPositionX()) {
            int r = this.move(1);
            if(r == 1) steps.add(1);
            viewCells();
            if (IsWin()) JOptionPane.showMessageDialog(null,"恭喜您游戏胜利！","提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(t.getPositionY() ==gamePanel.getCells()[gamePanel.getCells().length-1].getPositionY()  && t.getPositionX()==gamePanel.getCells()[gamePanel.getCells().length-1].getPositionX()+1) {
            int r = this.move(2);
            if(r == 1) steps.add(2);
            viewCells();
            if (IsWin()) JOptionPane.showMessageDialog(null,"恭喜您游戏胜利！","提示",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(t.getPositionY() ==gamePanel.getCells()[gamePanel.getCells().length-1].getPositionY() +1 && t.getPositionX()==gamePanel.getCells()[gamePanel.getCells().length-1].getPositionX()) {
            int r = this.move(3);
            if(r == 1) steps.add(3);
            viewCells();
            if (IsWin()) JOptionPane.showMessageDialog(null,"恭喜您游戏胜利！","提示",JOptionPane.ERROR_MESSAGE);
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
}
