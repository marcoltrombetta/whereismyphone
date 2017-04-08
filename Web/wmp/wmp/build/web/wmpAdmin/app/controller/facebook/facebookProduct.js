App.controller('facebookProduct', ['facebookService','$scope', '$filter', '$http', '$state', '$stateParams', '$rootScope', '$modal', 'newsletterStagesService', 'smsStagesService', function (facebookService,$scope, $filter, $http, $state, $stateParams, $rootScope, $modal, newsletterStagesService, smsStagesService) {
    $scope.myVendorId = $rootScope.GlobalVendorDetail.Id;
    $scope.ProductsChosenList = [];
    $scope.ProductsChosenListInput = [];
    $scope.CouponsChosenListInput = [];
    $scope.RaffleChosenListInput = [];

    //facebook
    facebookService.fbAsyncInit();

    $scope.writePageFeed = function (id, token, message) {
        var promise = facebookService.writeFeed(id,message, token);
        promise.then(function (result) {
            // $scope.pageFeeddata = result;
        });
    }

    $scope.writePageFeedPhoto = function (id, token, photourl, message) {
        var promise = facebookService.writeFeedPhoto(id, message, photourl, token);
        promise.then(function (result) {
            // $scope.pageFeeddata = result;
        });
    }

    $scope.openProducts = function (entityTypeId) {
        if (($rootScope.GlobalVendorDetail.IsParent == true || $rootScope.GlobalVendorDetail.IsParent == 1) && newsletterStagesService.stages.VendorId == $rootScope.GlobalVendorDetail.ParentId)
            $scope.chooseType = 7;//parent website and sentParentUtc
        else
            $scope.chooseType = 8;      
     
        $scope.entityTypeId = entityTypeId;//1: product

        var url;

        if ($scope.entityTypeId == 2) {
            $scope.ProductsChosenList = $scope.CouponsChosenListInput;
            url = 'app/views/productCoupon/gridProductCouponSelect.html'; //coupons or raffles

            var modalInstanceCoupon = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: url,
                size: 'lg',
                scope: $scope
            });

            modalInstanceCoupon.result.then(function (selectedItem) {
                $scope.CouponsChosenListInput =selectedItem;
            });

        } else if ($scope.entityTypeId == 3) {
            $scope.ProductsChosenList = $scope.RafflesChosenListInput;
            url = 'app/views/productCoupon/gridProductCouponSelect.html';

            var modalInstanceRaffle = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: url,
                size: 'lg',
                scope: $scope
            });

            modalInstanceRaffle.result.then(function (selectedItem) {
                $scope.RafflesChosenListInput = selectedItem;
            });
        } else if ($scope.entityTypeId == 1) {
            $scope.ProductsChosenList = $scope.ProductsChosenListInput;
            url = 'app/views/product/gridProductSelect.html'; //product

            var modalInstanceProduct = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: url,
                size: 'lg',
                scope: $scope
            });

            modalInstanceProduct.result.then(function (selectedItem) {
                $scope.ProductsChosenListInput = selectedItem;
            });
        }

    };

    $scope.post = function () {
        var pageid='170692820003020';
        var token = 'EAAPYWey2HbgBAKrWqfpVvuywC2pNsTPyAvaKURcDvsYrF8sdtbAQdH9dasFZB2GBOoW1Aw9s5JPmmrGyAHhliKm6nsXYGEqNH2vfWudDiNUU8cgqDOJfBrZBHLNd24BluNlzJYjpD7aZBZBZAWfYCGs83vPUOPGJXXNUwCN14Ivt4PTDEneJY';

        var promise = facebookService.getMyPagesId();
        promise.then(function (result) {
            $scope.mypagesid = result.data;
        });

        if ($scope.ProductsChosenListInput != undefined) {
            postProducts($scope.ProductsChosenListInput, token, pageid);
        }

        if ($scope.RafflesChosenListInput != undefined) {
            postRaffle($scope.RafflesChosenListInput, token, pageid);
        }

        if ($scope.CouponsChosenListInput != undefined) {
            postCoupons($scope.CouponsChosenListInput, token, pageid);
        }

    }

    function postProducts(arr,token,pageid) {
        for (var i = 0; i < arr.length ; i++) {
            $http({
                url: '/ProductAdmin/GetProductById',
                method: "POST",
                data: JSON.stringify({ Id: arr[i].Id }),
                headers: { 'Content-Type': 'application/json' }
            }).success(function (data) {
                var message = data.name + '\n' + data.fullDescription;

                var photo = data.ProductPictures[0];

                if (photo != null && photo != undefined && photo.Picture != undefined) {
                    $scope.writePageFeedPhoto(pageid,token , photo.Picture.BaseUrl, message);
                } else {
                    $scope.writePageFeed(pageid, token, message);
                }
            }).
            error(function (data, status, headers, config) {
                var O = 0;
            });
        }
    }

    function postRaffle(arr, token, pageid) {
        for (var i = 0; i < arr.length ; i++) {
            $http({
                url: '/ProductAdmin/GetProductById',
                method: "POST",
                data: JSON.stringify({ Id: arr[i].Id }),
                headers: { 'Content-Type': 'application/json' }
            }).success(function (data) {
                var message = data.name + '\n' + data.fullDescription;

                var photo = data.ProductPictures[0];

                if (photo != null && photo != undefined && photo.Picture != undefined) {
                    $scope.writePageFeedPhoto(pageid, token, photo.Picture.BaseUrl, message);
                } else {
                    $scope.writePageFeed(pageid, token, message);
                }
            }).
            error(function (data, status, headers, config) {
                var O = 0;
            });
        }
    }

    function postCoupons(arr, token, pageid) {
        for (var i = 0; i < arr.length ; i++) {
            $http({
                url: '/ProductAdmin/GetProductById',
                method: "POST",
                data: JSON.stringify({ Id: arr[i].Id }),
                headers: { 'Content-Type': 'application/json' }
            }).success(function (data) {
                var message = data.name + '\n' + data.fullDescription;

                var photo = data.ProductPictures[0];

                if (photo != null && photo != undefined && photo.Picture != undefined) {
                    $scope.writePageFeedPhoto(pageid, token, photo.Picture.BaseUrl, message);
                } else {
                    $scope.writePageFeed(pageid, token, message);
                }

            }).
            error(function (data, status, headers, config) {
                var O = 0;
            });
        }
    }

}]);