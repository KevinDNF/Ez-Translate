<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="js/lib/pdfjs/web/viewer.css">
<link rel="stylesheet" href="css/styleEdit.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/translate.js"></script>
</head>
	
	<button onclick="translateDocument()">EDIT DOCUMENT</button>	
	
	<iframe id="frame" src="js/lib/pdfjs/web/viewer.jsp?file=%2FTranslatorApp/tempPDFs/temp.pdf">
		Original Document goes here.
	</iframe>

	<div id="edited">
		Edited Document will go here	
	</div>

</html>
