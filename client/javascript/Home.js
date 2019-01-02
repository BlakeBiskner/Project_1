/*
 * Home Page Javascript
 * Blake Biskner
 * version 1.0
 * 
 */

window.onload = function() {
	console.log("here");
	getUserInfo();
	console.log("done");
}

function getUserInfo() {
	// 1 Open XHR Object
	let xhr = new XMLHttpRequest();
	// 2 Define onreadystatechange function
	xhr.onreadystatechange = function() {
		if ((xhr.readyState == 4) && (xhr.status == 200)) {
			let user = JSON.parse(xhr.responseText);
			console.log(user);
			setValues(user);
		}
	}
	// 3 Open request
	// Change to inTuition for Matt
    xhr.open("GET",
            "http://localhost:8080/inTuition_draft/client/html/HomeJSON.do",
			true);
	// 4 Send request
	xhr.send();
}

function setValues(user) {
	console.log("in setValues");
	document.getElementById("dropdownUser").innerHTML=(user.firstname+" "+user.lastname);
}


