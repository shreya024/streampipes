/*
 * Copyright 2018 FZI Forschungszentrum Informatik
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.streampipes.connect.adapter;

import org.junit.Test;
import org.streampipes.model.connect.adapter.AdapterDescription;
import org.streampipes.model.connect.adapter.GenericAdapterSetDescription;
import org.streampipes.model.grounding.*;

import static org.junit.Assert.*;

public class GroundingServiceTest {

    @Test
    public void extractBrokerTest() {
        AdapterDescription adapterDescription = getAdapterDescription();

        String result = GroundingService.extractBroker(adapterDescription);

        assertEquals("localhost:1111", result);
    }


    @Test
    public void extractTopicTest() {
        AdapterDescription adapterDescription = getAdapterDescription();

        String result = GroundingService.extractTopic(adapterDescription);

        assertEquals("test.topic", result);
    }

    @Test
    public void createEventGroundingTest() {

        EventGrounding eventGrounding = GroundingService.createEventGrounding("localhost", 1, null);

        assertEquals("localhost", eventGrounding.getTransportProtocol().getBrokerHostname());
        assertEquals(1, ((KafkaTransportProtocol)eventGrounding.getTransportProtocol()).getKafkaPort());
        assertTrue(eventGrounding.getTransportProtocol().getTopicDefinition().getActualTopicName().startsWith("org.streampipes.connect"));

    }

    private AdapterDescription getAdapterDescription() {
        AdapterDescription adapterDescription = new GenericAdapterSetDescription();
        EventGrounding eventGrounding = new EventGrounding();
        KafkaTransportProtocol transportProtocol = new KafkaTransportProtocol();
        transportProtocol.setBrokerHostname("localhost");
        transportProtocol.setKafkaPort(1111);
        eventGrounding.setTransportProtocol(transportProtocol);
        adapterDescription.setEventGrounding(eventGrounding);

        TopicDefinition topicDefinition = new SimpleTopicDefinition("test.topic");
        transportProtocol.setTopicDefinition(topicDefinition);
        return adapterDescription;
    }

}