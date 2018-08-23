app.service("brandService", function ($http) {
    this.findAll = function (page, size) {
        return $http.get("/seller/brand/all?page=" + (page - 1) + "&size=" + size);
    };
    this.save = function (entity) {
        return $http.post("/seller/brand/save", entity);
    };
    this.delete = function (selectedIds) {
        return $http.delete("/seller/brand/deleteAll?ids=" + selectedIds);
    };
});