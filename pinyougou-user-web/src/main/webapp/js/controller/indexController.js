app.controller("indexControlle",function ($scope,loginService) {
    $scope.showName=function () {
        loginService.showName().success(
            function (response) {
                $scope.loginName=response.name;
            }
        )
    }

});