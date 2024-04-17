package com.github.meeting.demo.pattern.oberver;

/**
 * Description:
 * <p>
 * </p>
 *
 * @author pengpeng
 * @version 1.0
 * @since 2024/4/17
 */
// 观察者 - 读者
public class Reader implements Observer {
    private String name;

    public Reader(String name) {
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " 收到新闻: " + news);
    }
}
