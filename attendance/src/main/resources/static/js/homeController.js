var app = angular.module('mainApp', ['ngRoute']);

app.controller('homeController',function($scope,$http,$timeout){

	 
	
	   $scope.information = function (){	
		   $http.get('/vinfo')
		     .success(function (response) {
		         $scope.vinfo = response;
		        
		       
		     });
			 
				
			 $http.get('/list')
		     .success(function (response) {
		         $scope.list = response;
		       
		       
		     });
	   }; 
	   $scope.information();

    
    $scope.home = function (){

		 $http.get('/checkid')
	     .success(function (response) {
	         $scope.ifid = response;
	         if($scope.ifid.hasID == 1){
	        	 
	        	 $scope.information();
	         }
	       
	         else if($scope.ifid.hasID == 0){
           	
             	  $timeout( function(){ $scope.home(); }, 3000);
             	 
             }
	        	 
	        
	     });

		
   };
    
    
 

});

