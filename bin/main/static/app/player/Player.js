function search_summoner(){
	var summoner_name = document.getElementById('summoner_name').value;
	console.log(summoner_name);
	console.log ('검색ㄱ');
	
	var httpRequest;
	//if (window.XMLHttpRequest) { // 모질라, 사파리, IE7+ ...
	    httpRequest = new XMLHttpRequest();
	//} else if (window.ActiveXObject) { // IE 6 이하
	//   httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	//}
	httpRequest.onreadystatechange = function (){
		if (httpRequest.readyState === XMLHttpRequest.DONE) {
			if (httpRequest.status === 200) {
				alert(httpRequest.responseText);
		    } else {
		    	alert('request에 뭔가 문제가 있어요.');
		    }
		}
	}
}

function press_enter_on_user_name(){
	if(event.keyCode==13){
	
		search_summoner();
	}

}