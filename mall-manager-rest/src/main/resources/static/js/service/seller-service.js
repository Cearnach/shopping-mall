app.service("sellerService", function ($http) {
    this.findAll = function (page, size) {
        return $http.get("/seller/all?page=" + (page - 1) + "&size=" + size);
    };
    this.save = function (entity) {
        return $http.post("/seller/save", entity);
    };
    this.delete = function (selectedIds) {
        return $http.delete("/seller/deleteAll?ids=" + selectedIds);
    };
});