//KevinDNF
//

//returns an array of pages
//param from is the container of the pages
function getPages(from){
	var iframe = document.getElementById(from);
	var iframeContent = iframe.contentDocument || iframe.contentWindow.document;

	var pages = iframeContent.getElementsByClassName("page");
	return pages;
}

//DATA EXTRACTION
function extractPageData(page){
	var textLayer = page.getElementsByClassName("textLayer")[0];
	var textElements = textLayer.getElementsByTagName("div");
	//array of text elements... ignore the last one
	var textArray = [];
	var textObject = [];
	
	for (i=0;i<textElements.length; i++){
		textObject = [];
		var style = textElements[i].style;
		//can be done by parsing cssText
		var cssText = style.cssText;

		var fontFamily = style.fontFamily;
		var fontSize = style.fontSize;
		var left = style.left;
		var length = style.length;
		var top = style.top;
		var content = textElements[i].innerHTML;
		
		textObject = [cssText,fontFamily,fontSize,left,length,top,content];
		textArray.push(textObject);
		//document.getElementsByClassName("textLayer")[0].getElementsByTagName("div")[0].style.
	}
	console.log(textArray);	
	return textArray;
}

///DATA INSERTION
function insertPageData(page, data){
	var textLayer = page.getElementsByClassName("textLayer")[0]; 
	var textElements = textLayer.getElementsByTagName("div");
	
	for (i = 0; i < textElements.length; i++){
		textElements[i].style.cssText = data[i][0];
		textElements[i].style.fontFamily = data[i][1];
		textElements[i].style.fontSize = data[i][2];
		textElements[i].style.left = data[i][3];
		textElements[i].style.length = data[i][4];
		textElements[i].style.top = data[i][5];
		textElements[i].innerHTML = data[i][6];
	}
}

//Change Text Data Array
function changeText(page, data){
	var textLayer = page.getElementsByClassName("textLayer")[0]; 
	var textElements = textLayer.getElementsByTagName("div");
	
	for (i = 0; i < textElements.length; i++){
		//data[i][0]; //CssText
		
		//insert complex math here
		// data[i][3];//Left
		// data[i][4];//Length
		// data[i][5];//Top
		
		//Translate text here
		data[i][6] = "Hello World";
	}
	return data;
}

//MAIN
function translateHTML(){
	var pages = getPages("frame");
	var editContainer = document.getElementById("edited");
	//Get text
	textArray = extractPageData(pages[0])
	//Change Text
	textArray = changeText(pages[0], textArray);
	//Insert Text
	insertPageData(pages[0], textArray);
	//
	editContainer.innerHTML = pages[0].innerHTML;
}