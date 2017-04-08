app.controller('userAdminController', function($scope,$http,$state,$modal,growl) {
       $scope.gridOptions = {};
        
        function loadGridValues(){
            $http({
                url: "../../../User/List", 
                method: "POST"
            }).success(function(data) {
                $scope.gridOptions.data = data;
            });
        }
        
       $scope.editClick=function (id){
             $http({
                url: "../../../User/Edit", 
                method: "GET",
                params: {Id:id},
            }).success(function(data) {
                 $scope.user = data;
            });
       }
       
       $scope.submit=function (){
           $http({
                url: "../../../User/Edit", 
                method: "POST",
                data: $scope.user
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
                url: "../../../User/Delete", 
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