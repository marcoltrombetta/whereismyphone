app.controller("positionHistoryController", [ "$scope", "$http","$interval","uiGridConstants","utilsService", function($scope, $http,$interval,uiGridConstants,utilsService) {

            var addressPointsToMarkers = function(points) {
              return points.map(function(ap) {
                return {
                  //layer: 'realworld',
                    lat: ap.latitude,
                    lng: ap.longitude,
                    label: {
                        message: ap.phoneName.desc,
                        options: {
                                noHide: true
                        }
                    }      
                };
              });
            };
            
            var addPaths = function(points) {
              return points.map(function(ap) {
                return {                       
                    color: '#FF0000',
                    opacity: 0.01,
                    stroke: false,
                    fillColor: '#ff69b4',
                    fillOpacity: 0.5,
                    weight: 8,
                    radius: ap.accuracy,
                    latlngs: [ap.latitude,ap.longitude],
                    type: 'circle',
                    clickable: true,
                    heading: 240       
                };
              });
            };
            
       
            angular.extend($scope, {
                center: {
                    lat: -37.9212959167,
                    lng: 175.5604435167,
                    zoom: 8
                },
                events: {
                    map: {
                        enable: ['moveend', 'popupopen'],
                        logic: 'emit'
                    },
                    marker: {
                        enable: [],
                        logic: 'emit',
                        label: {
                            message: "Hey, drag me if you want",
                            options: {
                                    noHide: true
                            }
                    }                    
                    }
                },
                layers: {
                    baselayers: {
                        osm: {
                            name: 'OpenStreetMap',
                            type: 'xyz',
                            url: 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
                        }
                    },
                    overlays: {
                        realworld: {
                            name: "Real world data",
                          //  type: "markercluster",
                            visible: true
                        }
                    }
                }
            });
             
            $scope.loadPaths=function (markerid){
				
                $scope.paths = {
                    /*	area: {
                                    color: '#FF0000',
                                    opacity: 0.5,
                                    stroke: false,
                                    fillColor: '#ff69b4',
                                    fillOpacity: 0.5,
                                    weight: 0,
                                    latlngs: [data2],
                                    type: 'polygon',
                                    clickable: true,
                                    heading: 240
                            },*/
                            polyline: {
                                    latlngs: $scope.data[markerId][3],
                                    color: '#FF0000',
                                    opacity: 0.5,
                                    stroke: true,
                                    fillColor: '#ff69b4',
                                    fillOpacity: 0.5,
                                    weight: 2,
                                    clickable: true,
                                    heading: 240
                            } 

                           , circle: {
                                    color: '#FF0000',
                                    opacity: 0.1,
                                    stroke: false,
                                    fillColor: '#ff69b4',
                                    fillOpacity: 0.5,
                                    weight: 8,
                                    radius: 60,
                                    latlngs: $scope.data[markerId][4],
                                    type: 'circle',
                                    clickable: true,
                                    heading: 240
                            } 
                    };

                }

            function init(){
                $scope.paths={};
                $scope.markers={};
                
                $scope.gridOptions = {};
                $scope.polyline={};
                $scope.filter={};
                $scope.filter.dateFrom='';
                $scope.filter.dateTo='';
                          
                $scope.gridOptions.enablePaging= true;
                $scope.gridOptions.showFooter= true;
                $scope.gridOptions.enablePaginationControls= false;
                $scope.gridOptions.paginationPageSizes= [25, 50, 75];
                $scope.gridOptions.virtualizationThreshold = 50;
                $scope.gridOptions.minRowsToShow=25;
                
                $scope.gridOptions.columnDefs = [
                    { field: 'id', visible: true, displayName: 'Id' },
                    { field: 'phoneName.desc', displayName: 'Name' },
                    { field: 'phoneInfo.modelo', displayName: 'Model' },
                    { field: 'date', displayName: 'Date' },
                    { field: 'latitude', displayName: 'Latitude' },
                    { field: 'longitude', displayName: 'Longitude' },
                    { field: 'accuracy', displayName: 'Accuracy' }
                ];
            
                $scope.gridOptions.onRegisterApi = function (gridApi) {
                    $scope.gridApi = gridApi;
                }                               
                
                updateMarkers();
                
                $http.get("../../../PhonesSelectController").success(function(data) {
                    $scope.filter.phones = data;
                });
            
            }

            function updateMarkers() {
            	$http.post("../../../PositionHistoryController").success(function(data) {
                    $scope.gridOptions.data = data;
                    $scope.markers = addressPointsToMarkers(data);
                    //$scope.paths=addPaths(data);
                });
            }
              
            $scope.filterClick=function (){
                $scope.polyline.latlngs=[];
                
                 utilsService.post({
                    url: "../../../PositionHistoryController", 
                    method: "POST",
                    data: $scope.filter,
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }, function (data) {
                    $scope.gridOptions.data = data;
                    
                    //show markers
                    if($scope.filter.show=='0'){
                        $scope.polyline={};
                        $scope.markers = addressPointsToMarkers(data);
                    }
                   // $scope.paths=addPaths(data);
                    if($scope.filter.show=='1'){
                        $scope.markers={};
                        //show polyline
                        for(var i=0;i<data.length;i++){
                            var coord = {
                                lat: data[i].latitude,
                                lng: data[i].longitude
                            };
                            $scope.polyline.latlngs.push(coord);
                            $scope.polyline.color= '#000';
                            $scope.polyline.weight=4;
                        }
                        $scope.paths['polyline']=$scope.polyline;
                    }
                });
                 /*$http({
                    url: "../../../PositionHistoryController", 
                    method: "POST",
                    data: $scope.filter,
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).success(function(data) {
                    $scope.gridOptions.data = data;
                    
                    //show markers
                    if($scope.filter.show=='0'){
                        $scope.polyline={};
                        $scope.markers = addressPointsToMarkers(data);
                    }
                   // $scope.paths=addPaths(data);
                    if($scope.filter.show=='1'){
                        $scope.markers={};
                        //show polyline
                        for(var i=0;i<data.length;i++){
                            var coord = {
                                lat: data[i].latitude,
                                lng: data[i].longitude
                            };
                            $scope.polyline.latlngs.push(coord);
                            $scope.polyline.color= '#000';
                            $scope.polyline.weight=4;
                        }
                        $scope.paths['polyline']=$scope.polyline;
                    }
                });*/
            }
                                
            init();

        }]);
