
function onTypeValidate() {

    if(document.getElementById("newPassword").value==  document.getElementById("confirmPassword").value){
        document.getElementById("message").style.color = "green";
        document.getElementById("message").innerHTML = "Password matched";
        return true;
    }else {
        document.getElementById("message").style.color = "red";
        document.getElementById("message").innerHTML = "Password does not matched";
        return  false;
    }

}

