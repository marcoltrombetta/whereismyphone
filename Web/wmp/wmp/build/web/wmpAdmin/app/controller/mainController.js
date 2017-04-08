app.controller('mainController', function($scope,$http) {
        
        $scope.loadMenu=function(){
            $http({
               url: "../../../MenuController", 
               method: "POST"
              }).success(function(data) {
                   $scope.menu=data;
               });
        }
        
         $scope.loadNotification=function(){
            $http({
               url: "../../../NotificationAdminController", 
               method: "POST"
              }).success(function(data) {
                   $scope.notification=data;
               });
        }
                
        function init(){
            $scope.loadMenu();
            $scope.loadNotification();
        }
        
        init();
});
