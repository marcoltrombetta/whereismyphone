app.service('utilsService', function ($http, $state, growl, $rootScope) {

    this.errorMessage = function (text) {
        growl.error(text, { title: 'Error!', ttl: 5000 });
    }

    this.successMessage = function (text) {
        growl.success(text, { title: 'Success!', ttl: 3000 });
    }

    this.warnMessage = function () {
        growl.warning("Espere...", { title: 'Cargando', ttl: 3000 });
    }

    this.post = function (config, onsuccess) {
        var message = growl.warning("Espere...", { title: 'Procesando' });
        $http(config).success(function (data) {
            if (message != undefined) {
                message.destroy();
            }
            if (data == "err") {
                //$state.go("app.login");
            } else if (data == "datosinc") {
                //growl.error("Los datos ingresados son incorrectos", { title: 'Error!', ttl: 5000 });
            } else {
                onsuccess(data);
            }
        }).error(function (err) {
            growl.error(err, { title: 'Error!', ttl: 5000 });
        });
    }

    this.gridOptions = function (coldef) {
        var gridOptions = {
            enableSelectAll: true,
            exporterMenuCsv: false,
            exporterFilter: false,
            gridMenuShowHideColumns: false,
            exporterSuppressColumns: ['Editar', 'Eliminar', 'Id'],
            exporterMenuAllData : false,
            exporterCsvFilename: 'myFile.csv',
            exporterPdfDefaultStyle: { fontSize: 9 },
            //exporterPdfTableStyle: { margin: [30, 30, 30, 30] },
            exporterPdfTableHeaderStyle: { fontSize: 10, bold: true, italics: true, color: 'red' },
            exporterPdfFooter: function (currentPage, pageCount) {
                return { text: currentPage.toString() + ' of ' + pageCount.toString(), style: 'footerStyle' };
            },
            exporterPdfCustomFormatter: function (docDefinition) {
                docDefinition.styles.headerStyle = { fontSize: 22, bold: true };
                docDefinition.styles.footerStyle = { fontSize: 10, bold: true };
                return docDefinition;
            },
            exporterPdfOrientation: 'portrait',
            exporterPdfPageSize: 'LETTER',
            exporterPdfMaxGridWidth: 500,
            exporterCsvLinkElement: angular.element(document.querySelectorAll(".custom-csv-link-location")),
            
        };
       
        gridOptions.columnDefs = coldef;
        gridOptions.virtualizationThreshold = 1000;
        return gridOptions;
    }

    this.uploadFileToUrl = function (file, uploadUrl, onsuccess) {
        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        })
        .success(function (data) {
            onsuccess(data);
        })
        .error(function () {
        });
    }
   
});