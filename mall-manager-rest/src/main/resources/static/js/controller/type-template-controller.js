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


    $scope.bindSaveData = function (entity) {
        $scope.entity = entity;
        $scope.form = [];
        if (entity === undefined) {
            return;
        }
        $scope.entity.selectedBrands = [];
        if (entity.brandList !== undefined) {
            entity.brandList.forEach(function (brand) {
                $scope.entity.selectedBrands.push({id: brand.id, text: brand.name});
            });
        }
        $scope.entity.selectedSpecs = [];
        if (entity.specificationList !== undefined) {
            entity.specificationList.forEach(function (spec) {
                $scope.entity.selectedSpecs.push({id: spec.id, text: spec.name});
            });
        }
        var attr = entity.customAttribute;
        if (attr !== undefined && attr !== null && attr.trim().length !== null) {
            $scope.form.customAttribute = [];
            var attrArr = attr.split(",");
            attrArr.forEach(function (value) {
                $scope.form.customAttribute.push({text: value});
            })
        }
    };
    $scope.save = function () {
        if ($scope.entity === undefined) {
            alert("数据不完整");
            return;
        }
        var saveEntity = {};
        $scope.brandIds = [];
        $scope.specIds = [];
        if ($scope.entity.selectedBrands !== undefined) {
            $scope.entity.selectedBrands.forEach(function (brand) {
                $scope.brandIds.push(brand.id);
            });
        }
        if ($scope.entity.selectedSpecs !== undefined) {
            $scope.entity.selectedSpecs.forEach(function (spec) {
                $scope.specIds.push(spec.id);
            });
        }
        saveEntity.brandIdList = $scope.brandIds;
        saveEntity.specificationIdList = $scope.specIds;
        var id = $scope.entity.id;
        if (id !== undefined && id > 0) {
            saveEntity.id = id;
        } else {
            saveEntity.id = 0;
        }
        var customAttributeText = "";
        if ($scope.form.customAttribute !== undefined) {
            $scope.form.customAttribute.forEach(function (attr, index) {
                if (index === $scope.form.customAttribute.length - 1) {
                    customAttributeText += attr.text;
                } else {
                    customAttributeText += attr.text + ",";
                }
            });
        }
        saveEntity.customAttribute = customAttributeText;
        saveEntity.name = $scope.entity.name;
        typeTemplateService.save(saveEntity).then(function (resp) {
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
    $scope.addTableRow = function () {
        if ($scope.form === undefined) {
            $scope.form = {customAttribute: []};
        }
        if ($scope.form.customAttribute === undefined) {
            $scope.form.customAttribute = [];
        }
        $scope.form.customAttribute.push({});
    };

    $scope.deleteTableRow = function (index) {
        $scope.form.customAttribute.splice(index, 1);
    };
});

