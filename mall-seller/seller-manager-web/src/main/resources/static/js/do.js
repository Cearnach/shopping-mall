function register() {
    var formData = new FormData(document.getElementById('reg-form'));
    $.ajax({
        url: '/reg',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false
    });
}