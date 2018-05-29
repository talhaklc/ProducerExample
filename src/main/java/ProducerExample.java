import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by tkilic on 28.05.2018.
 */
public class ProducerExample {
    public static void main(String[] args) throws InterruptedException {
        String topicName="search";
        String[] dizi = {"canta","cuzdan","ayakkabi","kalem","anahtar"};
        Properties configPro = new Properties();
        configPro.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configPro.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
        configPro.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        Producer producer=new KafkaProducer<String,String>(configPro);
        for(int i=0;i<dizi.length;i++) {
            ProducerRecord<String, String> rec = new ProducerRecord<String, String>
                    (topicName, dizi[i]);
            producer.send(rec);
            TimeUnit.SECONDS.sleep(2);
        }
        producer.close();
    }
}
