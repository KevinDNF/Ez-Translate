<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="css/style.css">
    <title>Ez Translate</title>
    <script src="/ksf/js/hypas-api.js" language="JavaScript"></script>
    <script src="js/script.js"></script>
    <script src="js/translate.js"></script>

		<!-- Library PDF.JS -->
    <script src="js/lib/pdf.js"></script>
    <script src="js/lib/pdf.worker.js"></script>
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
						<canvas id="viewer">

						</canvas>
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
					
                    <button type="button" onclick="openFileExplorer('/')">
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
                        close
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
                        close
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
                        close
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
        </div>
    </div>
<!--- viewer scripts
	I have done them in the same file as the html to 
	avoid havin to deal with the importing and all that jazz -->

	<script>
		var pdf, 
				currentPage, 
				totalPage, 
				pageRendering = 0, 
				canvas = document.getElementById("viewer"),
				canvas = canvas.getContext("2d");

		function selectFile(path){
			console.log("Loading...")
			url = menuUrl + "?Action=SelectedFile&Path=" + path;
			fetch(url)
			.then((resp) =>{
				resp.text().then((buf) => {
					console.log("Buffer Received");
					//convert text to base64 encoded data
					buf = buf.replace(/-/g,"+");
					buf = buf.replace(/_/g,"/");
					//console.log(buf);
					dd = convertData(buf);
					//console.log(dd);
					console.log("File Loaded");
					//cdata = convertData(buf);
					
					displayPDF({data: dd});	
				})
			})
		}

		function displayPDF(binData){
			data = binData;//convert?
			pdfjsLib.getDocument(data)
				.then((pdf)=>{
					console.log("PDF LOADED");
					console.log(pdf.numPages);	
					//then we display it
			});
		}
		
		function convertData(data){
			var BASE64_MARKER = ';base64,';
		 	var base64Index = data.indexOf(BASE64_MARKER) + BASE64_MARKER.length;
		  	var base64 = data.substring(base64Index);
		  	var raw = window.atob(base64);
		  	var rawLength = raw.length;
		  	var array = new Uint8Array(new ArrayBuffer(rawLength));
		  	console.log(raw.length);

		  	for(var i = 0; i < rawLength; i++) {
				array[i] = raw.charCodeAt(i);
				  //console.out(raw.charCodeAt(i));
		  	}
		  	return array;
		}
		
		function base64ToUint8Array(base64) {
		    var raw = atob(base64);
		    var uint8Array = new Uint8Array(raw.length);
		    for (var i = 0; i < raw.length; i++) {
		      uint8Array[i] = raw.charCodeAt(i);
		    }
		    return uint8Array;
		  }
		
	
	</script> 
</body>
</html>
