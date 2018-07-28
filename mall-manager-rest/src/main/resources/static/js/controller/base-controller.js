app.controller("baseController", function ($scope) {
    $scope.reload = true;
    //分页控件配置currentPage:当前页   totalItems :总记录数  itemsPerPage:每页记录数  perPageOptions :分页选项  onChange:当页码变更后自动触发的方法
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function () {
            if (!$scope.reload) {
                return;
            }
            $scope.findList($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
            $scope.reload = false;
            setTimeout(function () {
                $scope.reload = true;
            }, 200);
        }
    };

});