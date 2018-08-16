app.controller("goodsController", function ($scope, $http, $controller, $location, goodsService, itemCatService, brandService) {
    $controller('baseController', {$scope: $scope});
    $scope.findAll = function (page, size) {
        if (page === undefined || page < 1) {
            page = 1;
        }
        if (size === undefined || size < 0) {
            size = 10;
        }
        goodsService.findAll(page, size)
            .then(function (resp) {
                $scope.pageInfo = resp.data;
                $scope.paginationConf.totalItems = $scope.pageInfo.totalElements;
            }).catch(function () {
            alert("获取列表失败");
        });
    };


    $scope.bindSaveData = function (entity) {
        $scope.entity = entity;
    };
    $scope.save = function () {

        if ($scope.entity === undefined) {
            alert("数据不完整");
            return;
        }
        $scope.entity.brandId = $("#brand-id").val();
        $scope.entity.itemCategoryId = $("#item-category-id").val();
        $scope.entity.descriptino=editor.html();
        goodsService.save($scope.entity).then(function (resp) {
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
            goodsService.delete($scope.selectedIds)
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
    $scope.fetchGoods = function () {
        $scope.findItemCatList = itemCatService.findAll()
            .then(function (value) {
                $scope.itemCatList = value.data.data;
                console.log(value);
            });
        $scope.findBrandList = brandService.findAll(1, 10)
            .then(function (value) {
                $scope.brandList = value.data.data;
            });

        var goodsId = $location.search()["id"];
        if (goodsId === undefined || goodsId.trim().length === 0) {
            return;
        }
        findById(goodsId);
    };
    $scope.findById = function (goodsId) {
        goodsService.findById(goodsId)
            .then(function (value) {
                $scope.entity = value.data;
                console.log(value);
            })
            .catch(function (reason) {
                console.log(reason);
            });
    };
});

