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
	loadDoc("http://localhost:8080/inTuition_draft/client/html/WelcomeJSON.do",welcomeFunction);

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
			 console.log("in if of onreadystatechange (ie status==400 readyState==2)");
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

function welcomeFunction(xhr){
	console.log("in welcomeFunction xhr");
	let regInfo=JSON.parse(xhr.responseText);
	console.log(regInfo);
	// Split up JSON 
	let users=regInfo.users;
	let depts=regInfo.depts;
	let jobs = regInfo.jobs;
	// Persist data in browser for curresnt session (ie will clear when browser tab closes)
	// Check browser support

	// Display Name
	var txt="";
	// Iterate through names
	for(i=0;i<users.length;i++){
		txt+="<option value ="+ users[i].userID + ">"+users[i].firstname + " " + users[i].lastname+"</option>";
	}
	console.log(txt);
	document.getElementById("supervisor").innerHTML=txt;

	
	var txt="";
	// Iterate through names
	for(i=0;i<jobs.length;i++){
		txt+="<option value ="+ jobs[i].jobID + ">"+jobs[i].job+"</option>";
	}
	console.log(txt);
	document.getElementById("job").innerHTML=txt;

	
	var txt="";
	// Iterate through names
	for(i=0;i<depts.length;i++){
		txt+="<option value ="+ depts[i].id + ">"+depts[i].name+"</option>";
	}
	console.log(txt);
	document.getElementById("dept").innerHTML=txt;

	
}
