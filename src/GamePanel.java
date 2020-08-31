import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel {

    private Settings settings;
    private Cell cells[] = new Cell[settings.getOrder()*settings.getOrder()];
    int ImageHeight;
    int ImageWidth;

    public GamePanel() {
        this.setLayout(null);
        init();
    }

    public void init() {
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
                bufnew = buf.getSubimage(i*singleSize, j*singleSize, singleSize, singleSize);
                icon = new ImageIcon(bufnew);
                cells[i*settings.getOrder()+j] = new Cell(icon, i*settings.getOrder()+j, singleSize, settings.getLable());

                if(i==j && i==(settings.getOrder()-1)){         //如果到了右下角，按钮的背景图设置为纯白，标签置空。
                    ImageIcon iconWhite = new ImageIcon("pictures/white.jpg");
                    cells[i*settings.getOrder()+j].setIcon(iconWhite);
                    cells[i*settings.getOrder()+j].setText("");
                }
            }
        }
    }
}