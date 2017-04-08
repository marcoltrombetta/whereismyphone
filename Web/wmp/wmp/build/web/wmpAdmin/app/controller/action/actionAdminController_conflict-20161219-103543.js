app.controller('actionAdminController', function($scope,$http,growl, utilsService) {
        $scope.gridOptions = {};
        $scope.action={};
             
        function loadGridValues(){
            utilsService.post({
                url: "../../../Action/List", 
                method: "POST"
            }, function (data) {
                $scope.gridOptions.data = data;
            });
        }
        
        $scope.submit=function (){
            utilsService.post({
                url: "../../../Action/Edit", 
                method: "POST",
                data: $scope.action
            }, function (data) {
                growl.success('Saved.',{title: 'Success!'});
                $('#myModal').modal('hide');
                init();
            });
          
       }
        
        $scope.editClick=function (id){
            utilsService.post({
                url: "../../../Action/Edit", 
                method: "GET",
                params: {Id:id}
            }, function (data) {
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
