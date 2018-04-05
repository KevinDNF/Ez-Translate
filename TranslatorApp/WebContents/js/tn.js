
function getPages(from){
	var iframe = document.getElementById(from);
	var iframeContent = iframe.contentDocument || iframe.contentWindow.document;

	var pages = iframeContent.getElementsByClassName("page");
	return pages;
}


function changeText(){
	console.log(this)
}


function editFile(){
	var pages = getPages("frame");
	var editContainer = document.getElementById("edited");
	//Edit
	changeText(pages[0]);
	//Edit
	editContainer.innerHTML = pages[0].innerHTML;
}

function changeText(page){
	var textLayer = page.getElementsByClassName("textLayer")[0];
	var text = textLayer.getElementsByTagName("div");
	
	for (i = 0; i < 11; i++) {
	    text[i].innerHTML = "HELLO WORLD";
	}
	
	console.log(page);
	
	
	return page;
}




function test(){
	var headHTML = editContainer.getElementsByTagName('head').innerHTML;
	
	
	headHTML    += '<link type="text/css" rel="stylesheet" href="js/lib/pdjs/web/viewer.css">';
	editContainer.getElementsByTagName('head').innerHTML = headHTML;
}
