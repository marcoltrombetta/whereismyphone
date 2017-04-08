app.controller('userProfileController', function($scope,$http,growl) {
        
        $scope.userpassword={};
        
        $scope.loadProfile=function(){
            $http({
               url: "../../../User/Profile", 
               method: "POST"
              }).success(function(data) {
                    $scope.userProfile=data;
                });
        }        
     
       $scope.submit=function (){
           $http({
                url: "../../../ChangePasswordController", 
                method: "POST",
                data: $scope.userpassword,
                headers: {
                    'Content-Type': 'application/json'
                }
            }).success(function(data) {
                if(data[0].status=="ok"){
                    growl.success('Saved.',{title: 'Success!'});
                    $('#myModal').modal('hide');
                 }else{
                    growl.error("Error",{title: 'Error!'});
                 }
            }).error(function(err){
                growl.error(err,{title: 'Error!'});
            });
       }
     
        function init(){
            $scope.loadProfile();
           
        }
        
        init();
});
