app.service("sellerService", function ($http) {
    this.findAll = function (page, size) {
        return $http.get("/seller/all?page=" + (page - 1) + "&size=" + size);
    };
    this.findByStatusCode = function (statusCode, page, size) {
        if (statusCode === -1) {
            return this.findAll(page, size);
        }
        return $http.get("/seller/all/status?statusCode=" + statusCode + "&page=" + (page - 1) + "&size=" + size);
    };
    this.findUncheckSellers = function (page, size) {
        return this.findByStatusCode(11, page, size);
    };
    this.findCheckedSellers = function (page, size) {
        return this.findByStatusCode(10, page, size);
    };
    this.findCheckDeniedSellers = function (page, size) {
        return this.findByStatusCode(12, page, size);
    };
    this.findClosedSellers = function (page, size) {
        return this.findByStatusCode(13, page, size);
    };
    this.updateStatusCode = function (sellerId, statusCode) {
        return $http.put("/seller/update/status?sellerId=" + sellerId + "&statusCode=" + statusCode);
    };
    this.save = function (entity) {
        return $http.post("/seller/save", entity);
    };
    this.delete = function (selectedIds) {
        return $http.delete("/seller/deleteAll?ids=" + selectedIds);
    };
});