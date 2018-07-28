app.service("brandService", function ($http) {
    this.findList = function (page, size) {
        return $http.get("/manager-service/brand/list?page=" + (page - 1) + "&size=" + size);
    };
    this.save = function (entity) {
        return $http.post("/manager-service/brand/save", entity)
    };
    this.delete = function (selectedIds) {
        $http.delete("/manager/brand/deleteAll?ids=" + selectedIds)
    };
});