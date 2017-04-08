app.controller('alarmAccionAdminController', function($scope,$http,growl,utilsService) {
       $scope.gridOptions = {};
        
        function loadGridValues(){
            utilsService.post({
                url: "../../../AlarmAccion/List", 
                method: "POST"
            }, function (data) {
                $scope.gridOptions.data = data;
            });
        }
        
       $scope.editClick=function (id){
           utilsService.post({
                 url: "../../../AlarmAccion/Edit", 
                method: "GET",
                params: {Id:id}
            }, function (data) {
                 $scope.alarmaccion = data;
            });
             
       }
       
       $scope.submit=function (){
           utilsService.post({
                url: "../../../AlarmAccion/Edit", 
                method: "POST",
                data: $scope.alarmaccion
            }, function (data) {
                 growl.success('Saved.',{title: 'Success!'});
                $('#myModal').modal('hide');
                init();
               // $scope.gridOptions.data = data;
            });
       }
              
        $scope.deleteClick=function (id){
            utilsService.post({
                url: "../../../AlarmAccion/Delete", 
                method: "POST",
                data: {Id:id}
            }, function (data) {
                growl.success('Deleted.',{title: 'Success!'});
                init();
                //$scope.gridOptions.data = data;
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