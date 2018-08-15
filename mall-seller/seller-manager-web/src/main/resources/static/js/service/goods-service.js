app.service("goodsService", function ($http) {
    this.findAll = function (page, size) {
        return $http.get("/goods/all?page=" + (page - 1) + "&size=" + size);
    };
    this.save = function (entity) {
        return $http.post("/goods/save", entity);
    };
    this.delete = function (selectedIds) {
        return $http.delete("/goods/deleteAll?ids=" + selectedIds);
    };
    this.findById = function (id) {
        return $http.get("/goods/" + id);
    }
});