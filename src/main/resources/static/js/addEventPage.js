function addEvent(userName) {

    var eventName = $('#eventName').val();
    var startDate = $('#startDate').val();
    var startTime = $('#startTime').val();
    var endDate = $('#endDate').val();
    var endTime = $('#endTime').val();


     if (userName && startDate && startTime && endDate && endTime) {
        $.ajax(
            {
                type : "POST",
                url  : "/Frienduler/user/" + userName + "/event/" + eventName,
                data : {
                    "startDate" : startDate,
                    "startTime" : startTime,
                    "endDate" : endDate,
                    "endTime" : endTime
                },
                success : function(result) {
                    alert("Event added successfully");
                    location.reload();
                }
                // error: function (jqXHR, exception, ex) {
                //     alert(exception);
                //     alert(ex);
                //     alert("Failed to add the event. Please check the inputs.");
                // }
            });
    } else {
        alert("One or more fields are missing!");
    }
}

function deleteEvent(userName, eventName) {

    if (userName && eventName) {

        $.ajax(
            {
                type : "DELETE",
                url  : "/Frienduler/user/" + userName + "/event/" + eventName,
                data : {
                },
                success : function(result) {
                    alert("Event deleted successfully");
                    location.reload();
                },
                error: function (jqXHR, exception, ex) {
                    console.log(exception);
                    console.log(ex);
                    alert("Failed to delete the event.");
                }
            });
    } else {
        alert("Invalid User/Event Id");
    }
}
