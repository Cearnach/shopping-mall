app.service("goodsService", function ($http) {
    this.findAll = function (page, size) {
        return $http.get("/seller/goods/all?page=" + (page - 1) + "&size=" + size);
    };
    this.save = function (entity) {
        return $http.post("/seller/goods/saveDTO", entity);
    };
    this.delete = function (selectedIds) {
        return $http.delete("/seller/goods/deleteAll?ids=" + selectedIds);
    };
    this.findById = function (id) {
        return $http.get("/seller/goods/" + id);
    }
});