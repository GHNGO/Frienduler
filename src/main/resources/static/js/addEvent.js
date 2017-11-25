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
                },
                error: function (jqXHR, exception) {
                    alert("Failed to add the event. Please check the inputs.");
                }
            });
    } else {
        alert("Invalid user Id");
    }
}