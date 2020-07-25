package com.itcanten;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/7/1 20:23
 */
public class MyKafkaProducer {
    private static final String brokerList = "192.168.174.130:9092";
    private static final String topic = "1709B-top";
    private static Properties initConfig() {
        Properties properties = new Properties();
        /* properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,brokerList);*/
        properties.put("bootstrap.servers", brokerList);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        properties.put("client.id", "producer.client.id.demo");
        return properties;
    }
    public static void main(String[] args) {
        Properties properties = initConfig();
        org.apache.kafka.clients.producer.KafkaProducer<String, String> producer =
                new org.apache.kafka.clients.producer.KafkaProducer<>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "887788888888");
        producer.send(record);
        //producer.close();

    /*    try {
            //方法一 发送 无回调函数  通过异常是否发送成功
            producer.send(record).get();
            // 方法二 发送 有回调函数 通过返回对象 可以看到分区
            Future<RecordMetadata> send = producer.send(record);

            RecordMetadata recordMetadata = send.get();

            //进入的分区 topic 存放位置的
            System.out.println(recordMetadata.partition());
            //偏移量就是id
            System.out.println(recordMetadata.offset());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/



    }
}