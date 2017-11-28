
function addFriend(userName) {

    var friendName = $('#input_friend_id').val();


    if (userName && friendName) {
        $.ajax(
            {
                type : "POST",
                url  : "/Frienduler/user/" + userName + "/addFriend/" + friendName,
                data : {
                },
                success : function(result) {
                    location.reload();
                },
                error: function (jqXHR, exception, ex) {
                    alert(exception);
                    alert(ex);
                    alert("Failed to add the friend. Please check the inputs.");
                }
            });
    } else {
        alert("Friend Name is missing");
    }
}

function removeFriend(userName) {

    var friendName = $('#input_friend_id_remove').val();


    if (userName && friendName) {
        $.ajax(
            {
                type : "DELETE",
                url  : "/Frienduler/user/" + userName + "/" + friendName,
                data : {
                },
                success : function(result) {
                    // alert(result);
                    location.reload();
                }
                // error: function (jqXHR, exception, ex) {
                //     alert(exception);
                //     alert(ex);
                //     alert("Failed to remove the friend. Please check the inputs.");
                //     location.reload();
                // }
            });
    } else {
        alert("Friend Name is missing");
    }
}

function addGroup() {
    var groupName = $('#add_group_input').val();
    if (groupName) {
        $.ajax(
            {
                type:"POST",
                url : "/Frienduler/group/" + groupName,
                data: {

                },
                success : function(result) {
                    location.reload();
                }, error: function(jqXHR, exception, ex) {
                    alert("Failed to add group, please check inputs");
                    location.reload();
                }
            }
        );
    } else {
        alert("Group name is missing");
    }
}

function addFriendToGroup() {
    var groupName = $('#group_select').val();
    var friendName = $('#friend_id_add_group').val();
    // console.log("Running addFriendToGroup");
    if (groupName && friendName) {
        $.ajax(
            {
                type:"POST",
                url : "/Frienduler/group/" + groupName + "/" + friendName,
                data: {

                },
                success : function(result) {
                    location.reload();
                }, error: function(jqXHR, exception, ex) {
                    console.log(exception);
                    console.log(ex);
                alert("Failed to add user to group, please check inputs");
                location.reload();
            }
            }
        );
    } else {
        alert("Group/Friend name is missing");
    }
}

function addSelfToGroup(userName) {
    var groupName = $('#group_select').val();
    // console.log("running addSelfToGroup");
    if (groupName && userName) {
        $.ajax(
            {
                type:"POST",
                url : "/Frienduler/group/" + groupName + "/" + userName,
                data: {

                },
                success : function(result) {
                    location.reload();
                }, error: function(jqXHR, exception, ex) {
                alert("Failed to add user to group, please check inputs");
                location.reload();
            }
            }
        );
    } else {
        alert("Group/Friend name is missing");
    }
}

function removeFromGroup() {
    var groupName = $('#group_select_remove').val();
    var userName = $('#friend_id_remove_from_group').val();
    // console.log("Running removeFromGroup");
    if (groupName && userName) {
        $.ajax(
            {
                type:"DELETE",
                url : "/Frienduler/group/" + groupName + "/" + userName,
                data: {

                },
                success : function(result) {
                    alert(result);
                    location.reload();
                }, error: function(jqXHR, exception, ex) {
                console.log(exception);
                console.log(ex);
                alert("Failed to remove user from group, please check inputs");
                location.reload();
                }
            }
        );
    } else {
        alert("Group/Member name is missing");
    }
}
