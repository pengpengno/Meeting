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
public class Main {


    public static void main(String[] args) {
        // 创建新闻发布者
        NewsPublisher publisher = new NewsPublisher();

        // 创建两个观察者（读者）
        Observer reader1 = new Reader("小明");
        Observer reader2 = new Reader("小红");

        // 注册观察者
        publisher.registerObserver(reader1);
        publisher.registerObserver(reader2);

        // 发布新闻
        publisher.publishNews("Java 21发布了！");
        publisher.publishNews("Spring Framework 更新了！");

        // 读者取消订阅
        publisher.removeObserver(reader2);

        // 再次发布新闻
        publisher.publishNews("小米 su7 上线了，雷总为你开车门，快行动起来吧！");
    }
}