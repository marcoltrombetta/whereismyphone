app.controller('phonenamesAdminController', ['$scope', '$http','$state','growl',function($scope,$http,$state,growl) {
       $scope.gridOptions = {};
       
        function loadGridValues(){
            $http({
                url: "../../../PhoneNames/List", 
                method: "POST"
            }).success(function(data) {
                $scope.gridOptions.data = data;
            });
        }

       $scope.editClick=function (id){
            $http({
                url: "../../../PhoneNames/Edit", 
                method: "GET",
                params: {Id:id},
            }).success(function(data) {
                 $scope.phonenames = data;
            });
       }
       
        $scope.submit=function (){
           $http({
                url: "../../../PhoneNames/Edit", 
                method: "POST",
                data: $scope.phonenames
            }).success(function(data) {
                growl.success('Saved.',{title: 'Success!'});
                init();
                $('#myModal').modal('hide');
               // $scope.gridOptions.data = data;
            }).error(function(err){
                growl.error(err,{title: 'Error!'});
            });
       }
              
        function init(){         
          $scope.gridOptions.columnDefs = [
              { field: 'id', visible: true, displayName: 'Id' },
              { field: 'desc', displayName: 'Name' },
              { field: 'phoneInfo.imei', displayName: 'Imei' },
              { field: 'phoneInfo.modelo', displayName: 'Model' },
              { field: 'Edit' , cellTemplate:'<div> <button type="button" class="btn btn-sm btn-success btn-block" ng-click="grid.appScope.editClick(row.entity.id)" data-toggle="modal" data-target="#myModal">Edit</button></div>'}
              //{ field: 'Delete' , cellTemplate:'<div><a class="btn btn-sm btn-success btn-block" ng-click="grid.appScope.deleteClick(row.entity.id)">Delete</a></div>'}
          ];
          
          $scope.gridOptions.virtualizationThreshold = 50;

          loadGridValues();
       }
       
       init();
}]);