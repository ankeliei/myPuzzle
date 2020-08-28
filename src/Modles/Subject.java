package Modles;

import Views.Begin;

import java.util.ArrayList;
import java.util.List;
//import java.util.Observable;
import Views.Observer;

public abstract class Subject {
    private List<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers() {              //遍历通知订阅的视图类
        for (Observer observer : observers) observer.update();
    }
}
