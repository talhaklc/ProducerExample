import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by tkilic on 29.05.2018.
 */
public class ConsumerExample {
    public static void main(String[] args) {
        String topicName="search";

        Properties configPro = new Properties();
        configPro.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configPro.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        configPro.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        configPro.put(ConsumerConfig.GROUP_ID_CONFIG,"buyukveriudemy2");
        configPro.put(ConsumerConfig.CLIENT_ID_CONFIG,"exam2");

        KafkaConsumer<String,String> kafkaConsumer;
        kafkaConsumer = new KafkaConsumer<String, String>(configPro);

        kafkaConsumer.subscribe(Arrays.asList(topicName));
        try {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String> record : records)
                    System.out.println(record.value());
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        kafkaConsumer.close();
    }
}
