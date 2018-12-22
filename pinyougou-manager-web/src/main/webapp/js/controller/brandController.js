app.controller('brandController',function ($scope,$controller,brandService) {
    //继承baseController  共用$scope  不是真正意义上的继承
    $controller('baseController',{$scope:$scope});
    //查询所有
    $scope.findAll = function () {
        brandService.findAll().success(function (response) {
            $scope.list = response;
        });
    };
    //分页
    $scope.findPage = function (page,size) {
        brandService.findPage(page,size).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;
            })
    };
    //查找一个
    $scope.findOne = function (id) {
        brandService.findOne(id).success(function (brand) {
            $scope.brand = brand;
        })

    };
    //保存
    $scope.save = function () {
        var object = null;
        if ($scope.brand.id != null){
            object = brandService.update($scope.brand);
        }else{
            object = brandService.add($scope.brand);
        }
        object.success(
            function (response) {
                if (response.success){
                    $scope.reloadList();
                }else {
                    alert(response.message);
                }
            }
        )
    };
    //删除
    $scope.delete = function () {
        if (confirm('确定要删除吗？')) {
            brandService.delete($scope.selectIds).success(function (response) {
                if (response.success) {
                    $scope.reloadList();
                } else {
                    alert(response.message);
                }
            })
        }
    };
    //存储条件查询的条件
    $scope.searchBrand = {};
    //条件查询
    $scope.search = function (page,size) {
        brandService.search(page,size,$scope.searchBrand).success(function (response) {
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;
        })
    }

});