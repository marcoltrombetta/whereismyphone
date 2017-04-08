	app.service('dataService', function(){
		var data=[];
		
		this.initialize=function(){
				data1=[
                    34.43434343,
                    54.545454545,
                    "aaaa",
					[],
					[]
                ];

				data2=[
                    34.53434343,
                    54.545454545,
                    "aaaa11",
                    [], 
                    []
                ];
              
                data.push(data1);
                data.push(data2);
                  
           return data;  
		}
		
		this.doPost=function(url){		
        	console.log(url);
		    $http.get(url).success(function(data) {
		    	console.log(data);
		    });
		}
	});
