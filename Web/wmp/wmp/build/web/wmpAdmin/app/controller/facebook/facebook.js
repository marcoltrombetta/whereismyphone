//https://developers.facebook.com/docs/graph-api/reference/v2.7/page/feed
//https://developers.facebook.com/docs/reference/javascript/FB.Canvas.getPageInfo

app.service('facebookService', ['$q','$window', function ($q,$window) {
    return {
        fbAsyncInit: function () { //call first
            FB.init({
                appId: '1082305665179064',
                status: true,
                cookie: true,
                xfbml: true,
                version: 'v2.7'
            });
            FB.login(function () { }, { perms: 'manage_pages,user_likes,publish_actions,publish_pages' });
        },
        postOnProfile: function () {
            FB.api('/me/feed', 'post', { message: $scope.message });
        },
        getMyPagesId: function () {
            var deferred = $q.defer();
            FB.api('/me/accounts', {
                fields: 'page_id,name,access_token'
            }, function (response) {
                if (!response || response.error) {
                    deferred.reject('Error occured');
                } else {
                    deferred.resolve(response);
                }
            });
            return deferred.promise;
        },
        readFeed: function (pageid) {
            var deferred = $q.defer();
            FB.api(
                "/" + pageid + "/feed",
                function (response) {
                    if (response && !response.error) {
                        deferred.resolve(response);
                    }
                }
            );
            return deferred.promise;
        },
        writeFeed: function (pageid, message, access_token_data) {
            var deferred = $q.defer();
            FB.api(
                "/" + pageid + "/feed",
                "POST",
                {
                    access_token: access_token_data,
                    message: message
                },
                function (response) {
                    if (response && !response.error) {
                        deferred.resolve(response);
                    }
                }
            );
            return deferred.promise;
        },
        logout: function () {
            FB.logout(function (response) { });
        },
        writeFeedPhoto: function (pageid, message, photourl, access_token_data) {
            var deferred = $q.defer();
            FB.api('/'+pageid+'/photos', 'post', {
                access_token: access_token_data,
                message: message,
                url: photourl,
            }, function (response) {
               
            });
            return deferred.promise;
        }
    }
}]);

app.controller('facebookAdminController', ['$q','facebookService', '$window', '$scope', '$http', '$state', '$stateParams', '$rootScope', function ($q,facebookService, $window, $scope, $http, $state, $stateParams, $rootScope) {
   // facebookService.logout();
    facebookService.fbAsyncInit();
   
    $scope.getMyPages = function () {
        var promise=facebookService.getMyPagesId();
        promise.then(function (result) {
            $scope.mypagesid = result.data;
        });
    }

    $scope.post = function () {
        facebookService.postOnProfile($scope.message);
    }

    $scope.readPageFeed = function (id) {
        $scope.getMyPages();

        var promise = facebookService.readFeed(id);
        promise.then(function (result) {
            $scope.pageFeeddata = result;
        });
    }

    $scope.writePageFeed = function (id) {
        var promise = facebookService.writeFeed(id, $scope.message, 'EAAPYWey2HbgBAOuUOUX6j9xOlmI3B4T850Ia0OFraGehZB6Egkxh8raTfDKObKQhTlgVPVPBNxZCCJ9TFV32q19YNc6dVTZCgflEQsC1ZCOr2AH1l9bYcVj5Y8NWSigP42UZAPlSq11bQIKu0f7Ifd6VZCUfQOd3S82drc7gaCuQZDZD');
        promise.then(function (result) {
           // $scope.pageFeeddata = result;
        });
    }
    
}]);