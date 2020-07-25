package com.itcanten;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/7/1 18:11
 */
public class KafkaProducer {
    private static final String brokerList="192.168.174.130:9092";
    private static final String topic="1709B-top";

    public static Properties init(){
        Properties props = new Properties();
        props.put("bootstrap.servers",brokerList);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
      // props.put("client.id","producer.client.id.demo");
        return props;
    }

    public static void main(String[] args) {
        Properties props = init();
        org.apache.kafka.clients.producer.KafkaProducer<String, String> stringStringKafkaProducer =
                new org.apache.kafka.clients.producer.KafkaProducer<String, String>(props);
        //消息存入的主题 发送的消息  ProducerRecord生产
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "6666");
        stringStringKafkaProducer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(e!=null){
                    e.printStackTrace(); }
                else{
                    recordMetadata.partition();
                    recordMetadata.offset();
                }

            }
        });
        stringStringKafkaProducer.close();

        //发送消息
        //stringStringKafkaProducer.send(record);
        /*Properties props = init();
        org.apache.kafka.clients.producer.KafkaProducer<String, String> objectObjectKafkaProducer =
                new org.apache.kafka.clients.producer.KafkaProducer<>(props);
        ProducerRecord<String, String> hahah = new ProducerRecord<>(topic, "hahah777");

            objectObjectKafkaProducer.send(hahah);
        objectObjectKafkaProducer.close();*/
    }
}
