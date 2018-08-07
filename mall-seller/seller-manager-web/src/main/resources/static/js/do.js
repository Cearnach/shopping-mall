function register() {
    var formData = new FormData(document.getElementById('reg-form'));
    $.ajax({
        url: '/seller/register',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        sucess:function (result) {
            console.log(result);
        },
        error:function (error) {
            console.log(error);
        }
    });
}