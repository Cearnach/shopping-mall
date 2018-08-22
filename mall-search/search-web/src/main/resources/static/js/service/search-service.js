app.service("brandService", function ($http) {
    this.findAll = function (page, size) {
        return $http.get("/search/all?page=" + (page - 1) + "&size=" + size);
    };
    this.save = function (entity) {
        return $http.post("/manager/brand/save", entity);
    };
    this.delete = function (selectedIds) {
        return $http.delete("/manager/brand/deleteAll?ids=" + selectedIds);
    };
    this.findGoods = function (searchMap, page, size) {
        return $http.get("/search?name"+)
    }
});