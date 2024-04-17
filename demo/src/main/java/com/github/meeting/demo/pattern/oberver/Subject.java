package com.github.meeting.demo.pattern.oberver;

/**
 * Description: 主题
 *
 * @author pengpeng
 * @version 1.0
 * @since 2024/4/17
 */
public interface Subject {

    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String news);
}