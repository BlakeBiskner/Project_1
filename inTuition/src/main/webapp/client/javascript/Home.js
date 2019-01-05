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
	loadDoc("http://localhost:8080/inTuition/client/html/HomeJSON.do",homeFunction);
	// Application Form
	loadDoc("http://localhost:8080/inTuition/client/html/NewFormJSON.do",formFunction);

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
	let formSession=JSON.parse(xhr.responseText);
	console.log(formSession);

	// Variable Declaration
	let typeList=formSession.eventTypes;
	let gradeList=formSession.eventGradeFormats;
	let user=formSession.user;

	// Display Name
	// Displays automatically when last function runs so change tuition reimbursement innerhtml id to match
	document.getElementById("dropdownUser").innerHTML=(user.firstname+" "+user.lastname);
	// Display name
	// document.getElementById("dopdownUser").innerHTML=(formSession.user.firstName+" "+formSession.user.lastName);
	// Display Remaining Reimbursement

	// Display Type of Event Dropdown
	var txt="";
	// Iterate through names
	for(i=0;i<typeList.length;i++){
		txt+="<option>"+typeList[i].desc+"</option>";
	}
	console.log(txt);
	document.getElementById("eventType").innerHTML=txt;

	// Set corresponding reimbursement percentage
	console.log("hi");
	console.log(typeList)
	document.getElementById("eventType").addEventListener("change",function(){displayCoverage(typeList);}); // To use parameterized function must wrap in function(){;}
	document.getElementById("eventGradeFormat").addEventListener("change",function(){displayGrades(gradeList);})
	document.getElementById("eventCost").addEventListener("change",displayCostInCart);

	// Display Grade Format Dropdown
	var gradeTxt="";
	for(i=0;i<gradeList.length;i++){
		console.log(gradeList[i]);
		gradeTxt+="<option>"+gradeList[i].format+"</option>";
	}
	document.getElementById("eventGradeFormat").innerHTML=gradeTxt;
}

// Handler Functions
function displayCoverage(typeList){
	console.log("hi2");
	console.log(typeList);
	let eventSelection=document.getElementById("eventType").value;
	console.log(eventSelection);
	for(i=0;i<typeList.length;i++){
		console.log(typeList[i].desc);
		if((typeList[i].desc)==eventSelection){
			console.log(typeList[i].coverage);
			document.getElementById("eventCoverage").placeholder=(typeList[i].coverage);
			document.getElementById("cardCoverage").innerHTML=(typeList[i].coverage);
		}
	}
	console.log("About to update cart");
	updateCart();
}

function displayGrades(gradeList){
	console.log("in displayGrades");
	console.log(gradeList);
	let gradeSelection=document.getElementById("eventGradeFormat").value;
	console.log(gradeSelection);
	for(i=0;i<gradeList.length;i++){
		if((gradeList[i].format)==gradeSelection){
			console.log(gradeList[i].desc);
			document.getElementById("eventGradeDescription").placeholder=(gradeList[i].desc);
		}
	}
}

function displayCostInCart(){
	console.log("in displayCostInCart");
	document.getElementById("cardEventTotalCost").innerHTML=document.getElementById("eventCost").value;
	console.log("About to update cart");
	updateCart();
}

// Helper Functions
function updateCart(){
	let currAvailable=(document.getElementById("availableReimbursement").innerHTML);
	let percent=(document.getElementById("cardCoverage").innerHTML)*.01;
	console.log(percent);
	let currCost=(document.getElementById("cardEventTotalCost").innerHTML);
	console.log(currCost);
	
	let currEstimate=percent*currCost;
	console.log(currEstimate);
	
	let currRemaining=currAvailable-currEstimate;
	console.log(currRemaining);
	
	document.getElementById("cardEstimateReimbursement").innerHTML=currEstimate;
	document.getElementById("cardRemainingReimbursement").innerHTML=currRemaining;
}
