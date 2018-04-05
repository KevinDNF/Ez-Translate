function pickCloudService(){
    document.getElementById("overlay").classList.add("show");
    document.getElementById("pickCloud").classList.add("show");
}
function loginToCloud(){
    document.getElementById("pickCloud").classList.remove("show");
    document.getElementById("login").classList.add("show");
}
function exitOverlay(){
    document.getElementById("overlay").classList.remove("show");
    document.getElementById("login").classList.remove("show");
    document.getElementById("pickCloud").classList.remove("show");
}

function pickAColour(){
    document.getElementById("overlay").classList.add("show");
    document.getElementById("pickAColour").classList.add("show");
}

$('#select').change(function(){
    if($(this).val() == 'A'){ 
      $("header").css('background-color', 'white');
    }
      if($(this).val() == 'B'){
      $("header").css('background-color', 'red');
    }
      if($(this).val() == 'C'){
      $("header").css('background-color', 'yellow');
    }
      if($(this).val() == 'D'){
      $("header").css('background-color', 'green');
    }
});
