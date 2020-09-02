import java.io.File;

public class Settings {
    private int order;
    private File file;
    private boolean tag;

    public Settings(){
        order = 3;
        file = new File("src/pictures/蒙娜丽莎（默认）.jpg");
        tag = false;
    }

    public boolean getTag() {
        return tag;
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

    public void setTag(boolean tag) {
        this.tag = tag;
    }
}
