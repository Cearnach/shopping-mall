app.controller("typeTemplateController", function ($scope, $http, $controller, typeTemplateService, brandService, specificationService) {
    $controller('baseController', {$scope: $scope});
    $scope.findAll = function (page, size) {
        if (page === undefined || page < 1) {
            page = 1;
        }
        if (size === undefined || size < 0) {
            size = 10;
        }
        typeTemplateService.findAll(page, size)
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
        typeTemplateService.save($scope.entity).then(function (resp) {
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
            typeTemplateService.delete($scope.selectedIds)
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
    $scope.brandList = {data: [{id: 1, text: "联想"}, {id: 2, text: "华为"}]};

    $scope.findBrandList = function () {
        brandService.findAll(1, 10).then(function (resp) {
            $scope.brandList = {data: []};
            resp.data.data.forEach(function (brand) {
                $scope.brandList.data.push({id: brand.id, text: brand.name});
            });
        });
    };
    $scope.findSpecificationList = function () {
        specificationService.findAll(1, 10).then(function (resp) {
            $scope.specificationList = {data: []};
            resp.data.data.forEach(function (spec) {
                $scope.specificationList.data.push({id: spec.id, text: spec.name});
            });
        });
    };
    $scope.t = function (entity) {
        $scope.brandIds = [];
        $scope.specIds = [];
        if (entity.selectedBrands !== undefined) {
            entity.selectedBrands.forEach(function (brand) {
                $scope.brandIds.push(brand.id);
            });
        }
        if (entity.selectedSpecs !== undefined) {
            entity.selectedSpecs.forEach(function (spec) {
                $scope.specIds.push(spec.id);
            });
        }
        console.log($scope.brandIds);
        console.log($scope.specIds);
    };
});

