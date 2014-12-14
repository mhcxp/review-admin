'use strict';

var directives = angular.module('app.directives', []);

//顶部菜单栏
directives.directive('headmenu',function(){
	return {
		restrict : 'EA',
		require:'ngModel',//依赖于ngmodel，从而获取当前要显示的菜单项内容是什么
		scope:{
			ngModel:'=',
			name:'admin'
		},
		template:'<div class="headmenu pull-right"><a></a></div>',
		replace : true,
		link:function(scope,elme,attr,ctrl){
			//console.log(scope.ngModel);
		}
	}
});
