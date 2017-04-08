app.controller('phonenamesAdminController', ['$scope', '$http','$state','growl','utilsService',function($scope,$http,$state,growl,utilsService) {
       $scope.gridOptions = {};
       
        function loadGridValues(){
            utilsService.post({
                url: "../../../PhoneNames/List", 
                method: "POST"
            }, function (data) {
                $scope.gridOptions.data = data;
            });
        }

       $scope.editClick=function (id){
            utilsService.post({
                 url: "../../../PhoneNames/Edit", 
                method: "GET",
                params: {Id:id}
            }, function (data) {
                $scope.phonenames = data;
            });
           
       }
       
        $scope.submit=function (){
            utilsService.post({
                url: "../../../PhoneNames/Edit", 
                method: "POST",
                data: $scope.phonenames
            }, function (data) {
                growl.success('Saved.',{title: 'Success!'});
                init();
                $('#myModal').modal('hide');
               // $scope.gridOptions.data = data;
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