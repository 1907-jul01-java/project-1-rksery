//var filePath = document.getElementById("filePath");
//https://thoughtbot.com/blog/ridiculously-simple-ajax-uploads-with-formdata
function fileUpload() {
    var xhr = new XMLHttpRequest();
    var fileInput = document.getElementById('filePath');
    var file = fileInput.files[0];
    var formData = new FormData();

    formData.append('file', file);

    xhr.open('POST', 'http://localhost:8080/project1/api/image/', true);
    //xhr.setRequestHeader("Content-Type", "multipart/form-data");
    xhr.send(formData);
}
