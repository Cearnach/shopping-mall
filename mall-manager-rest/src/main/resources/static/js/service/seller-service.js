app.service("sellerService", function ($http) {
    this.findAll = function (page, size) {
        return $http.get("/manager/seller/all?page=" + (page - 1) + "&size=" + size);
    };
    this.save = function (entity) {
        return $http.post("/manager/seller/save", entity);
    };
    this.delete = function (selectedIds) {
        return $http.delete("/manager/seller/deleteAll?ids=" + selectedIds);
    };
});