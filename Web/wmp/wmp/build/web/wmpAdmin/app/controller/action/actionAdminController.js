app.controller('actionAdminController', function($scope,$http,growl) {
        $scope.gridOptions = {};
        $scope.action={};
             
        function loadGridValues(){
            $http({
                url: "../../../Action/List", 
                method: "POST"
            }).success(function(data) {
                $scope.gridOptions.data = data;
            });
        }
        
        $scope.submit=function (){
           $http({
                url: "../../../Action/Edit", 
                method: "POST",
                data: $scope.action
            }).success(function(data) {
                growl.success('Saved.',{title: 'Success!'});
                $('#myModal').modal('hide');
                init();
            }).error(function(err){
                growl.error(err,{title: 'Error!'});
            });
       }
        
        $scope.editClick=function (id){
             $http({
                url: "../../../Action/Edit", 
                method: "GET",
                params: {Id:id},
            }).success(function(data) {
                $scope.action = data;
            });
       }
        
        function init(){         
          $scope.gridOptions.columnDefs = [
              { field: 'id', visible: true, displayName: 'Id' },
              { field: 'phoneName.desc', displayName: 'Phone' },
              { field: 'sound', displayName: 'Sound' },
              { field: 'logout', displayName: 'Logout' },
              { field: 'broadcast', displayName: 'Broadcast' },
              { field: 'Action' , cellTemplate:'<div> <button type="button" class="btn btn-sm btn-success btn-block" ng-click="grid.appScope.editClick(row.entity.id)" data-toggle="modal" data-target="#myModal">New</button></div>'},
          ];
          
          $scope.gridOptions.virtualizationThreshold = 50;

          loadGridValues(); 
       }
       
       init();
});
