
function getHTML(){
	var iframe = document.getElementById('frame');
	var iframeContent = iframe.contentDocument || iframe.contentWindow.document;
	var viewer = iframeContent.getElementById("viewer");
	//document.body.innerHTML = viewer.innerHTML;
	return viewer.innerHTML;
}


function changeText(){
	console.log(this)
}


function editFile(){
	var viewerHTML = getHTML();
	var editContainer = document.getElementById("edited");
	editContainer.innerHTML = viewerHTML;

}
function test(){
	var headHTML = editContainer.getElementsByTagName('head').innerHTML;
	
	
	headHTML    += '<link type="text/css" rel="stylesheet" href="js/lib/pdjs/web/viewer.css">';
	editContainer.getElementsByTagName('head').innerHTML = headHTML;
}
