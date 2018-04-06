function test(){
    var pages = getPages("frame");
    var editContainer = document.getElementById("edited");
    //Get Text
    var textArray = extractPageData(pages[0]);
    //End of get Text
    //Change Text
    var textLayer = pages[0].getElementsByClassName("textLayer")[0];
    var textElements = textLayer.getElementsByTagName("div");
    //text Elements is a array;

    for (i =0; i< textElements.length; i++){
            //translate then position then replace then redraw
        calculateNewElement(textArray[i],i);
    }


}
//End of Change Text
//------------------------------------------------------------
//Functions

//returns an array of pages in from
//param from is the container of the pages
function getPages(from){
    var iframe = document.getElementById(from);
    var iframeContent = iframe.contentDocument || iframe.contentWindow.document;

    var pages = iframeContent.getElementsByClassName("page");
    return pages;
}

//DATA EXTRACTION
//Returns a 2d array containing text sections with its propeties
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
    return textArray;
}

function calculateNewElement(dataObject,i){
    var srcLang = "en";
    var tarLang = "es";

    var txt = dataObject[6];

    var url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=" 
        + srcLang + "&tl=" + tarLang + "&dt=t&q=" + encodeURI(txt);

    fetch(url)
        .then(function(resp){
            resp.json()
            .then(function(data){
                translatedText = data[0][0][0];
                //maths
                var newDataObject = dataObject;
                newDataObject[0] = dataObject[0];//change
                newDataObject[1] = dataObject[1];//change
                newDataObject[2] = dataObject[2];
                newDataObject[3] = dataObject[3];
                newDataObject[4] = dataObject[4];
                newDataObject[5] = dataObject[5];
                newDataObject[6] = translatedText;
                
                insertToPage(getPages("frame")[0], newDataObject,i);
                document.getElementById("edited").innerHTML = getPages("frame")[0].innerHTML;
            })
            .catch(function(resp){
                console.log("Invalid respose: " + resp);
            })
        })
        .catch(function(resp){
            console.log("Error: " + resp);
        })
}

function insertToPage(page, data,i){
    var textLayer = page.getElementsByClassName("textLayer")[0]; 
    var textElements = textLayer.getElementsByTagName("div");
    
    textElements[i].style.cssText = data[0];
    textElements[i].style.fontFamily = data[1];
    textElements[i].style.fontSize = data[2];
    textElements[i].style.left = data[3];
    textElements[i].style.length = data[4];
    textElements[i].style.top = data[5];
    textElements[i].innerHTML = data[6];
}