'use strict';

angular.module('app.controllers', [])
  .controller('IndexCtrl', ['$rootScope','$scope', function($rootScope,$scope) {
	  
	  //当前用户是否登陆
	  $scope.notlogin = true;
	  $scope.usernotlogin = function(){
		  return $scope.notlogin;
	  }
	  
	  $rootScope.menuActive = function(id){
		  return id===$rootScope.activeMenu;
	  };
	  

  }])
  .controller('LeftCtrl', ['$scope', function($scope) {

  }])
  .controller('RightCtrl', ['$scope', function($scope) {

  }]);
