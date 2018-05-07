export class PipelineDetailsController {

    pipelines: any;
    starting: any;
    stopping: any;
    refreshPipelines: any;
    PipelineOperationsService: any;
    activeCategory: any;
    dtOptions = {paging: false, searching: false,   "order": [], "columns": [
            { "orderable": false },
            null,
            null,
            { "orderable": false },
        ]};

    constructor(PipelineOperationsService) {
        this.PipelineOperationsService = PipelineOperationsService;
        this.starting = false;
        this.stopping = false;
        this.toggleRunningOperation = this.toggleRunningOperation.bind(this);


        // if (this.pipeline.immediateStart) {
        //     if (!this.pipeline.running) {
        //         this.PipelineOperationsService.startPipeline(this.pipeline._id, this.toggleRunningOperation, this.refreshPipelines);
        //     }
        // }
    }

    toggleRunningOperation(currentOperation) {
        if (currentOperation === 'starting') {
            this.starting = !(this.starting);
        } else {
            this.stopping = !(this.stopping);
        }
    }

}

PipelineDetailsController.$inject = ['PipelineOperationsService'];