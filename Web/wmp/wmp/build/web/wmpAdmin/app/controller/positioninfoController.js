app.controller("positioninfoController", [ "$scope", "$http","$interval", "growl", function($scope, $http,$interval,growl) {
            $scope.selectId=0;
            
            var addressPointsToMarkers = function(points) {
              return points.map(function(ap) {
                return {
                  //layer: 'realworld',
                    lat: ap.latitude,
                    lng: ap.longitude,
                    message: ap.latitude + ', ' + ap.longitude,
                    label: {
                        message: ap.phoneName.desc,
                        options: {
                                noHide: true
                        }
                    }      
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
            
            $scope.setColor=function (pathname,color){
                $scope.paths[pathName].color=color;
            }
                         
            function loadPathsHist(id){
                var result;
                $scope.data={};
                //$scope.polyline={};
                $scope.polyline.latlngs=[]; 
                
                $http({
                    url: "../../../PositionInfoHistoryController", 
                    method: "POST",
                    data:{Id:id}
                }).success(function(data) {
                    result = data;
                     for(var i=0;i<result.length;i++){
                        var coord = {
                            lat: result[i].latitude,
                            lng: result[i].longitude
                        };
                        $scope.polyline.latlngs.push(coord);
                    }
                });                
                
                $scope.polyline.color= $scope.colors[$scope.selectId];
                $scope.polyline.weight=4;
                
                var pathName=$scope.selectId;
                $scope.paths[pathName]=$scope.polyline;
              
            };
            
            $scope.loadPathsRadius=function (data){
                for(var i=0;i<data.length;i++){
                    if(data[i].id==$scope.selectId){
                        var circle={};
                        circle.latlngs=[data[i].latitude, data[i].longitude];
                        circle.type= 'circle';
                        circle.radius=data[i].accuracy;
                        circle.color=$scope.colors[data[i].id];
                        circle.fillColor= '#ff69b4';
                        circle.heading= 240;

                        var pathName='c'+data[i].id;
                        $scope.paths[pathName]=circle;   
                    }
                }
              /*  $scope.paths.push( {
                            polyline: {
                                    latlngs: [346546546, 66.6546546],
                                    color: '#FF0000',
                                    opacity: 0.5,
                                    stroke: true,
                                    fillColor: '#ff69b4',
                                    fillOpacity: 0.5,
                                    weight: 2,
                                    clickable: true,
                                    heading: 240
                            } ,
            
                
                            circle: {
                                    color: '#FF0000',
                                    opacity: 0.5,
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
                    });
                */
                }


            $scope.deletePoint=function(id, Phoi){
                $http({
                url: "../../../PositionInfo/DeletePoint", 
                method: "POST",
                data: {Id:id, Phoi:Phoi}
                }).success(function(data) {
                    growl.success('Deleted.',{title: 'Success!'});
                    loadTimeLine($scope.selectId);
                }).error(function(err){
                    growl.error(err,{title: 'Error!'});
                });
            }
            
            function loadTimeLine(id){
                $http({
                    url: "../../../PositionInfoHistoryController", 
                    method: "POST",
                    data:{Id:id}
                }).success(function(data) {
                    $scope.timeline=data;
                });    
                
                
            }

            function init(){
                $scope.paths={};
                $scope.markers={};
                $scope.gridOptions = {};
                $scope.polyline={};

                $scope.colors=[];

                $scope.gridOptions.columnDefs = [
                    { field: 'id', visible: true, displayName: 'Id' },
                    { field: 'phoneName.desc', displayName: 'Name' },
                    { field: 'phoneInfo.modelo', displayName: 'Model' },
                    { field: 'date', displayName: 'Date' },
                    { field: 'latitude', displayName: 'Latitude' },
                    { field: 'longitude', displayName: 'Longitude' },
                    { field: 'accuracy', displayName: 'Accuracy' },
                    { field: 'Edit' ,cellTemplate:'<div><a class="btn btn-sm btn-success btn-block" ng-click="grid.appScope.updateHistory(row.entity.phoneInfo.id)">History Trace</a></div>'},
                    { field: 'Color' , cellTemplate:'<div> <input colorpicker type="text" ng-model="grid.appScope.colors[row.entity.phoneInfo.id]"/></div>'}
                ];
      
                $scope.gridOptions.virtualizationThreshold = 50;
      
                updateMarkers();
                $interval(updateMarkers, 30000);
                                
            }
            
            function updateMarkers() {
                $http({
                    url: "../../../PositionInfo/List", 
                    method: "POST",
                }).success(function(data) {
                    $scope.gridOptions.data = data;
                    $scope.markers = addressPointsToMarkers(data);
                    $scope.loadPathsRadius(data);
                    
                     if($scope.selectId!=0){
                        loadPathsHist($scope.selectId);
                    }
                });
                
            }
            	
            $scope.updateHistory=function(id) {
                $scope.selectId=id;
                $scope.paths=[];
                loadPathsHist(id);
                loadTimeLine(id);
            }
                            
            init();
            
     }]);