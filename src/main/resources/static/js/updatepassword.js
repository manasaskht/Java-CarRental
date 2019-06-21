
function onTypeValidate() {

    if(document.getElementById("newPassword").value==  document.getElementById("confirmPassword").value){
        document.getElementById("message").style.color = "green";
        document.getElementById("message").innerHTML = "Matched";
        return true;
    }else {
        document.getElementById("message").style.color = "red";
        document.getElementById("message").innerHTML = "Not Matched";
        return  false;
    }

}

