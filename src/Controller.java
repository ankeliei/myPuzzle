import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.Random;

public class Controller {
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
        if(gamePanel.isWine()) return;
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
            int r = gamePanel.move(t);
            System.out.println("移动步骤是"+t+"   还原返回值"+r);
            gamePanel.viewCells();
            steps.printAll();
        }
    }

    public void nextStep(){
        System.out.println("开始处理下一步");
        if(gamePanel.isWine()) return;
        if(steps.hasNext()){
            int t = steps.getNext();
            System.out.println("原步骤是"+t);
            int r = gamePanel.move(t);
            System.out.println("移动步骤是"+t+"   还原返回值"+r);
            gamePanel.viewCells();
            steps.printAll();
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
}
