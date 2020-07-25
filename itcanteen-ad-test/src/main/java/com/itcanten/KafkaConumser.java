package com.itcanten;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/7/1 23:17
 */
public class KafkaConumser {
    private static final String brokerList="192.168.174.130:9092";
  private static   Properties props;
    static {
        //1.配置消费者创建实例
         props = new Properties();
        props.put("bootstrap.servers",brokerList);
        props.put("key.deserializer", StringDeserializer.class.getName());

        props.put("value.deserializer",StringDeserializer.class.getName());
        props.put("group.id","test-consumer-group");
    }
    @SuppressWarnings("ALL")
    public static void test1(){
        //默认自动提交位移
        props.put("enable auto commit","false");
        //1.配置消费者创建实例
        KafkaConsumer<String, String> objectObjectKafkaConsumer = new KafkaConsumer<>(props);
        //2.订阅主题
        objectObjectKafkaConsumer.subscribe(Collections.singletonList("1709B-top"));
        //3.拉取消息并消费  通过死循环

        try {
            while (true){
                ConsumerRecords<String, String> poll = objectObjectKafkaConsumer.poll(100);
                for (ConsumerRecord<String, String> stringStringConsumerRecord : poll) {
                    System.out.println(stringStringConsumerRecord.topic());
                    System.out.println(stringStringConsumerRecord.offset());
                    System.out.println(stringStringConsumerRecord.value());
                    System.out.println(stringStringConsumerRecord.partition());
                }
                //提交动作  同步--->线程造成堵塞
                objectObjectKafkaConsumer.commitAsync();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            //无论同步提交是否成功这里都通过异步进行再次提交
            objectObjectKafkaConsumer.commitSync();
            objectObjectKafkaConsumer.close();
        }
    }
    public static void main(String[] args) {
        test1();
    }

    public static void test3(){

    }
}
