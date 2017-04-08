app.controller('userValidationAdminController', function($scope,$http) {
        $scope.gridOptions = {};
        
        function loadGridValues(){
            $http({
                url: "../../../UserValidationAdminController", 
                method: "POST"
            }).success(function(data) {
                $scope.gridOptions.data = data;
            });
        }
        
        function init(){         
          $scope.gridOptions.columnDefs = [
              { field: 'id', visible: true, displayName: 'Id' },
              { field: 'phoneInfo.user.email', displayName: 'User' },
              { field: 'number', displayName: 'Number' },
              { field: 'validated', displayName: 'Valid' },
              { field: 'Delete' , cellTemplate:'<div><a class="btn btn-sm btn-success btn-block" ng-click="grid.appScope.deleteClick(row.entity.id)">Delete</a></div>'}
          ];
          
          $scope.gridOptions.virtualizationThreshold = 50;

          loadGridValues(); 

       }
       
       init();
});
