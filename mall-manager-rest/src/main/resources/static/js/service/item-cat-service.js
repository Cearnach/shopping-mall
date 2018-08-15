app.service("itemCatService", function ($http) {
    this.findAll = function (page, size) {
        return $http.get("/manager/itemCat/all?page=" + (page - 1) + "&size=" + size);
    };
    this.save = function (entity) {
        return $http.post("/manager/itemCat/save", entity);
    };
    this.delete = function (selectedIds) {
        return $http.delete("/manager/itemCat/deleteAll?ids=" + selectedIds);
    };
});