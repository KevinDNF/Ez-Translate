//KevinDNF
//


//main function
//call to start translating document
//Source document from id="frame" has to have the same DOM structure as pdf.js
//Target document is id="edited" which can be an empty div
function translateDocument(){
    var pageID = 0;
    var pages = getPages("frame")

    //duplicates viewer
    document.getElementById("edited").innerHTML = pages[pageID].innerHTML; 

    //Process text Elements
    var textArray = processTextElements(pages[pageID]);

    for (i = 0; i < textArray.length; i++){
        //translate, position, replace then redraw
        calculateNewElement(textArray[i],i);
    }
}
//----------------------Functions---------------------------------

//returns an array of pages in from
//@param from is the container of the pages
function getPages(from){
    var iframe = document.getElementById(from);
    var iframeContent = iframe.contentDocument || iframe.contentWindow.document;

    var pages = iframeContent.getElementsByClassName("page");
    return pages;
}
//returns the text elements of the page
//@param page to extract elements
function getTextElements(page){
    var textLayer = page.getElementsByClassName("textLayer")[0];
    var textElements = textLayer.getElementsByTagName("div");
    return textElements;
}

//--------------------DATA EXTRACTION-----------------------
//Returns a 2d array containing text sections with its propeties
//@param page to extract data
function processTextElements(page){
    //get raw text Elements
    var textElements = getTextElements(page);

    var textArray = [];
    var textObject = [];
    
    //array of text elements... ignore the last one
    for (i=0; i<textElements.length -1; i++){
        textObject = [];
        var style = textElements[i].style;
        //Must improve
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
    return textArray;
}

//---------------------DATA MANIPULATION--------------------------
//Text gets translated here
//Todo 
// - Calculate position
// - Calculate style
// - customize target and source language
function calculateNewElement(dataObject, index){
    var srcLang = "en";
    var tarLang = "es";
    var page = document.getElementById("edited");
    var txt = dataObject[6];

    var url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=" 
        + srcLang + "&tl=" + tarLang + "&dt=t&q=" + encodeURI(txt);

    fetch(url)
        .then(function(resp){
            resp.json()
            .then(function(data){
                translatedText = data[0][0][0];
                //maths goes here
                var newDataObject = dataObject;
                newDataObject[0] = dataObject[0];//change
                newDataObject[1] = dataObject[1];//change
                newDataObject[2] = dataObject[2];//maybe use a dictionary??
                newDataObject[3] = dataObject[3];
                newDataObject[4] = dataObject[4];
                newDataObject[5] = dataObject[5];
                newDataObject[6] = translatedText;
                
                insertToPage(page, newDataObject,  index);
            })
            .catch(function(resp){
                console.log("Invalid respose: " + resp);
                //not json?
            })
        })
        .catch(function(resp){
            console.log("Error: " + resp);
            //no connection? or some other reason?
        })
}

// Inserts the data object to the page
function insertToPage(page, data, i){
    var textElements = getTextElements(page);

    textElements[i].style.cssText = data[0];
    textElements[i].style.fontFamily = data[1];
    textElements[i].style.fontSize = data[2];
    textElements[i].style.left = data[3];
    textElements[i].style.length = data[4];
    textElements[i].style.top = data[5];
    textElements[i].innerHTML = data[6];
}