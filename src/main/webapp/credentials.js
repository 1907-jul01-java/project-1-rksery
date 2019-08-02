document.getElementById("usernameHeader").innerHTML = sessionStorage.getItem("username");
document.getElementById("username").value = sessionStorage.getItem("username");
//document.getElementById("usernameHeader").innerHTML = sessionStorage.getItem("username");

function logout() {
    localStorage.clear();
    window.location.href = "index.html"
}