/* 
 * AJAX JavaScript
 *
 * Blake Biskner
 * version 2.0
 */

window.onload= function(){
	console.log("in window.onload start");

	///// Callback Functions /////
	// Home Screen
	loadDoc("http://localhost:8080/inTuition_draft/client/html/HomeJSON.do",homeFunction);
	// Application Form
	loadDoc("http://localhost:8080/inTuition_draft/client/html/NewFormJSON.do",formFunction);

	console.log("completed window.onload");
}

function loadDoc(url,cFunction){
	// 1. Create xhr object
	console.log("Creating xhr");
	let xhr=new XMLHttpRequest();
	//2. Define onreadystatechange function
	xhr.onreadystatechange=function() {
		console.log("in onreadystatechange");
		if((xhr.readyState==4)&&(xhr.status==200)){
			 console.log("in if of onreadystatechange (ie status==400 readyState==2");
			 cFunction(xhr);
		}
	}
		// 3. Open request
		console.log("Opening xhr");
		xhr.open("GET",url,true);
		//4. Sending Request
		console.log("Sending xhr");
		xhr.send();
		console.log("xhr sent");
}

function homeFunction(xhr){
	console.log("in homeFunction xhr");
	let user=JSON.parse(xhr.responseText);
	console.log(user);
	// Display Name
	document.getElementById("dropdownUser").innerHTML=(user.firstname+" "+user.lastname);
	// Display Reimbursement
	document.getElementById("availableReimbursement").innerHTML=(user.yearlyReimbursementRemaining);
	// Display Alerts
	if(user.hasUrgentEmail==true){
		document.getElementById("hiddenEmailAlert").style.display="block"; // Change css to display element
	}
}

function formFunction(xhr){
	console.log("in formFunction xhr");
	let ex=JSON.parse(xhr.responseText);
	console.log(ex);
}

