app.controller('userPlanAdminController', function($scope,$http,growl,utilsService) {
        $scope.gridOptions = {};
        
        function loadGridValues(){
            utilsService.post({
                url: "../../../UserPlanAdminController", 
                method: "POST"
            }, function (data) {
                $scope.gridOptions.data = data;
            });
        }
        
        $scope.editClick=function (id){
            utilsService.post({
                url: "../../../EditUserPlanAdminController", 
                method: "GET",
                params: {Id:id}
            }, function (data) {
                $scope.userplan = data;
            });
       }
       
        $scope.submit=function (){
            utilsService.post({
                url: "../../../EditUserPlanAdminController", 
                method: "POST",
                data: $scope.userplan
            }, function (data) {
                growl.success('Saved.',{title: 'Success!'});
                $('#myModal').modal('hide');
                init();
            });
       }
       
        function init(){         
          $scope.gridOptions.columnDefs = [
                { field: 'id', visible: true, displayName: 'Id' },
                { field: 'user.email', displayName: 'User' },
                { field: 'plan.desc', displayName: 'Plan' },
                { field: 'fechaVenc', displayName: 'Date End' },
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
