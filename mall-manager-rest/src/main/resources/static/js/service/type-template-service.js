app.service("typeTemplateService", function ($http) {
    this.findAll = function (page, size) {
        return $http.get("/manager/typeTemplate/all?page=" + (page - 1) + "&size=" + size);
    };
    this.save = function (entity) {
        return $http.post("/manager/typeTemplate/save", entity);
    };
    this.delete = function (selectedIds) {
        return $http.delete("/manager/typeTemplate/deleteAll?ids=" + selectedIds);
    };
});