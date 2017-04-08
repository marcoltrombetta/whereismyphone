app.controller('userProfileController', function($scope,$http,growl,utilsService) {
        
        $scope.userpassword={};
        
        $scope.loadProfile=function(){
            utilsService.post({
               url: "../../../User/Profile", 
               method: "POST"
            }, function (data) {
                $scope.userProfile=data;
            });
        }        
     
       $scope.submit=function (){
           utilsService.post({
                url: "../../../ChangePasswordController", 
                method: "POST",
                data: $scope.userpassword,
                headers: {
                    'Content-Type': 'application/json'
                }
            }, function (data) {
                if(data[0].status=="ok"){
                    growl.success('Saved.',{title: 'Success!'});
                    $('#myModal').modal('hide');
                 }else{
                    growl.error("Error",{title: 'Error!'});
                 }
            });
       }
     
        function init(){
            $scope.loadProfile();
           
        }
        
        init();
});
