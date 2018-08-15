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
            $scope.findAll($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
            $scope.reload = false;
            setTimeout(function () {
                $scope.reload = true;
            }, 200);
        }
    };
    $scope.refreshList = function () {
        var currentPage = $scope.paginationConf.currentPage;
        if (currentPage === 1) {
            $scope.paginationConf.currentPage = 0;
        } else {
            $scope.paginationConf.currentPage = 1;
        }
    };

    // 定义方法：获取JSON字符串中的某个key对应值的集合
    $scope.jsonToString = function (jsonObj, key) {
        var value = "";
        if (jsonObj === undefined || jsonObj === null) {
            return value;
        }
        for (var i = 0; i < jsonObj.length; i++) {
            value += jsonObj[i].name;
            if (i < jsonObj.length - 1) {
                value += " , ";
            }
        }
        return value;
    };
// 从集合中查询某个名称的值是否存在
    $scope.searchObjectByKey = function (list, keyName, keyValue) {
        for (var i = 0; i < list.length; i++) {
            if (list[i][keyName] === keyValue) {
                return list[i];
            }
        }
        return null;
    };
});


function setChkCheckedAll(parentId, childClazz) {
    var isChecked = $("#" + parentId).prop("checked");
    $("." + childClazz).prop("checked", isChecked);
}