package com.itcanteen;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;

import java.io.IOException;

/**
 * @author shuaixinxin
 * @email 1575465660@qq.com
 * @date 2020/6/28 10:57
 */
public class AppTest {
    public static void main(String[] args) throws IOException {
       /* BinaryLogClient client =
                new BinaryLogClient("localhost", 3306, "root", "root");
        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setCompatibilityMode(
                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
        );
        client.setEventDeserializer(eventDeserializer);
        client.registerEventListener(new BinaryLogClient.EventListener() {
            @Override
            public void onEvent(Event event) {
                System.out.println(event.toString());
            }
        });
        client.connect();*/
        BinaryLogClient client = new BinaryLogClient("127.0.0.1", 3306, "root", "root");
        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setCompatibilityMode(
                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
        );
        client.setEventDeserializer(eventDeserializer);
        client.registerEventListener(new BinaryLogClient.EventListener() {

            @Override
            public void onEvent(Event event) {
                //判断 获取到的数据是上面类型  是否是写类型
                if(event.getData() instanceof WriteRowsEventData){
                    //如果是写的类型输出写的数据
                    System.out.println("=====insert=====");
                    //他肯定是个对象，只有对象才有tostring的方法
                    System.out.println(event.getData().toString());

                }
                if(event.getData() instanceof UpdateRowsEventData){
                    System.out.println("=====update=======");
                    System.out.println(event.getData().toString());
                }
            }

        });
        client.connect();
    }
}
