function pickCloudService(){
    document.getElementById("overlay").classList.add("show");
    document.getElementById("pickCloud").classList.add("show");
}
function loginToCloud(){
    document.getElementById("pickCloud").classList.remove("show");
    document.getElementById("login").classList.add("show");
}
function exitOverlay(){
    document.getElementById("overlay").classList.remove("show");
    document.getElementById("login").classList.remove("show");
    document.getElementById("pickCloud").classList.remove("show");
}