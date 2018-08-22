app.service("searchService", function ($http) {
    this.findAll = function (page, size) {
        return $http.get("/search/all?page=" + (page - 1) + "&size=" + size);
    };
    this.save = function (entity) {
        return $http.post("/manager/search/save", entity);
    };
    this.delete = function (selectedIds) {
        return $http.delete("/manager/search/deleteAll?ids=" + selectedIds);
    };
    this.findGoods = function (key, page, size) {
        return $http.get("/search?key=" + key + "&page=" + (page - 1) + "&size=" + size);
    }
});