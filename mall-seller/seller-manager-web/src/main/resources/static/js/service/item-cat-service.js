app.service("itemCatService", function ($http) {
    this.findAll = function () {
        return $http.get("/seller/itemCat/all");
    };
    this.save = function (entity) {
        return $http.post("/seller/itemCat/save", entity);
    };
    this.delete = function (selectedIds) {
        return $http.delete("/seller/itemCat/deleteAll?ids=" + selectedIds);
    };

});