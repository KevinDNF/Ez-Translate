<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="css/style.css">
        <!-- lib css-->
    <link rel="stylesheet" href="js/lib/text_layer_builder.css">
    <title>Ez Translate</title>
    <script src="/ksf/js/hypas-api.js" language="JavaScript"></script>
    <script src="js/script.js"></script>
        <!-- More Scripts at the bottom of the page-->
		<!-- Library PDF.JS -->
    <script src="js/lib/pdf.js"></script>
    <script src="js/lib/pdf.worker.js"></script>
    <script src="js/lib/text_layer_builder.js"></script>
		<!-- Library PDF.JS -->

  </head>

  <body onload="HyPAS.App.fullScreen(false);">
    <div id="mainBody">

        <div class="header">
            <button type="button">
                <img src="img/home.png" alt="Go Home">
            </button>
            <h1>Ez Translate</h1>
        </div>

        <div class="section" id="mainUI">
            <form>
                <div class="header">
                    <h2>Select a File</h2>
                </div>
                <div class="main">
                    <button type="button">
                        <img src="img/USB.png" alt="USB" onclick="openFileExplorer('/')" >
                        <p>USB
                    </button>
                    <!-- 
                    <button type="button" onclick="pickCloudService()">
                        <img src="img/CloudDown.png" alt="Cloud">
                        <p>Cloud
                    </button>
                    <button type="button">
                        <img src="img/Scan.png" alt="Scan">
                        <p>Scan
                    </button>
                    -->
					<button type="button" onclick="translateDocument()">
                        <img src="img/translate.png" alt="Translate">
                        <p>Translate
                    </button>
                </div>
            </form>

            <form>
                <div class="header">
                    <h2>Select Languages</h2>
                </div>
                <div class="main">
                    <p>From:</p>
                    <button type="button" class="longButton">
                       Spanish 
                    </button>

                    <img src="img/arrow.png" alt="arrow">

                    <p>To:</p>
                    <button type="button" class="longButton">
                       English 
                    </button>
                </div>
            </form>
						
						<!--
            <iframe id="frame" src="js/lib/pdfjs/web/viewer.jsp?file=%2FTranslatorApp/tempPDFs/temp.pdf">
               Something went wrong... 
            </iframe>
            <div id="edited">
            	Hello
            </div>
						-->
					<div id="canvasContainer">
					</div>
            <form>
                <div class="header">
                    <h2>Save To</h2>
                </div>
                <div class="main">
                <!--
                	<a href="editDocument.jsp">
                	 	Test
                    </a>
                    -->
					
                    <button type="button" onclick="saveToUSB()">
                        <img src="img/USB.png" alt="USB">
                        <p>USB
                    </button>
                    <!--  
                    <button type="button" onclick="pickCloudService()">
                        <img src="img/CloudUP.png" alt="Cloud">
                        <p>Cloud
                    </button>
                     --> 
                    <button type="button" onclick="printDocument()">
                         <img src="img/Print.png" alt="Print">
                         <p>Print
                    </button>           
                </div>
            </form>

            <form>
                <div class="header">
                    <h2>Settings</h2>
                </div>
                <div class="main">
                	<!--
                    <button type="button" class="longButton">
                       Lock Settings 
                    </button>
                    -->
                    <p>Accent Color:</p>
                    <button type="button" class="longButton" onclick="pickAColour()">
                    	Change color
                    </button>
                    <!--
                    <button type="button" class="longButton" onclick="">
                       Set settings as default 
                    </button>           
                    -->
                </div>
            </form>
        </div>
<!--              Overlay               -->
        <div id="overlay">
            <form id="pickCloud">
                <div class="header">
                    <h2>Pick a cloud service</h2>
                    <button type="button" class="close" onclick="exitOverlay()">
                    </button>
                </div>
                <div class="main">
                    <button type="button" onclick="loginToCloud()">
                        <img src="img/gdrive.png" alt="google drive">
                        <p>Google Drive</p>
                    </button>
                    <button type="button" onclick="loginToCloud()">
                        <img src="img/mega.png" alt="mega">
                        <p>Mega</p>
                    </button>
                    <button type="button" onclick="loginToCloud()">
                        <img src="img/onedrive.png" alt="one drive">
                        <p>One Drive</p>
                    </button>
                </div>
            </form>

            <form id="login">
                <div class="header">
                    <h2>Login</h2>
                    <button type="button" class="close" onclick="exitOverlay()">
                    </button>
                </div>
                <div class="main">
                    <input type="text">
                        Username
                    <input type="text">
                        Passwords
                </div>
            </form>
            <form id="pickAColour">
                <div class="header">
                    <h2>Pick a colour</h2>
                    <button type="button" class="close" onclick="exitOverlay()">
                    </button>
                </div>
                <div class="main">
                    <button type="button" onclick="changeColor('blue')">
                   		<div class="colorblock" style="background-color:blue"></div>
                    </button>          
                    <button type="button" onclick="changeColor('rgb(223,55,55)')">
                   		<div class="colorblock" style="background-color:rgb(233,55,55)"></div>
                    </button>          
                    <button type="button" onclick="changeColor('green')">
                   		<div class="colorblock" style="background-color:green"></div>
                    </button>          
                    <button type="button" onclick="changeColor('purple')">
                   		<div class="colorblock" style="background-color:purple"></div>
                    </button>          
				</div> 
            </form>
            
			<form id="fileExplorer">

			</form>
			
			<form id="printingOptions">
                <div class="header">
                    <h2>Print options</h2>
                    <button type="button" class="close" onclick="exitOverlay()">
                    </button>
                </div>
                <div class="main">
                	<select name="colormode">
                		<option>AUTO</option>
                		<option>GRAY</option>
                		<option>B&W</option>
                		<option>FILE PROPERTY</option>
                	</select>
<!--                 	<select name="colormode"> -->
<!--                 		<option>AUTO</option> -->
<!--                 		<option>GRAY</option> -->
<!--                 		<option>B&W</option> -->
<!--                 		<option>FILE PROPERTY</option> -->
<!--                 	</select> -->
				</div> 
            </form>
			
        </div>
    </div>
<!--- viewer scripts
	I have done them in the same file as the jsp to 
	avoid havin to deal with the importing and all that jazz -->

	<script>
        //onPdfSelection
		function selectFile(path){
            cleanContainer();
			console.log("Loading...")
			url = menuUrl + "?Action=SelectedFile&Path=" + path;
			fetch(url)
			.then((resp) =>{
				resp.text().then((buf) => {
					//resp.text() is a Base64 encoded pdf
					console.log("Buffer Received");
					//convert text to base64 encoded data
					arrayData = base64toUint8Array(buf);
					console.log("Base64 -> Uint8Array: Success!");
                    //loadPDF({data: arrayData});	
                    startViewer({data: arrayData});
				})
			})
		}

        //------PDF VIEWER FUNCTIONALITY STARTS HERE----//
        //This code block contains tall the functionality of the viewer
        //Viewport will first be set to 1 so ensure the quality of the pdf
        //is good then ill extract the text with its position and scale.
        function startViewer(dataArray){
            pdfjsLib.getDocument(arrayData)
            .then((pdf) =>{
                var container = document.getElementById("canvasContainer");
                for (var i=1; i <= pdf.numPages; i++){
                    pdf.getPage(i).then((page) => {
                        var scale = 1; //0.5
                        var viewport = page.getViewport(scale);
                        var div = document.createElement("div");
                        div.setAttribute("id","page-" + (page.pageIndex + 1));
                        div.setAttribute("class", "page");
                        div.setAttribute("style", "position:relative");
                        container.appendChild(div);

                        var canvas = document.createElement("canvas");
                        div.appendChild(canvas);
                        var context = canvas.getContext("2d");
                        canvas.height = viewport.height;
                        canvas.width = viewport.width;

                        var renderContext = {
                            canvasContext: context,
                            viewport: viewport
                        };

                        page.render(renderContext)
                            .then(() => {
                                return page.getTextContent();
                            })
                            .then((textCont) =>{
                                var textLayerDiv = document.createElement("div");
                                textLayerDiv.setAttribute("class", "textLayer");
                                div.appendChild(textLayerDiv);

                                var textLayer = new TextLayerBuilder({
                                    textLayerDiv: textLayerDiv,
                                    pageIndex: page.pageIndex,
                                    viewport:viewport
                                });

                                textLayer.setTextContent(textCont);
                                textLayer.render();
                                //RENDER IS DONE
                                //Translation here
                            })
                    })
                }
        })
        }

        function cleanContainer(){
            var container = document.getElementById("canvasContainer");
            container.innerHTML = "";
        }

        //---------------------Utility functions------------------//
		// base64 --> uint8Array
		// we will have to invert this conversion to send the pdf back
		// maybe move this function to a utility javascript file?
		function base64toUint8Array(data){
			// Remove "safe for web" characters
			data = data.replace(/-/g,"+");
			data = data.replace(/_/g,"/");

			//Base64 --> string
			var BASE64_MARKER = ';base64,';
		 	var base64Index = data.indexOf(BASE64_MARKER) + BASE64_MARKER.length;
		  	var base64 = data.substring(base64Index);
		  	var raw = window.atob(base64);
		  	//raw pdf data.
		  	var rawLength = raw.length;
		  	//String --> Uint8Array
		  	var array = new Uint8Array(new ArrayBuffer(rawLength));
		  	console.log("Raw Buffer length:" + raw.length);

		  	for(var i = 0; i < rawLength; i++) {
				array[i] = raw.charCodeAt(i);
				  //console.out(raw.charCodeAt(i));
		  	}
		  	return array;
		}
        
    </script> 
    <script src="js/translate.js"></script>
</body>
</html>
