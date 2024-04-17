package com.github.meeting.demo.pattern.oberver;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 发布 News
 * @author pengpeng
 * @version 1.0
 * @since 2024/4/17
 */
public class NewsPublisher implements Subject {

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String news) {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }

    public void publishNews(String news) {
        System.out.println("发布新闻: " + news);
        notifyObservers(news);
    }
}