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
            if((userApps[i].statusID==0)||(userApps[i].statusID==4))
            {
                console.log(userApps[i]);
                appTable+='<div class="card">';
                appTable+='<div class="card-header">';
                appTable+='<a class="collapsed card-link" data-toggle="collapse" href="#Collapse'+userApps[i].applicationID+'">'; // Use the applicationId as a link to collapse as it is unique for each application also added prefix Collapse so will label Collapse20 instead of just 20 (to be safe so reference string and not possible number)
                appTable+="<strong>"+userApps[i].eventTitle+"</strong>";
                appTable+="</a>";
                appTable+="</div>";
                appTable+='<div id="Collapse'+userApps[i].applicationID+'" class="collapse" data-parent="#appAccordian">';

                // Application Info
                appTable+='<div class="card-body cardList">';
                

                appTable+='<div class="list-group list-group-flush">';

                appTable+='<li class="list-group-item border border-right-0 border-left-0"><strong>Application Information</strong></li>';
                // Approved
                if(userApps[i].statusID==4){
                    appTable+='<li class="list-group-item list-group-item-success"><strong>Status</strong></li>';
                    appTable+='<li class="list-group-item list-group-item-success">'+userApps[i].status+'</li>';
                // Denied
                } else if(userApps[i].statusID==0){
                    appTable+='<li class="list-group-item list-group-item-danger"><strong>Status</strong></li>';
                    appTable+='<li class="list-group-item list-group-item-danger">'+userApps[i].status+'</li>';
                } else{
                }
                appTable+='<li class="list-group-item list-group-item-light"><strong>Type</strong></li>';
                appTable+='<li class="list-group-item list-group-item-light">'+userApps[i].typeDescription+'</li>';
                appTable+='<li class="list-group-item list-group-item-light"><strong>Grading Format</strong></li>';
                appTable+='<li class="list-group-item list-group-item-light">'+userApps[i].gradeFormat+'</li>';
                // Show Reimbursement if Rewarded
                if(userApps[i].statusID==4){
                    appTable+='<li class="list-group-item list-group-item-light"><strong>Awarded Reimbursement</li>';
                    appTable+='<li class="list-group-item list-group-item-light">$'+(((0.01*userApps[i].typeCoverage)*userApps[i].cost))+'</li>';
                }
                appTable+="</div>";
                appTable+="</div>";
                appTable+="</div>";
                appTable+="</div>";
            }
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