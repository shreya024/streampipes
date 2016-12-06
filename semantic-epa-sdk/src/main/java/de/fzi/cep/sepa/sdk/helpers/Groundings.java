package de.fzi.cep.sepa.sdk.helpers;

import de.fzi.cep.sepa.model.impl.JmsTransportProtocol;
import de.fzi.cep.sepa.model.impl.KafkaTransportProtocol;
import de.fzi.cep.sepa.model.impl.TransportFormat;
import de.fzi.cep.sepa.model.vocabulary.MessageFormat;

/**
 * Created by riemer on 06.12.2016.
 */
public class Groundings {

    public static KafkaTransportProtocol kafkaGrounding(String kafkaHost, Integer kafkaPort, String topic) {
        return new KafkaTransportProtocol(kafkaHost, kafkaPort, topic, kafkaHost, kafkaPort);
    }

    public static JmsTransportProtocol jmsGrounding(String jmsHost, Integer jmsPort, String topic) {
        return new JmsTransportProtocol(jmsHost, jmsPort, topic);
    }

    public static TransportFormat jsonFormat() {
        return new TransportFormat(MessageFormat.Json);
    }

    public static TransportFormat thriftFormat() {
        return new TransportFormat(MessageFormat.Thrift);
    }
}
