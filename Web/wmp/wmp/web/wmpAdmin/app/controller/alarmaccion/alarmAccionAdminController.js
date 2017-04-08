app.controller('alarmAccionAdminController', function($scope,$http,growl) {
       $scope.gridOptions = {};
        
        function loadGridValues(){
            $http({
                url: "../../../AlarmAccion/List", 
                method: "POST"
            }).success(function(data) {
                $scope.gridOptions.data = data;
            });
        }
        
       $scope.editClick=function (id){
             $http({
                url: "../../../AlarmAccion/Edit", 
                method: "GET",
                params: {Id:id},
            }).success(function(data) {
                 $scope.alarmaccion = data;
            });
       }
       
       $scope.submit=function (){
           $http({
                url: "../../../AlarmAccion/Edit", 
                method: "POST",
                data: $scope.alarmaccion
            }).success(function(data) {
                growl.success('Saved.',{title: 'Success!'});
                $('#myModal').modal('hide');
                init();
               // $scope.gridOptions.data = data;
            }).error(function(err){
                growl.error(err,{title: 'Error!'});
            });
       }
              
        $scope.deleteClick=function (id){
            $http({
                url: "../../../AlarmAccion/Delete", 
                method: "POST",
                data: {Id:id}
            }).success(function(data) {
                growl.success('Deleted.',{title: 'Success!'});
                init();
                //$scope.gridOptions.data = data;
            }).error(function(err){
                growl.error(err,{title: 'Error!'});
            });
       }
       
        function init(){         
          $scope.gridOptions.columnDefs = [
              { field: 'id', visible: true, displayName: 'Id' },
              { field: 'desc', displayName: 'Desc' },
              { field: 'plan.desc', displayName: 'Plan' },
              { field: 'Edit' , cellTemplate:'<div> <button type="button" class="btn btn-sm btn-success btn-block" ng-click="grid.appScope.editClick(row.entity.id)" data-toggle="modal" data-target="#myModal">Edit</button></div>'},
              { field: 'Delete' , cellTemplate:'<div><a class="btn btn-sm btn-success btn-block" ng-click="grid.appScope.deleteClick(row.entity.id)">Delete</a></div>'}
          ];

            $scope.gridOptions.virtualizationThreshold = 50;
            loadGridValues(); 
            
            $http({
                 url: "../../../PlanSelectController", 
                 method: "GET"
             }).success(function(data) {
                  $scope.plans=data;
             });

       }
       
       init();

});