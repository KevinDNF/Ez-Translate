function pickCloudService(){
    document.getElementById("overlay").classList.add("show");
    document.getElementById("pickCloud").classList.add("show");
}
function loginToCloud(){
    document.getElementById("pickCloud").classList.remove("show");
    document.getElementById("login").classList.add("show");
}

function displayPath(){
	
}

function printDocument(){
	url = menuUrl + "?Action=Print";
	fetch(url);
}

function print(){
	
}

function exitOverlay(){
    overlay = document.getElementById("overlay");
    forms = overlay.children;
    for (i=0;i<forms.length;i++){
    	forms[i].classList.remove("show");
    }
    overlay.classList.remove("show");
}
function pickAColour(){
    document.getElementById("overlay").classList.add("show");
    document.getElementById("pickAColour").classList.add("show");
}
function changeColor(color){
    headers = document.getElementsByClassName("header");
    for (i=1; i<headers.length; i++){
    headers[i].style.backgroundColor = color;
    }
}
var menuUrl = "MainMenu"; 

function openFileExplorer(){
	url = menuUrl + "?Action=FileExplorer";
	fetch(url)
	.then((resp) => {
			console.log(resp)
			resp.text().then((text) => {
				console.log(text)
				document.getElementById("fileExplorer").innerHTML = text;
				document.getElementById("overlay").classList.add("show");
				document.getElementById("fileExplorer").classList.add("show"); 
				})
				.catch((err) => console.log(err))
			})
		.catch((resp) => console.log("Error: " + resp))
}
