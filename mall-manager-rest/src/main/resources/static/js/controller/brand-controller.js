app.controller("brandController", function ($scope, $http, $controller, brandService) {
    $controller('baseController', {$scope: $scope});
    $scope.findList = function (page, size) {
        if (page === undefined || page < 1) {
            page = 1;
        }
        if (size === undefined || size < 0) {
            size = 10;
        }
        brandService.findList(page, size)
            .then(function (resp) {
                $scope.pageInfo = resp.data;
                $scope.paginationConf.totalItems = $scope.pageInfo.totalElements;
            }).catch(function () {
            alert("获取品牌列表失败");
        });
    };

    $scope.refreshBrandList = function () {
        $scope.paginationConf.currentPage = 1;
    };
    $scope.bindSaveBrandData = function (brand) {
        $scope.brand = brand;
    };
    $scope.saveBrand = function () {
        if ($scope.brand === undefined) {
            alert("数据不完整");
            return;
        }
        brandService.save($scope.brand).then(function (resp) {
            if (resp.data.success) {
                alert("保存品牌成功");
                $scope.refreshBrandList();
            } else {
                alert("保存品牌失败");
                console.log(resp.data.message);
            }

        }).catch(function (reason) {
            alert("保存品牌失败");
        });
    };
    /* $scope.updateSelected = function (event, id) {
         if (event.target.checked) {
             $scope.selectedIds.push(id);
         } else {
             var index = $scope.selectedIds.indexOf(id);
             $scope.selectedIds.splice(index, 1);
         }
         console.log($scope.selectedIds);
     };*/
    $scope.deleteBrand = function () {
        $scope.selectedIds = [];
        $(".chkItem").each(function () {
            if ($(this).prop("checked")) {
                var id = $(this).attr("objId");
                $scope.selectedIds.push(id);
            }
        });
        if (confirm("确定删除?") && $scope.selectedIds.length > 0) {
            brandService.delete($scope.selectedIds)
                .then(function (resp) {
                    if (resp.data.success) {
                        alert("删除成功");
                        $scope.refreshBrandList();
                    } else {
                        console.log(resp);
                    }
                }).catch(function () {
                console.log('error');
            })
        }
    };
});

