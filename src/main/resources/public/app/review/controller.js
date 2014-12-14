app.controller('ReviewCtrl', ['$rootScope','$scope','reviewService', function($rootScope,$scope,reviewService) {
	
	$rootScope.activeMenu = "review";

	$scope.selectionItems = [];
	//表格配置项
	$scope.gridOptions = {
			data:'reviews',
			selectedItems:$scope.selectionItems,
			enablePinning:true,
			multiSelect:false,
			columnDefs:[
			      {field:'id',displayName:'id'},
			      {field:'author',displayName:'创建人'},
			      {field:'creationDate',displayName:'创建时间',cellFilter:'date: "yyyy-MM-dd hh:mm:ss"'},
			      {displayName:'状态',cellTemplate:'<div class="nggrid-cell"><span class="label label-primary" ng-hide="row.getProperty(\'close\')">活动</span>'
			    	  		+'<span class="label label-danger" ng-show="row.getProperty(\'close\')">关闭</span></div>'},
			      {displayName:'操作',cellTemplate:'<div class="nggrid-cell"><div class="btn-group">'
			    	  	+'<button class="btn btn-xs btn-danger" ng-click="closeReview(row.getProperty(\'pid\'))" ng-hide="row.getProperty(\'close\')"><i class="fa fa-edit"></i></button>'
			    	  	+'<button class="btn btn-xs btn-primary" ng-click="activeReview(row.getProperty(\'pid\'))" ng-show="row.getProperty(\'close\')"><i class="fa fa-edit"></i></button>'
			    	  	+'</div></div>'}
			]
	};
	$scope.currentPage = 1;
	$scope.pageChanged = function(){
//		console.log($scope.currentPage);
		paging($scope.currentPage);
	};
	
	var paging = function(index){
		reviewService.paging(index).then(function(msg){
			if(msg.success){
				$scope.reviews = msg.data.content;
				$scope.page = msg.data;
				$scope.currentPage = msg.data.Index;
			}
		},function(err){
			alert("查询数据失败！");
		});
	};
	
	paging(1);
	
	
	$scope.closeReview = function(pid){
		reviewService.closeReview(pid).then(function(msg){
			if(msg.success){
				$scope.page = reviewService.getPage();
				$scope.reviews = $scope.page.content;
//				console.log($scope.page);
			}else{
				alert("关闭review失败！原因是："+msg.msg);
			}
		},function(err){
			alert(err);
		});
	};
	
	$scope.activeReview= function(pid){
		reviewService.activeReview(pid).then(function(msg){
			if(msg.success){
				$scope.page = reviewService.getPage();
				$scope.reviews = $scope.page.content;
//				console.log($scope.page);
			}else{
				alert("开启review失败！原因是："+msg.msg);
			}
		},function(err){
			alert(err);
		});
	};//end activeReview
	
	///chart
	var reivewtable = $("#reviewtable");
	var reviewchart = $("#reviewchart");
	var rt_cont = $("#reviewtable-content");
	var rc_cont = $("#reviewchart-content");
	$scope.activetabstr = "reviewtable";
	
	
	$scope.activetab = function(str){
		$scope.activetabstr = str;
	};
	
	$scope.istableactive = function(){
		if($scope.activetabstr == "reviewtable"){
			return true;
		}
		return false;
	};
	
	$scope.ischartactive = function(){
		if($scope.activetabstr=="reviewchart"){
			return true;
		}
		return false;
	};
	
	reviewService.chart().then(function(msg){
		$scope.chartdata = [];
		if(msg.success){
			var obj = {};
			obj.value = msg.data[0];
			obj.name = "活动";
			var obj2 = {};
			obj2.value = msg.data[1];
			obj2.name = "已关闭";
			$scope.chartdata.push(obj);
			$scope.chartdata.push(obj2);
			console.log($scope.chartdata);
			var option = {
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        orient : 'vertical',
				        x : 'left',
				        data:['活动','已关闭']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {
				                show: true, 
				                type: ['pie', 'funnel'],
				                option: {
				                    funnel: {
				                        x: '25%',
				                        width: '50%',
				                        funnelAlign: 'center',
				                        max: 1548
				                    }
				                }
				            },
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    series : [
				        {
				            name:'review统计',
				            type:'pie',
				            radius : ['50%', '70%'],
				            itemStyle : {
				                normal : {
				                    label : {
				                        show : false
				                    },
				                    labelLine : {
				                        show : false
				                    }
				                },
				                emphasis : {
				                    label : {
				                        show : true,
				                        position : 'center',
				                        textStyle : {
				                            fontSize : '30',
				                            fontWeight : 'bold'
				                        }
				                    }
				                }
				            },
				            data:$scope.chartdata
				        }
				    ]
				};
			myChart.setOption(option);
		}
	},function(err){
		alert(err);
	});
	
	var myChart = echarts.init(document.getElementById('chart'));
	
		                    
   
	
	
  }]);



app.controller('IssueCtrl', ['$rootScope','$scope','issueService', function($rootScope,$scope,issueService) {
	$rootScope.activeMenu = "review";
	$scope.selectionItems = [];
	//表格配置项
	$scope.gridOptions = {
			data:'issues',
			plugins:[new ngGridCsvExportPlugin({containerPanel:'#issue-title'})],
			selectedItems:$scope.selectionItems,
			enablePinning:true,
			multiSelect:false,
			showFooter:false,
			columnDefs:[
			      {field:'id',displayName:'id'},
			      {field:'assignedTo',displayName:'处理人'},
			      {field:'fileName',displayName:'文件名'},
			      {field:'fileLine',displayName:'行号'},
			      {field:'summary',displayName:'标题'},
			      {field:'description',displayName:'描述'},
			      {field:'creationDate',displayName:'创建时间',cellFilter:'date: "yyyy-MM-dd hh:mm:ss"'},
			      {field:'lastModificationDate',displayName:'修改时间',cellFilter:'date: "yyyy-MM-dd hh:mm:ss"'},
			      {displayName:'状态',cellTemplate:'<div class="nggrid-cell"><span class="label label-primary" ng-hide="row.getProperty(\'closed\')">活动</span>'
			    	  		+'<span class="label label-danger" ng-show="row.getProperty(\'closed\')">关闭</span></div>'},
			      {displayName:'操作',cellTemplate:'<div class="nggrid-cell"><div class="btn-group">'
			    	  	+'<button class="btn btn-xs btn-danger" ng-click="closeReview(row.getProperty(\'pid\'))" ng-hide="row.getProperty(\'closed\')"><i class="fa fa-edit"></i></button>'
			    	  //	+'<button class="btn btn-xs btn-primary" ng-click="activeReview(row.getProperty(\'pid\'))" ng-show="row.getProperty(\'close\')"><i class="fa fa-edit"></i></button>'
			    	  	+'</div></div>'}
			]
	};
	$scope.currentPage = 1;
	$scope.pageChanged = function(){
//		console.log($scope.currentPage);
		paging($scope.currentPage);
	};
	
	var paging = function(index){
		issueService.paging(index).then(function(msg){
			console.log(msg);
			if(msg.success){
				$scope.issues = msg.data.content;
				$scope.page = msg.data;
				$scope.currentPage = msg.data.Index;
			}
		},function(err){
			alert("查询数据失败！");
		});
	};
	
	paging(1);
	
	$scope.closeReview = function(pid){
		issueService.close(pid).then(function(msg){
			if(msg.success){
				$scope.page = issueService.getPage();
				$scope.issues = $scope.page.content;
			}else{
				alert("关闭issue失败！原因是："+msg.msg);
			}
		},function(err){
			alert(err);
		});
	};
	
	
}]);

