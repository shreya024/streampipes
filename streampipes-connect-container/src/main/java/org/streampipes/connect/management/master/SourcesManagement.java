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

package org.streampipes.connect.management.master;

import org.streampipes.connect.exception.AdapterException;
import org.streampipes.model.SpDataSet;
import org.streampipes.model.connect.adapter.AdapterSetDescription;
import org.streampipes.storage.couchdb.impl.AdapterStorageImpl;

public class SourcesManagement {


    private AdapterStorageImpl adapterStorage;

    public SourcesManagement(AdapterStorageImpl adapterStorage) {
        this.adapterStorage = adapterStorage;
    }

    public SourcesManagement() {
        this.adapterStorage = new AdapterStorageImpl();
    }

    public void addAdapter(String baseUrl, String streamId, SpDataSet dataSet) throws AdapterException {
        AdapterSetDescription adapterDescription = (AdapterSetDescription) this.adapterStorage.getAdapter(streamId);
        adapterDescription.setDataSet(dataSet);

        WorkerRestClient.invokeSetAdapter(baseUrl, adapterDescription);
    }

    public void detachAdapter(String baseUrl, String streamId, String runningInstanceId) throws AdapterException {
        AdapterSetDescription adapterDescription = (AdapterSetDescription) this.adapterStorage.getAdapter(streamId);

        WorkerRestClient.stopSetAdapter(baseUrl, adapterDescription);
    }
}
