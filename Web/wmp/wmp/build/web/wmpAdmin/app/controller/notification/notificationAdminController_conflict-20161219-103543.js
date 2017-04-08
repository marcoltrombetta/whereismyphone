app.controller('notificationAdminController', function($scope,$http,utilsService) {
        $scope.gridOptions = {};
        
        function loadGridValues(){
             utilsService.post({
                url: "../../../NotificationAdminController", 
                method: "POST"
            }, function (data) {
                $scope.gridOptions.data = data;
            });
        }
        
        function init(){         
          $scope.gridOptions.columnDefs = [
              { field: 'id', visible: true, displayName: 'Id' },
              { field: 'user.email', displayName: 'User' },
              { field: 'sms', displayName: 'Sms' },
              { field: 'date', displayName: 'Date' },
              { field: 'read', displayName: 'Read' },
              { field: 'Delete' , cellTemplate:'<div><a class="btn btn-sm btn-success btn-block" ng-click="grid.appScope.deleteClick(row.entity.id)">Delete</a></div>'}
          ];
          
          $scope.gridOptions.virtualizationThreshold = 50;

          loadGridValues(); 

       }
       
       init();
});
