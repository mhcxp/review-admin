app.factory("reviewService",function($http,$q){
	var reviewService = {};
	reviewService._pool = {};//review的对象池
	reviewService.page = {};
	reviewService._put = function(pid,data){
		var instance = this._pool[pid];
		if(!instance){
			instance = {};
		}
		angular.extend(instance,data);
		this._pool[pid] = instance;
	};
	reviewService.get = function(pid){
		return reviewService._pool[pid];
	};
	reviewService.getPage = function(){
		var content = [];
		for(var pid in reviewService._pool){
			content.push(reviewService._pool[pid]);
		}
		reviewService.page.content = content;
		return reviewService.page;
	};
	
	reviewService.paging = function(index){
		var deferred = $q.defer();
		var scope = this;
		$http.get("/review/page/"+index)
			.success(function(msg){
//				console.log(msg);
				if(msg.success){
					scope.page = msg.data;
					msg.data.content.forEach(function(data){
						scope._put(data.pid,data);
					});
				}
				deferred.resolve(msg);
			}).error(function(){
				deferred.reject();
			});
		return deferred.promise;
	}//end paging
	
	reviewService.closeReview = function(pid){
		var instance = reviewService.get(pid);
		var deferred = $q.defer();
		var scope = this;
		$http.post("/review/close/"+pid)
			.success(function(msg){
				console.log(msg);
				if(msg.success){
					instance.close = true;
					reviewService._put(pid,instance);
				}
				deferred.resolve(msg);
			})
			.error(function(){
				deferred.reject();
			});
		return deferred.promise;
	};//end closeReview
	
	reviewService.activeReview = function(pid){
		var instance = reviewService.get(pid);
		var deferred = $q.defer();
		var scope = this;
		$http.post("/review/active/"+pid)
			.success(function(msg){
				console.log(msg);
				if(msg.success){
					instance.close = false;
					reviewService._put(pid,instance);
				}
				deferred.resolve(msg);
			})
			.error(function(){
				deferred.reject();
			});
		return deferred.promise;
	};
	reviewService.chart = function(){
		var deferred = $q.defer();
		var scope = this;
		$http.get("/review/chart/")
			.success(function(msg){
				deferred.resolve(msg);
			})
			.error(function(){
				deferred.reject();
			});
		return deferred.promise;
	};//end close
	
	
	return reviewService;
});


app.factory("issueService",function($http,$q){
	var issueService = {};
	issueService._pool = {};//review的对象池
	issueService.page = {};
	issueService._put = function(pid,data){
		var instance = this._pool[pid];
		if(!instance){
			instance = {};
		}
		angular.extend(instance,data);
		this._pool[pid] = instance;
	};
	issueService.get = function(pid){
		return issueService._pool[pid];
	};
	issueService.getPage = function(){
		var content = [];
		for(var pid in issueService._pool){
			content.push(issueService._pool[pid]);
		}
		issueService.page.content = content;
		return issueService.page;
	};
	
	issueService.paging = function(index){
		var deferred = $q.defer();
		var scope = this;
		$http.get("/issue/page/"+index)
			.success(function(msg){
				if(msg.success){
					scope.page = msg.data;
					msg.data.content.forEach(function(data){
						scope._put(data.pid,data);
					});
				}
				deferred.resolve(msg);
			}).error(function(){
				deferred.reject();
			});
		return deferred.promise;
	}//end paging
	
	issueService.closeIssue = function(pid){
		var instance = issueService.get(pid);
		var deferred = $q.defer();
		var scope = this;
		$http.post("/issue/close/"+pid)
			.success(function(msg){
				if(msg.success){
					instance.close = true;
					issueService._put(pid,instance);
				}
				deferred.resolve(msg);
			})
			.error(function(){
				deferred.reject();
			});
		return deferred.promise;
	};//end close
	
	
	return issueService;
});