'use strict';

app.config(['$stateProvider','$urlRouterProvider',function($stateProvider,$urlRouterProvider){
	
	$urlRouterProvider.otherwise("/review");
	
	$stateProvider
	.state("index",{
		url:'/index',
		views:{
			'':{
				templateUrl:'/public/app/home.html'
			},
			'main@index':{
				templateUrl:'/public/app/review/review.index.html'
			}
		}
	})
	.state("review",{
		url:'/review',
		views:{
			'':{
				templateUrl:'/public/app/home.html'
			},
			'main@review':{
				templateUrl:'/public/app/review/review.index.html'
			}
		}
	})
	.state("issue",{
		url:'/issue',
		views:{
			'':{
				templateUrl:'/public/app/home.html'
			},
			'main@issue':{
				templateUrl:'/public/app/review/issues.html'
			}
		}
	})
	.state("task",{
		url:'/task',
		views:{
			'':{
				templateUrl:'/public/app/home.html'
			},
			'main@task':{
				templateUrl:'/public/app/task/task.index.html'
			}
		}
	})
	;
	
	
}]);

/*
app.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/review/index', {
        templateUrl: '/public/app/review/review.index.html', 
        controller: 'ReviewIndexCtrl'
    });
  $routeProvider.when('/view2', {
        templateUrl: 'partials/partial2.html', 
        controller: 'MyCtrl2'
    });
  $routeProvider.otherwise({
        redirectTo: '/review/index'
    });
}]);
*/
