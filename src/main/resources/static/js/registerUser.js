
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
                location.replace("/Frienduler/user/" + userName);
            }
            }
        );
    } else {
        alert("First/Last name is missing");
    }
}



