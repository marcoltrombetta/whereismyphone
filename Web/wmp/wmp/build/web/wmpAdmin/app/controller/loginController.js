app.controller('loginController', ['$scope', '$window','$http','$state', function($scope, $window,$http,$state) {
     $scope.doLogin=function (){
        $http({
             url: "../../../Login", 
             method: "POST",
             data : $scope.login,
             headers: {
                 'Content-Type': 'application/json'
             }
            }).success(function(data) {
                 if(data[0].status=="ok"){
                     $window.location.href="/wmp/wmpAdmin/app/view/index.html#/dashboard"
                 }else{
                     $scope.loginError="Login Fail";
                 }
             });
        };    
}]);


