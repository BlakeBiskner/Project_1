window.onload=function(){
    console.log("in User Applications");
    // Get session from storage
    if(typeof(Storage)!="undefined"){
        console.log("Storage supported");
        let user=JSON.parse(localStorage.getItem("User")); // Retrieves items from browser and converts to javascript
        let userApps=JSON.parse(localStorage.getItem("UserApps"));
        console.log(user);
        console.log(userApps);
        // Display name
        document.getElementById("dropdownUser").innerHTML=(user.firstname+" "+user.lastname);
        // Display Applications
        let appTable="";
        for(i=0;i<userApps.length;i++){
            console.log(userApps[i]);
            appTable+='<div class="card">';
            appTable+='<div class="card-header">';
            appTable+='<a class="collapsed card-link" data-toggle="collapse" href="#Collapse'+userApps[i].applicationID+'">'; // Use the applicationId as a link to collapse as it is unique for each application also added prefix Collapse so will label Collapse20 instead of just 20 (to be safe so reference string and not possible number)
            appTable+="<strong>"+userApps[i].eventTitle+"</strong>";
            appTable+="</a>";
            appTable+="</div>";
            appTable+='<div id="Collapse'+userApps[i].applicationID+'" class="collapse" data-parent="#appAccordian">';

            // Progress Bar
            appTable+='<div class="card-body">'
            appTable+='<strong>Application Progress</strong>';
            appTable+='</div>';
            appTable+='<div class="card-body">';
            appTable+='<div class="progress">'; // Add container element with progress class
            
            var barProgress=0;
            switch(userApps[i].statusID){
                case 1: // Submitted
                    barProgress=25;
                    break;
                case 2:
                    barProgress=50;
                    break;
                case 3:
                    barProgress=75;
                    break;
                case 4:
                    barProgress=100;
                    break;
                case 0:
                    barProgress=0;
                    break;
            }
            appTable+='<div class="progress-bar progress-bar-striped progress-bar-animated" style="width:'+barProgress+'%"></div>';
            appTable+='</div>';
            appTable+='</div>';
            appTable+='<div class="card-body">';
            appTable+='<p>'+userApps[i].status+'</p>';
            appTable+='</div>';

            // Application Info
            appTable+='<div class="card-body cardList">';
            appTable+='<div class="list-group list-group-flush">';
            appTable+='<li class="list-group-item border border-right-0 border-left-0"><strong>Application Information</strong></li>';
            appTable+='<li class="list-group-item list-group-item-light"><strong>Type</strong></li>';
            appTable+='<li class="list-group-item list-group-item-light">'+userApps[i].typeDescription+'</li>';
            appTable+='<li class="list-group-item list-group-item-light"><strong>Grading Format</strong></li>';
            appTable+='<li class="list-group-item list-group-item-light">'+userApps[i].gradeFormat+'</li>';
            appTable+='<li class="list-group-item list-group-item-light"><strong>Estimated Reimbursement</strong></li>';
            appTable+='<li class="list-group-item list-group-item-light">$'+(((0.01*userApps[i].typeCoverage)*userApps[i].cost))+'</li>';
            // Submit Grade Modal Reference 
            appTable+='<li class="list-group-item list-group-item-primary" align="center"><strong><a data-toggle="modal" href="#GradeModal'+userApps[i].applicationID+'">Submit Grade</a></strong></li>';
            appTable+="</div>";
            appTable+="</div>";
            appTable+="</div>";
            appTable+="</div>";

            // Grade Submission Modal
            appTable+='<div class="modal fade" id="GradeModal'+userApps[i].applicationID+'">';
            appTable+='<div class="modal-dialog modal-dialog-centered modal-lg">'; // Modal-lg makes a large modal
            appTable+='<div class="modal-content" align="center">';

            appTable+='<div class="modal-header">';
            appTable+='<h3 class="modal-title">Grade</h3>';
            appTable+='<button type="button" class="close" data-dismiss="modal">&times;</button>';
            appTable+='</div>';

            appTable+='<div class="modal-body">';
            appTable+='<form class="col-12" method="POST" action="Grade.do">';
            appTable+='<div class="form-row">';
            appTable+='<div class="form-group col-12">'
            appTable+='<label for="grade">Grade</label>';
            appTable+='<input type="text" class="form-control" id="grade" name="grade">';
            appTable+='</div>';
            appTable+='</div>';

            appTable+='<div class="form-row">';
            appTable+='<div class="form-group col-12">';
            appTable+='<input type="submit" class="form-control btn-primary" id="gradeButton">';
            
            // Hidden form element
            appTable+='<input type="text" name="gradeId" id="gradeId" value="'+userApps[i].applicationID+'" style="display:none">';
            
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