app.controller("searchController",function ($scope,searchService) {
        //定义搜索对象的结构
    $scope.searchMap={'keywords':'','category':'','brand':'','spec':{}};

    //搜索
    $scope.search=function () {
        searchService.search($scope.searchMap).success(
            function (response) {
                $scope.resultMap=response;//搜索返回的结果

        });
    };

    $scope.addSearchItem=function (key,value) {
        if(key =='category' || key =='brand'){//如果用户点的书分类或者品牌
            $scope.searchMap[key]=value;

        }else {//否则是规ge
            $scope.searchMap.spec[key]= value;

        }
        $scope.search();//查询
    }
    $scope.removeSearchItem=function (key) {
        if(key =='category' || key =='brand'){//如果用户点的书分类或者品牌
            $scope.searchMap[key]='';

        }else {//否则是规ge
           delete $scope.searchMap.spec[key];

        }
        $scope.search();//查询
    }


});