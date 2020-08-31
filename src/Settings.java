import java.io.File;

public class Settings {
    private int order;
    private File file;
    private boolean lable;

    public Settings(){
        order = 3;
        file = new File("C:\\Users\\Administrator\\IdeaProjects\\myPuzzle\\pictures\\蒙娜丽莎（默认）.jpg");
        lable = false;
    }

    public boolean getLable() {
        return lable;
    }

    public int getOrder() {
        return order;
    }

    public File getFile() {
        return file;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setLable(boolean lable) {
        this.lable = lable;
    }
}
