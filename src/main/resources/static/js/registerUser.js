
function addUser(userName) {
    var firstName = $('#firstName').val();
    var lastName = $('#lastName').val();
    if (firstName && lastName && userName) {
        $.ajax(
            {
                type:"POST",
                url : "/Frienduler/newUser/" + userName,
                data: {
                    "firstName" : firstName,
                    "lastName" : lastName
                },
                parseError: true,
                success : function(result) {
                    alert("Successfully registered user");
                    location.replace("/Frienduler/user/" + userName);
                }, error : function(e, ex, exception) {
                    alert(e);
                    alert(ex);
                    alert(exception);
                    alert("ERROR");
            }
            }
        );
    } else {
        alert("First/Last name is missing");
    }
}



