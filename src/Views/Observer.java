package Views;

public abstract class Observer {
    protected Modles.Subject subject;
    public abstract void update();
}