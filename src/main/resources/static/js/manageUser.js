
function updateUser(userName) {
    var firstName = $('#firstName').val();
    var lastName = $('#lastName').val();
    if (firstName && lastName && userName) {
        $.ajax(
            {
                type:"POST",
                url : "/Frienduler/user/" + userName,
                data: {
                    "firstName" : firstName,
                    "lastName" : lastName
                },
                parseError: true,
                success : function(result) {
                    alert("Successfully updated user");
                    location.reload();
                }
            //     , error: function(e, f, g) {
            //         // var obj = jQuery.parseJSON(g);
            //         //
            //         // for ( a in obj ){
            //         //     alert(a);
            //         // }
            //         console.log(e);
            //         console.log(f);
            //         console.log(g);
            //     alert("Failed to update user, please check inputs");
            //     location.reload();
            // }
            }
        );
    } else {
        alert("First/Last name is missing");
    }
}



