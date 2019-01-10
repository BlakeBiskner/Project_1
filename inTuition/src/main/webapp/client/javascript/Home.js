/* 
 * AJAX JavaScript
 *
 * Blake Biskner
 * version 2.0
 */

window.onload = function() {
	console.log("in window.onload start");

	// /// Callback Functions /////
	// Home Screen
	loadDoc("http://localhost:8080/inTuition/client/html/HomeJSON.do",
			homeFunction);

	console.log("completed window.onload");
}

function loadDoc(url, cFunction) {
	// 1. Create xhr object
	console.log("Creating xhr");
	let xhr = new XMLHttpRequest();
	// 2. Define onreadystatechange function
	xhr.onreadystatechange = function() {
		console.log("in onreadystatechange");
		if ((xhr.readyState == 4) && (xhr.status == 200)) {
			console
					.log("in if of onreadystatechange (ie status==400 readyState==2)");
			cFunction(xhr);
		}
	}
	// 3. Open request
	console.log("Opening xhr");
	xhr.open("GET", url, true);
	// 4. Sending Request
	console.log("Sending xhr");
	xhr.send();
	console.log("xhr sent");
}

function homeFunction(xhr) {
	console.log("in homeFunction xhr");
	let userInfo = JSON.parse(xhr.responseText);
	console.log(userInfo);
	// Split up JSON
	let user = userInfo.user;
	let userApps = userInfo.userApps;
	let reviewApps = userInfo.reviewApps;
	let reviewAppUsers = userInfo.reviewAppUsers;
	// Persist data in browser for curresnt session (ie will clear when browser
	// tab closes)
	// Check browser support
	if (typeof (Storage) != "undefined") { // Typeof returns type of object
		console.log("Storage supported");
		localStorage.setItem("User", JSON.stringify(user)); // Store name value
															// pair in browser
															// storage with JSON
															// of user
		localStorage.setItem("UserApps", JSON.stringify(userApps));
		localStorage.setItem("ReviewApps", JSON.stringify(reviewApps));
		localStorage.setItem("ReviewAppUsers", JSON.stringify(reviewAppUsers));
	} else {
		console.log("Storage not supported");
	}
	// Display Name
	document.getElementById("dropdownUser").innerHTML = (user.firstname + " " + user.lastname);
	// Display Reimbursement
	document.getElementById("availableReimbursement").innerHTML = (user.yearlyReimbursementRemaining);
	// Display Alerts
	if (user.hasUrgentEmail == true) {
		document.getElementById("hiddenEmailAlert").style.display = "block"; // Change
																				// css
																				// to
																				// display
																				// element
	}
	// Display Badges
	if (userApps.length > 0) {
		var currUserApps = 0;
		var pastUserApps = 0;
		for (i = 0; i < userApps.length; i++) {
			let app = userApps[i];
			if ((app.statusID == 0) || (app.statusID == 4)) { // Denied or
																// approved
				pastUserApps++;
			} else {
				currUserApps++;
			}
		}
		console.log(pastUserApps);
		console.log(currUserApps)
		if (currUserApps > 0) {
			document.getElementById("currAppBadge").style.display = 'inline';
			document.getElementById("currAppBadge").innerHTML = currUserApps;
		}
		if (pastUserApps > 0)
			document.getElementById("pastAppBadge").style.display = 'inline';
		document.getElementById("pastAppBadge").innerHTML = pastUserApps;
	}
	if (reviewApps.length > 0) {
		console.log(reviewApps);
		document.getElementById("currAppReviewBadge").style.display = "inline";
		document.getElementById("currAppReviewBadge").innerHTML = reviewApps.length;
	}
}
