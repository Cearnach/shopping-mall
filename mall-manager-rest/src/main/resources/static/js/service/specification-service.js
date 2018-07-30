app.service("brandService", function ($http) {
    this.findAll = function (page, size) {
        return $http.get("/manager/specification/all?page=" + (page - 1) + "&size=" + size);
    };
    this.save = function (entity) {
        return $http.post("/manager/specification/save", entity);
    };
    this.delete = function (selectedIds) {
        return $http.delete("/manager/specification/deleteAll?ids=" + selectedIds);
    };
});