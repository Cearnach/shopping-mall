app.controller("sellerController", function ($scope, $http, $controller, sellerService) {
    $controller('baseController', {$scope: $scope});
    $scope.findAll = function (page, size) {
        if (page === undefined || page < 1) {
            page = 1;
        }
        if (size === undefined || size < 0) {
            size = 10;
        }
        sellerService.findAll(page, size)
            .then(function (resp) {
                $scope.pageInfo = resp.data;
                $scope.paginationConf.totalItems = $scope.pageInfo.totalElements;
            }).catch(function () {
            alert("获取列表失败");
        });
    };
    $scope.findByStatusCode = function (statusCode, page, size) {
        if (page === undefined || page < 1) {
            page = 1;
        }
        if (size === undefined || size < 0) {
            size = 10;
        }
        sellerService.findByStatusCode(statusCode, page, size)
            .then(function (resp) {
                $scope.pageInfo = resp.data;
                $scope.paginationConf.totalItems = $scope.pageInfo.totalElements;
            })
            .catch(function (reason) {
                alert("获取列表失败");
            });
    };
    $scope.refreshList = function () {
        var currentPage = $scope.paginationConf.currentPage;
        if (currentPage === 1) {
            $scope.paginationConf.currentPage = 0;
        } else {
            $scope.paginationConf.currentPage = 1;
        }
    };
    $scope.bindSaveData = function (entity) {
        $scope.entity = entity;
    };
    $scope.save = function () {
        if ($scope.entity === undefined) {
            alert("数据不完整");
            return;
        }
        sellerService.save($scope.entity).then(function (resp) {
            if (resp.data.success) {
                // alert("保存成功");
                $scope.refreshList();
            } else {
                alert("保存失败");
                console.log(resp.data.message);
            }

        }).catch(function (reason) {
            alert("保存失败");
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
    $scope.delete = function () {
        $scope.selectedIds = [];
        $(".chkItem").each(function () {
            if ($(this).prop("checked")) {
                var id = $(this).attr("objId");
                $scope.selectedIds.push(id);
            }
        });
        if (confirm("确定删除?") && $scope.selectedIds.length > 0) {
            sellerService.delete($scope.selectedIds)
                .then(function (resp) {
                    if (resp.data.success) {
                        // alert("删除成功");
                        $scope.refreshList();
                    } else {
                        alert("删除失败");
                        console.log(resp);
                    }
                }).catch(function () {
                alert("删除失败");
                console.log('error');
            })
        }
    };
    $scope.updateStatusCode = function (sellerId, statusCode) {
        if (sellerId === undefined || sellerId === 0 || statusCode === undefined || statusCode === 0) {
            alert("卖家ID或状态码不能为空");
            return;
        }
        sellerService.updateStatusCode(sellerId, statusCode)
            .then(function (resp) {
                if (resp.data) {
                    $scope.refreshList();
                    alert("操作成功");
                } else {
                    alert("操作失败");
                }
            })
            .catch(function (reason) {
                alert("操作异常");
            });
    };
    $scope.defaultStatusCode = -1;
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
            $scope.findByStatusCode($scope.defaultStatusCode, $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
            $scope.reload = false;
            setTimeout(function () {
                $scope.reload = true;
            }, 200);
        }
    };
    $scope.statusJson = {10: "审核通过", 11: "未审核", 12: "审核未通过", 13: "已关闭"};
});

