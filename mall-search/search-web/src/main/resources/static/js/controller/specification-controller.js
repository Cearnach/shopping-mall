app.controller("specificationController", function ($scope, $http, $controller, specificationService) {
    $controller('baseController', {$scope: $scope});
    $scope.findAll = function (page, size) {
        if (page === undefined || page < 1) {
            page = 1;
        }
        if (size === undefined || size < 0) {
            size = 10;
        }
        specificationService.findAll(page, size)
            .then(function (resp) {
                $scope.pageInfo = resp.data;
                $scope.paginationConf.totalItems = $scope.pageInfo.totalElements;
            }).catch(function () {
            alert("获取列表失败");
        });
    };

    $scope.refreshList = function () {
        $scope.paginationConf.currentPage = 1;
    };
    $scope.bindSaveData = function (entity) {
        $scope.entity = entity;
    };
    $scope.save = function () {
        if ($scope.entity === undefined) {
            alert("数据不完整");
            return;
        }
        specificationService.save($scope.entity).then(function (resp) {
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
            specificationService.delete($scope.selectedIds)
                .then(function (resp) {
                    if (resp.data.success) {
                        // alert("删除成功");
                        $scope.refreshList();
                    } else {
                        console.log(resp);
                        alert("删除失败");
                    }
                }).catch(function () {
                alert("删除失败");
                console.log('error');
            })
        }
    };
    $scope.findOption = function (entity) {
        $scope.entity = entity;
        specificationService.findOption(entity)
            .then(function (resp) {
                $scope.entity.specificationOptionList = resp.data;
                console.log($scope.entity);
            })
            .catch(function (reason) {
                console.log(reason);
            });
    };
    $scope.addTableRow = function () {
        $scope.entity.specificationOptionList.push({});
    };


    $scope.deleteTableRow = function (index) {
        $scope.entity.specificationOptionList.splice(index, 1);
    };

});

