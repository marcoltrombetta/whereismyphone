app.controller('userAdminController', function($scope,$http,$state,$modal,growl,utilsService) {
       $scope.gridOptions = {};
        
        function loadGridValues(){
            utilsService.post({
                url: "../../../User/List", 
                method: "POST"
            }, function (data) {
                $scope.gridOptions.data = data;
            });
        }
        
       $scope.editClick=function (id){
           utilsService.post({
                url: "../../../User/Edit", 
                method: "GET",
                params: {Id:id}
            }, function (data) {
                $scope.user = data;
            });
       }
       
       $scope.submit=function (){
           utilsService.post({
                url: "../../../User/Edit", 
                method: "POST",
                data: $scope.user
            }, function (data) {
                growl.success('Saved.',{title: 'Success!'});
                $('#myModal').modal('hide');
                init();
               // $scope.gridOptions.data = data;
            });
       }
              
        $scope.deleteClick=function (id){
             utilsService.post({
                url: "../../../User/Delete", 
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
              { field: 'email', displayName: 'Email' },
              { field: 'role.desc', displayName: 'Role' },
              { field: 'Edit' , cellTemplate:'<div> <button type="button" class="btn btn-sm btn-success btn-block" ng-click="grid.appScope.editClick(row.entity.id)" data-toggle="modal" data-target="#myModal">Edit</button></div>'},
              { field: 'Delete' , cellTemplate:'<div><a class="btn btn-sm btn-success btn-block" ng-click="grid.appScope.deleteClick(row.entity.id)">Delete</a></div>'}
          ];

            $scope.gridOptions.virtualizationThreshold = 50;
            loadGridValues(); 
            
            $http({
                 url: "../../../RoleSelectController", 
                 method: "GET"
             }).success(function(data) {
                  $scope.roles=data;
             });

       }
       
       init();

});