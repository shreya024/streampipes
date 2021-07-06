/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import { Component, Input } from '@angular/core';
import { AdapterDescriptionUnion } from '../../../core-model/gen/streampipes-model';
import { DialogRef } from '../../../core-ui/dialog/base-dialog/dialog-ref';
import { DataMarketplaceService } from '../../services/data-marketplace.service';

@Component({
    selector: 'sp-delete-adapter-dialog',
    templateUrl: './delete-adapter-dialog.component.html',
    styleUrls: ['./delete-adapter-dialog.component.scss']
})
export class DeleteAdapterDialogComponent {

    @Input()
    adapter: AdapterDescriptionUnion;

    isInProgress = false;
    currentStatus: any;

    constructor(private dialogRef: DialogRef<DeleteAdapterDialogComponent>,
                private dataMarketplaceService: DataMarketplaceService) {
    }

    close(refreshAdapters: boolean) {
        this.dialogRef.close(refreshAdapters);
    }

    deleteAdapter() {
        this.isInProgress = true;
        this.currentStatus = 'Deleting adapter...';

        this.dataMarketplaceService.deleteAdapter(this.adapter).subscribe(data => {
            this.close(true);
        });
    }

}
