package com.qintingshuang.web.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qintingshuang
 * @create 2021-04-23 15:40
 * @description rabbitMq配置，其中有死信交换机配置
 *
 *   topic类型交换机，routingkey表达式说明
 *     *（星号）可以替代一个单词
 *    ＃（hash）可以替换零个或多个单词
 *    符合"*.#.5s"的例如: qin.5s,qin.haha.5s,qin.haha.hhhh.5s。。。。。。
 *
 **/
@Configuration
public class RabbitConfig {



    /** 接收延迟消息的交换机  */
    public static final String DELAY_EXCHANGE="delay";


    /**
     * 各个时间段的延迟队列(没有消费者，过期后成为死信转发到死信交换机)
     */
    public static final String DELAY_QUEUE_5S="delay_5s";
    public static final String DELAY_BINDING_KEY_5S="#.5s";

    public static final String DELAY_QUEUE_1MIN="delay_1min";
    public static final String DELAY_BINDING_KEY_1MIN="#.1min";

    public static final String DELAY_QUEUE_5MIN="delay_5min";
    public static final String DELAY_BINDING_KEY_5MIN="#.5min";

    public static final String DELAY_QUEUE_30MIN="delay_30min";
    public static final String DELAY_BINDING_KEY_30MIN="#.30min";
    //各个时间段的延迟队列(没有消费者，过期后成为死信转发到死信交换机)

    /**  死信交换机  */
    public static final String DLX_EXCHANGE=" dlx";


    public static final String  WORK_QUERY_QUEUE=" work_query";
    public static final String  WORK_QUERY_BINDING_KEY="query.#";


    public static final String  WORK_NOTIFY_QUEUE=" work_notify";
    public static final String  WORK_NOTIFY_BINDING_KEY="notify.#";


    public static final String  WORK_SEPARATE_QUEUE=" work_separate";
    public static final String  WORK_SEPARATE_BINDING_KEY="separate.#";

    @Bean
    public TopicExchange delayExchange() {
        return new TopicExchange(DELAY_EXCHANGE, true, false);
    }

    @Bean
    public Queue delayQueue5s() {
        Map<String, Object> map=new HashMap<>();
        //设置整个队列的消息有效期(如果声明队列和发送消息时都设置了有效期，则以较小的为准)
        //设置消息有效期5秒，过期后变成死信消息，然后进入死信交换机
        map.put("x-message-ttl" , 5000);
        //消息转发到死信交换机时携带的routing key,如果不设置默认携带发送消息时设置的routing key
//        map.put("x-dead-letter-routing-key" , "delay.queue");
        //设置死信交换机
        map.put("x-dead-letter-exchange",DLX_EXCHANGE);
        return new Queue(DELAY_QUEUE_5S, true,false,false,map);
    }

    @Bean
    public Binding delayBinding5s() {
        return BindingBuilder.bind(delayQueue5s()).to(delayExchange()).with(DELAY_BINDING_KEY_5S);
    }

    /**
     * 设置消息有效期60秒，过期后变成死信消息，然后进入死信交换机
     * @return
     */
    @Bean
    public Queue delayQueue1min() {
        Map<String, Object> map=new HashMap<>();
        map.put("x-message-ttl" , 60000);
        map.put("x-dead-letter-exchange",DLX_EXCHANGE);
        return new Queue(DELAY_QUEUE_1MIN, true,false,false,map);
    }

    @Bean
    public Binding delayBinding1min() {
        return BindingBuilder.bind(delayQueue1min()).to(delayExchange()).with(DELAY_BINDING_KEY_1MIN);
    }

    /**
     * 设置消息有效期300秒，过期后变成死信消息，然后进入死信交换机
     * @return
     */
    @Bean
    public Queue delayQueue5min() {
        Map<String, Object> map=new HashMap<>();
        map.put("x-message-ttl" , 300000);
        map.put("x-dead-letter-exchange",DLX_EXCHANGE);
        return new Queue(DELAY_QUEUE_5MIN, true,false,false,map);
    }

    @Bean
    public Binding delayBinding5min() {
        return BindingBuilder.bind(delayQueue5min()).to(delayExchange()).with(DELAY_BINDING_KEY_5MIN);
    }

    /**
     * 设置消息有效期1800秒，过期后变成死信消息，然后进入死信交换机
     * @return
     */
    @Bean
    public Queue delayQueue30min() {
        Map<String, Object> map=new HashMap<>();
        map.put("x-message-ttl" , 1800*1000);
        map.put("x-dead-letter-exchange",DLX_EXCHANGE);
        return new Queue(DELAY_QUEUE_30MIN, true,false,false,map);
    }

    @Bean
    public Binding delayBinding30min() {
        return BindingBuilder.bind(delayQueue30min()).to(delayExchange()).with(DELAY_BINDING_KEY_30MIN);
    }

    @Bean
    public TopicExchange dlxExchange() {
        return new TopicExchange(DLX_EXCHANGE, true, false);
    }

    @Bean
    public Queue payWorkQueryQueue() {
        return new Queue( WORK_QUERY_QUEUE);
    }

    @Bean
    public Binding payWorkQueryBinding() {
        return BindingBuilder.bind(payWorkQueryQueue()).to(dlxExchange()).with( WORK_QUERY_BINDING_KEY);
    }

    @Bean
    public Queue payWorkNotifyQueue() {
        return new Queue( WORK_NOTIFY_QUEUE);
    }

    @Bean
    public Binding payWorkNotifyBinding() {
        return BindingBuilder.bind(payWorkNotifyQueue()).to(dlxExchange()).with( WORK_NOTIFY_BINDING_KEY);
    }

    @Bean
    public Queue payWorkSeparateQueue() {
        return new Queue( WORK_SEPARATE_QUEUE);
    }

    @Bean
    public Binding payWorkSeparateBinding() {
        return BindingBuilder.bind(payWorkSeparateQueue()).to(dlxExchange()).with( WORK_SEPARATE_BINDING_KEY);
    }

}
