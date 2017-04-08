app.controller('phoneinfoAdminController', function($scope,$http,$state) {
       $scope.gridOptions = {};
       
        function loadGridValues(){
            $http({
                url: "../../../PhoneInfo/List", 
                method: "POST"
            }).success(function(data) {
                $scope.gridOptions.data = data;
            });
        }
       
        $scope.deleteClick=function (id){
            $http({
                url: "../../../PhoneInfo/Delete", 
                method: "POST",
                data: {Id:id}
            }).success(function(data) {
                $scope.gridOptions.data = data;
            });
       }
       
        function init(){         
          $scope.gridOptions.columnDefs = [
              { field: 'id', visible: true, displayName: 'Id' },
              { field: 'imei', displayName: 'Imei' },
              { field: 'modelo', displayName: 'Model' },
             // { field: 'Edit' ,width:70, cellTemplate:'<div><a class="btn btn-sm btn-success btn-block" ng-click="grid.appScope.editClick(row.entity.id)">Edit</a></div>'},
              { field: 'Delete' ,width:70, cellTemplate:'<div><a class="btn btn-sm btn-success btn-block" ng-click="grid.appScope.deleteClick(row.entity.id)">Delete</a></div>'}
          ];
          
          $scope.gridOptions.virtualizationThreshold = 50;

          loadGridValues();
       }
       
       init();
});