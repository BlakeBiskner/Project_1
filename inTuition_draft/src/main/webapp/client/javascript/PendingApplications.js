/*
 * Pending Application
 *
 * author Blake Biskner
 *
 */

window.onload=function(){
    console.log("in Pending Applications");
    // Get session from storage
    if(typeof(Storage)!="undefined"){
        console.log("Storage supported");
        let user=JSON.parse(localStorage.getItem("User")); // Retrieves items from browser and converts to javascript
        let reviewApps=JSON.parse(localStorage.getItem("ReviewApps"));
        let reviewAppUsers=JSON.parse(localStorage.getItem("ReviewAppUsers"));
        
        console.log(user);
        console.log(reviewApps);
        console.log(reviewAppUsers);
        // Display name
        document.getElementById("dropdownUser").innerHTML=(user.firstname+" "+user.lastname);
        // Display Applications
        let appTable="";
        for(i=0;i<reviewApps.length;i++){
            console.log("Application to review");
            console.log(reviewApps[i]);
            console.log("User to review");
            console.log(reviewAppUsers[i]);

            appTable+='<div class="card">';
            appTable+='<div class="card-header">';
            appTable+='<a class="collapsed card-link" data-toggle="collapse" href="#Collapse'+reviewApps[i].applicationID+'">';
            appTable+='<strong>'+reviewAppUsers[i].firstname+' '+reviewAppUsers[i].lastname+' Application for '+reviewApps[i].eventTitle+'</strong>';
            appTable+='</a>';
            appTable+='</div>';
            // Get Unique Application id
            appTable+='<div id="Collapse'+reviewApps[i].applicationID+'" class="collapse" data-parent="#appAccordian" onclick="getAppId('+reviewApps[i].applicationID+')">'//getAppId('+reviewApps[i].applicationID+')">';
            appTable+='<div class="card-body>';

            // // Get unique application id
            // var cardAppId='Collapse'+reviewApps[i].applicationID;
            // console.log(cardAppId);
            // document.getElementById(cardAppId).addEventListener("click",function(){getAppId(reviewApps[i].applicationID);});


            // Modal Links
            appTable+='<div class="list-group list-group-flush">';
            appTable+='<li class="list-group-item list-group-item-light"><a data-toggle="modal" href="#UserModal'+reviewAppUsers[i].userID+'">Employee Information</a>';
            appTable+='<li class="list-group-item list-group-item-light"><a data-toggle="modal" href="#AppModal'+reviewApps[i].applicationID+'">Application Information</a>';
            appTable+='<li class="list-group-item list-group-item-light"><a data-toggle="modal" href="#EmailModal'+reviewAppUsers[i].userID+'">Request More Information</a>';
            appTable+='<li class="list-group-item list-group-item-light"><a data-toggle="modal" href="#ActionModal'+reviewApps[i].applicationID+'">Approve or Deny Application</a>';
            appTable+='</div>';
            appTable+='</div>'; 
            appTable+='</div>';
            appTable+='</div>';

            // Employee Modal
            appTable+='<div class="modal fade" id="UserModal'+reviewAppUsers[i].userID+'">';
            appTable+='<div class="modal-dialog modal-dialog-centered modal-lg">'; // Modal-lg makes a large modal
            appTable+='<div class="modal-content" align="center">';

            appTable+='<div class="modal-header">';
            appTable+='<h3 class="modal-title">'+reviewAppUsers[i].firstname+' '+reviewAppUsers[i].lastname+'</h3>';
            appTable+='<button type="button" class="close" data-dismiss="modal">&times;</button>';
            appTable+='</div>';

            appTable+='<div class="modal-body"><img class="img" width="50%" src="../images/user.svg" alt="user"></div>'; // img-fluid applies max width and auto height to image max width changes width if content larger than max it auto changes height else it leaves it alone (ensures width is never greater than max width)
            appTable+='<div class="modal-header"><strong>Employee Department</strong></div>'; // modal-header adds a bottom border
            appTable+='<div class="modal-header"><p>'+reviewAppUsers[i].dept+'</p></div>';
            appTable+='<div class="modal-header"><strong>Employee Job</strong></div>';
            appTable+='<div class="modal-header"><p>'+reviewAppUsers[i].job+'</p></div>';
            appTable+='<div class="modal-header"><strong>Remaining Reimbursement</strong></div>';
            appTable+='<div class="modal-header"><p>$'+reviewAppUsers[i].yearlyReimbursementRemaining+'</p></div>';
            appTable+='<div class="modal-header"><strong>Employee Email</strong></div>';
            appTable+='<div class="modal-header"><p>'+reviewAppUsers[i].email+'</p></div>';
            appTable+='</div>';
            appTable+='</div>';
            appTable+='</div>';

            // Application Modal
            appTable+='<div class="modal fade" id="AppModal'+reviewApps[i].applicationID+'">';
            appTable+='<div class="modal-dialog modal-dialog-centered modal-lg" align="center">';
            appTable+='<div class="modal-content" align="center">';

            appTable+='<div class="modal-header">';
            appTable+='<h3 class="modal-title">'+reviewApps[i].eventTitle+'</h3>';
            appTable+='<button type="button" class="close" data-dismiss="modal">&times;</button>';
            appTable+='</div>';

            appTable+='<div class="modal-body"><img class="img" width="50%" src="../images/form.svg" alt="user"></div>'; // img-fluid applies max width and auto height to image max width changes width if content larger than max it auto changes height else it leaves it alone (ensures width is never greater than max width)
            appTable+='<div class="modal-header"><strong>Event Type</strong></div>';
            appTable+='<div class="modal-header"><p>'+reviewApps[i].typeDescription+'</p></div>';
            appTable+='<div class="modal-header"><strong>Event Cost Before Reimbursement</strong></div>';
            appTable+='<div class="modal-header"><p>$'+reviewApps[i].cost+'</p></div>';
            appTable+='<div class="modal-header"><strong>Event Reimbursement Coverage</strong></div>';
            appTable+='<div class="modal-header"><p>'+reviewApps[i].typeCoverage+'%</p></div>';
            appTable+='<div class="modal-header"><strong>Grading Format</strong></div>';
            appTable+='<div class="modal-header"><p>'+reviewApps[i].gradeFormat+'</p></div>';
            appTable+='<div class="modal-header"><strong>Passing Grade</strong></div>';
            appTable+='<div class="modal-header"><p>'+reviewApps[i].passingGrade+'</p></div>';
            appTable+='<div class="modal-header"><strong>Start Date</strong></div>';
            appTable+='<div class="modal-header"><p>'+reviewApps[i].date+'</p></div>';
            // Optional Fields
            if(reviewApps[i].eventEndDate!=null){
                appTable+='<div class="modal-header"><strong>Event End Date</strong></div>';
                appTable+='<div class="modal-header"><p>'+reviewApps[i].eventEndDate+'</p></div>';
            }
            if(reviewApps[i].timeMissed!=null){
                appTable+='<div class="modal-header"><strong>Work Time Missed</strong></div>';
                appTable+='<div class="modal-header"><p>'+reviewApps[i].timeMissed+' days</p></div>';
            }
            appTable+='<div class="modal-header"><strong>Justification</strong></div>';
            appTable+='<div class="modal-header"><p>'+reviewApps[i].justification+'</p></div>';
            appTable+='</div>';
            appTable+='</div>';
            appTable+='</div>';

            // Request More Info
            appTable+='<div class="modal fade" id="EmailModal'+reviewAppUsers[i].userID+'">';
            appTable+='<div class="modal-dialog modal-dialog-centered modal-lg">'; // Modal-lg makes a large modal
            appTable+='<div class="modal-content" align="center">';

            appTable+='<div class="modal-header">';
            appTable+='<h3 class="modal-title">Email</h3>';
            appTable+='<button type="button" class="close" data-dismiss="modal">&times;</button>';
            appTable+='</div>';

            appTable+='<div class="modal-body">';
            appTable+='<form class="col-12">';
            appTable+='<div class="form-row">';
            appTable+='<div class="form-group col-12">'
            appTable+='<label for="message">Message</label>';
            appTable+='<textarea name="message" class="form-control" id="message" rows="5"></textarea>';
            appTable+='</div>';
            appTable+='</div>';

            appTable+='<div class="form-row">';
            appTable+='<div class="form-group col-12">';
            appTable+='<label for="emailAddress">Select Email</label>';
            appTable+='<select id="emailAddress" class="form-control" name="emailAddress">'
            appTable+='<option value="'+reviewAppUsers[i].email+'">'+reviewAppUsers[i].email+'</option>';
            appTable+='<option value="'+reviewAppUsers[i].dsEmail+'">'+reviewAppUsers[i].dsEmail+'</option>';
            appTable+='</select>';
            appTable+='</div>';
            appTable+='</div>';

            appTable+='<div class="form-row">';
            appTable+='<div class="form-group col-12">';
            appTable+='<input type="submit" class="form-control btn-primary" id=emailButton">';
            appTable+='</div>';
            appTable+='</div>';
            appTable+='</form>';

            appTable+='</div>';
            appTable+='</div>';
            appTable+='</div>';
            appTable+='</div>';

            // Application Action
            appTable+='<div class="modal fade" id="ActionModal'+reviewApps[i].applicationID+'">';
            appTable+='<div class="modal-dialog modal-dialog-centered modal-lg">'; // Modal-lg makes a large modal
            appTable+='<div class="modal-content" align="center">';

            appTable+='<div class="modal-header">';
            // Set name and id of this attribute approveId to change to unique app id which I can get later
            appTable+='<h3 class="modal-title" name="approveId" id="approveId">Application Actions</h3>';
            appTable+='<button type="button" class="close" data-dismiss="modal">&times;</button>';
            appTable+='</div>';

            appTable+='<div class="modal-body">';
            appTable+='<form class="col-12">';
            appTable+='<div class="form-row">';
            appTable+='<div class="form-group col-12">';
            appTable+='<strong>Approve</strong>';
            appTable+='</div>';
            appTable+='</div>';
            appTable+='<div class="form-row">';
            appTable+='<div class="form-group col-12">';
            appTable+='<input type="submit" class="form-control btn-success" id="approveButton">';
            appTable+='</div>';
            appTable+='</div>';
            appTable+='</form>';

            appTable+='<form class="col-12">';
            appTable+='<div class="form-row">';
            appTable+='<div class="form-group col-12">';
            appTable+='<strong>Deny</strong>';
            appTable+='</div>';
            appTable+='</div>';
            appTable+='<div class="form-row">';
            appTable+='<div class="form-group col-12">';
            appTable+='<input type="submit" class="form-control btn-danger" id="approveButton">';
            appTable+='</div>';
            appTable+='</div>';
            appTable+='</form>';

            appTable+='</div>';
            appTable+='</div>';
            appTable+='</div>';
            appTable+='</div>';
            
        }

        console.log(appTable);
        document.getElementById("appAccordian").innerHTML=appTable;

        // Clear data for next user
        localStorage.removeItem("User");
        localStorage.removeItem("UserApps");
        localStorage.removeItem("ReviewApps");
        localStorage.removeItem("ReviewAppUsers");
    }
}

// Handler Functions
function getAppId(appId){
    console.log(appId);
    document.getElementById("approveId").value=appId;
    console.log("Value of approveId is "+document.getElementById("approveId").value);
}