app.controller("positionHistoryReportController", [ "$scope", "$http", function($scope, $http) {

            function init(){             
                $scope.filter={};
                $scope.filter.dateFrom='';
                $scope.filter.dateTo='';
                
                 $http.get("../../../PhonesSelectController").success(function(data) {
                    $scope.filter.phones = data;
                });
            }

            $scope.filterClick=function (){
                 $http({
                    url: "../../../PositionHistoryController", 
                    method: "POST",
                    data: $scope.filter,
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).success(function(data) {
                    $scope.data = data;
                });
            }
                                
            init();
            
        }]);

//para reporte por mes, con grafico para ver count de latitude, longitude en un dia o rango de dias. Tambien mostrar grilla con esos datos
//select count(*),Latitude_PosI, Longitude_PosI, Date_PosI from positioninfo_hist where Date_PosI like '2016-06-15%' group by Latitude_PosI