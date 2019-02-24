export class AppFileDownloadCtrl {

    appFileDownloadRestApiService: any;
    newFile: any;
    allFiles: any;
    availableIndices: any;
    $rootScope: any;
    searchIndex: any;

    constructor($rootScope, AppFileDownloadRestApi) {
        this.$rootScope = $rootScope;
        this.appFileDownloadRestApiService = AppFileDownloadRestApi;
        this.newFile = {};
        this.allFiles = [];
        this.availableIndices = [];

    }

    $onInit() {
        this.getFiles();
        this.getAvailableIndices();

        this.$rootScope.newFile = {
            allData: 'true',
            output: 'json'
        }

        this.$rootScope.$on("UpdateFiles", (event, item) => {
            this.getFiles();
        });
    }

    getAvailableIndices() {
        this.appFileDownloadRestApiService.getIndices()
            .then(indices => {
                this.availableIndices = indices.data;
                console.log(indices.data);
            });
    }

    getFiles() {
        this.appFileDownloadRestApiService.getAll()
            .then(allFiles => {
                this.allFiles = allFiles.data;
            });

    };

    createNewFile(file) {
        if(file.index !== null) {
            var start = new Date(file.timestampFrom).getTime();
            var end = new Date(file.timestampTo).getTime();
            var output = file.output;
            var allData = file.allData;
            this.appFileDownloadRestApiService.createFile(file.index.indexName, start, end, output, allData).then((msg) => {
                this.getFiles();
            });
        }
    };

    querySearch (query) {
        var filterValue = query.toLowerCase();

        return this.availableIndices.filter(index => index.indexName.toLowerCase().indexOf(filterValue) >= 0);
    }
}

AppFileDownloadCtrl.$inject = ['$rootScope', 'AppFileDownloadRestApi'];
