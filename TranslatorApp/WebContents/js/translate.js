//KevinDNF
//

//Globals
'strict'
var sourcePointer = document.getElementById("canvasContainer");
var clonedSource = sourcePointer.cloneNode(true);

var pageID = 0;
var targetPath = "/";
var content;

//weird by idk at this point
var finishedTranslation = false;
var counter = 0;

//main function
//call to start translating document
//Source document from id="frame" has to have the same DOM structure as pdf.js
//Target document is id="edited" which can be an empty div
function translateDocument(){
    sourcePointer = document.getElementById("canvasContainer");
    clonedSource = sourcePointer.cloneNode(true);
    content = sourcePointer;
    var pages = getPages(content)

    //duplicates viewer
    //pageContent = pages[pageID].getElementsByClassName("textLayer")[0].outerHTML;
    //document.getElementById("edited").innerHTML = pageContent; 

    //Optimize Text
    optimize(content);
    //Process text Elements

    //page = document.getElementById("edited");
    var textArray = processTextElements(content);
    var promiseArray = [];
    var srcLang = "en";
    var tarLang = "es";

    for (i = 0; i < textArray.length; i++){
        //translate, position, replace then redraw
        //calculateNewElement(textArray[i],i);
        var txt = textArray[i][6];

        var url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=" 
            + srcLang + "&tl=" + tarLang + "&dt=t&q=" + encodeURI(txt);
        promiseArray.push(requestTxt(url));
    }

    Promise.all(promiseArray)
        .then((array) =>{
            for (i = 0; i < textArray.length; i++){
                var dataobject = textArray[i];
                var newdataobject = dataobject;
                var translatedtext = array[i];
                newdataobject[0] = dataobject[0];//change
                newdataobject[1] = dataobject[1];//change
                newdataobject[2] = dataobject[2];//maybe use a dictionary??
                newdataobject[3] = dataobject[3];
                newdataobject[4] = dataobject[4];
                newdataobject[5] = dataobject[5];
                newdataobject[6] = translatedtext;
                insertToPage(content, newdataobject,  i);
            }
            console.log("ALL TRANSLATED");
            //GOOD TO GO
        })


}
//----------------------Functions---------------------------------



//returns an array of pages in from
//@param from is the container of the pages
function getPages(from){
    var pages = from.getElementsByClassName("page");
    return pages;
}
//returns the text elements of the page
//@param page to extract elements
function getTextElements(src){
    var textLayer = src.getElementsByClassName("textLayer")[pageID];
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
function requestTxt(url){
    return fetch(url)
        .then(resp => resp.json())
        .then(data => data[0][0][0])
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
    console.log(page);
}

function optimize(htmlToOptimize){
    //0 = CssText
    //1 = fontFamily
    //2 = fontSize
    //3 = left
    //4 = length
    //5 = top  
    //console.log(htmlToOptimize);
    textDivs = getTextElements(htmlToOptimize);
    
    var prevDiv = null;
    var currDiv = null;

    //check if same line
    for (i = 0; i < textDivs.length-1; i++){

        currDiv = textDivs[i];

        if (  (currDiv != null) && (prevDiv != null) && checkProximity([currDiv, prevDiv],3)){
            //console.log("same Line");
            //console.log(prevDiv);
            //console.log(currDiv);
            prevDiv.innerHTML += currDiv.innerHTML;
            textDivs[i].outerHTML = null;
            currDiv = prevDiv;
            i -= 1;
        }

        prevDiv = currDiv;
    }
    //check if same paragraph here
}
//
//@param divArray is divs to compare
//@param accuracy is how close to each other is fine
//@param side is side to check, can be top or left
function checkProximity(divArray, accuracy){
    
    var a = divArray[0];
    var b = divArray[1];

    var aWidth = divArray[0].offsetWidth; 
    var bWidth = divArray[1].offsetWidth; 

    aTop = parseInt(a.style.top.slice(0,-2));
    bTop = parseInt(b.style.top.slice(0,-2))

//offsetHeigh
    if (Math.abs(aTop - bTop) < accuracy){
        //assume they are part of the same line
    	//expand to check if same line but comuns
        return true;
    }
}