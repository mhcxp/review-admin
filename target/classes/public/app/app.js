'use strict';
var app = angular.module('app', [
  'ngGrid',
  'ui.router',
  'ui.bootstrap',
  'app.services',
  'app.directives',
  'app.controllers'
]);

app.run(['$rootScope', '$state', '$stateParams',function ($rootScope,   $state,   $stateParams) {
	$rootScope.$state = $state;
	$rootScope.$stateParams = $stateParams;
	$rootScope.menus = [
	           {"id":"index"},
	           {"id":"review","children":[{"id":"issue"}]},
	           {'id':"task","children":[{"id":"week"}]},
	           {"id":"project"},
	           {"id":"sysconfig"}
	           ];
	$rootScope.activeMenu = 'index';
	
	}
]);

