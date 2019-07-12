/*
 * Copyright 2019 FZI Forschungszentrum Informatik
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

package org.streampipes.connect.adapter.model.generic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.streampipes.connect.adapter.Adapter;
import org.streampipes.model.connect.adapter.GenericAdapterDescription;
import org.streampipes.model.connect.adapter.GenericAdapterSetDescription;

public class GenericDataSetAdapter extends GenericAdapter<GenericAdapterSetDescription> {

    public static final String ID = GenericAdapterSetDescription.ID;

    Logger logger = LoggerFactory.getLogger(Adapter.class);

    public GenericDataSetAdapter() {
        super();
    }


    public GenericDataSetAdapter(GenericAdapterSetDescription adapterDescription, boolean debug) {
        super(adapterDescription, debug);
    }

    public GenericDataSetAdapter(GenericAdapterSetDescription adapterDescription) {
        super(adapterDescription);
    }

    @Override
    public GenericAdapterSetDescription declareModel() {
        GenericAdapterSetDescription adapterDescription = new GenericAdapterSetDescription();
        adapterDescription.setAdapterId(GenericAdapterSetDescription.ID);
        adapterDescription.setUri(GenericAdapterSetDescription.ID);
        adapterDescription.setAppId(GenericAdapterSetDescription.ID);
        return adapterDescription;
    }

    @Override
    public Adapter getInstance(GenericAdapterSetDescription adapterDescription) {
        return  new GenericDataSetAdapter(adapterDescription);
    }

    @Override
    public String getId() {
        return ID;
    }

    public void stopAdapter() {
        protocol.stop();
    }

    @Override
    public GenericAdapterDescription getAdapterDescription() {
        return adapterDescription;
    }

    @Override
    public void setProtocol(Protocol protocol) {
       this.protocol = protocol;
    }
}
