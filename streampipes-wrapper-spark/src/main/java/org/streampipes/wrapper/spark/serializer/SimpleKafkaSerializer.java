package org.streampipes.wrapper.spark.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by Jochen Lutz on 2017-12-21.
 */
public class SimpleKafkaSerializer implements VoidFunction<org.apache.spark.api.java.JavaRDD<java.util.Map<java.lang.String,java.lang.Object>>> {

    private final Map kafkaParams;
    //private final KafkaProducer<String, String> producer;
    private final String topic;

    public SimpleKafkaSerializer(Map kafkaParams, String topicName) {
        //producer = new KafkaProducer<String, String>(kafkaParams);
        this.topic = topicName;
        System.out.println("Sending output to Kafka topic '" + topicName + "'");
        this.kafkaParams = kafkaParams;
    }

    @Override
    public void call(JavaRDD<Map<String, Object>> javaRDD) throws Exception {
        System.out.println("Sending Kafka output");

        javaRDD.foreach(new VoidFunction<Map<String, Object>>() {
            private static final long serialVersionUID = 1L;

            private final ObjectMapper objectMapper = new ObjectMapper();

            @Override
            public void call(Map<String, Object> map) throws Exception {
                KafkaProducer<String, String> producer = new KafkaProducer<String, String>(kafkaParams);

                producer.send(new ProducerRecord<String, String>(topic, objectMapper.writeValueAsString(map)));
            }
        });
    }
}
