app.controller('menuAdminController', [ "$scope", "$http","uiGridConstants","growl", function($scope, $http,uiGridConstants,growl) {
               
        function loadGridValues(){
            $http({
                url: "../../../Menu/List", 
                method: "POST"
            }).success(function(data) {
                $scope.gridOptions.data = data;
            });
        }
        
        $scope.editClick=function (id){
             $http({
                url: "../../../Menu/Edit", 
                method: "GET",
                params: {Id:id},
            }).success(function(data) {
                 $scope.menu = data;
            });
       }
       
       $scope.submit=function (){
           $http({
                url: "../../../Menu/Edit", 
                method: "POST",
                data: $scope.menu
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
                url: "../../../Menu/Delete", 
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
            $scope.gridOptions = {};

            $scope.gridOptions.enablePaging= true;
            $scope.gridOptions.showFooter= true;
            $scope.gridOptions.enablePaginationControls= false;
            $scope.gridOptions.paginationPageSizes= [25, 50, 75];
            $scope.gridOptions.virtualizationThreshold = 50;
            $scope.gridOptions.minRowsToShow=25;
        
          $scope.gridOptions.columnDefs = [
            { field: 'id', visible: true, displayName: 'Id' },
            { field: 'plan.desc', displayName: 'Plan' },
            { field: 'role.desc', displayName: 'Role' },
            { field: 'name', displayName: 'Name' },
            { field: 'url', displayName: 'Url' },
            { field: 'style', displayName: 'Style' },
            { field: 'displayOrder', displayName: 'Display Order' },
            { field: 'parent.name', displayName: 'Parent' },
            { field: 'Edit' , cellTemplate:'<div> <button type="button" class="btn btn-sm btn-success btn-block" ng-click="grid.appScope.editClick(row.entity.id)" data-toggle="modal" data-target="#myModal">Edit</button></div>'},
            { field: 'Delete' , cellTemplate:'<div><a class="btn btn-sm btn-success btn-block" ng-click="grid.appScope.deleteClick(row.entity.id)">Delete</a></div>'}
          ];
            
            $scope.gridOptions.onRegisterApi = function (gridApi) {
                $scope.gridApi = gridApi;
            }  
            
          loadGridValues(); 
        
            $http({
                 url: "../../../RoleSelectController", 
                 method: "GET"
             }).success(function(data) {
                  $scope.roles=data;
             });
             
             $http({
                 url: "../../../PlanSelectController", 
                 method: "GET"
             }).success(function(data) {
                  $scope.plans=data;
             });   
       }
       
       init();
 
}]);
