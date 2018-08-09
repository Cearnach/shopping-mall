app.service("sellerService", function ($http) {
    this.findAll = function (page, size) {
        return $http.get("/seller/all?page=" + (page - 1) + "&size=" + size);
    };
    this.findByStatusCode = function (status, page, size) {
        return $http.get("/seller/all/status?statusCode=" + status + "&page=" + (page - 1) + "&size=" + size);
    };
    this.findUncheckSellers = function (page, size) {
        return findByStatusCode(11, page, size);
    };
    this.findCheckedSellers = function (page, size) {
        return findByStatusCode(10, page, size);
    };
    this.findCheckDeniedSellers = function (page, size) {
        return findByStatusCode(12, page, size);
    };
    this.findClosedSellers = function (page, size) {
        return findByStatusCode(13, page, size);
    };
    this.save = function (entity) {
        return $http.post("/seller/save", entity);
    };
    this.delete = function (selectedIds) {
        return $http.delete("/seller/deleteAll?ids=" + selectedIds);
    };
});