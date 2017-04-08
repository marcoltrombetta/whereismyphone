app.controller('gpxAdminController', function($scope,$http,growl) {  
     $scope.filter={};
    $http.get("../../../PhonesSelectController").success(function(data) {
       $scope.filter.phones = data;
   });
});
