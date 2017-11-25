// This is the version used for regular HTML + FreeMarker with jQuery

function healthCheck() {
	$.ajax(
			{
				type : "GET",
				url  : "/cs480/ping",
				data : {
				},
				success : function(result) {
					$('#status').text(result);
				},
				error: function (jqXHR, exception) {
					$('#status').text("Failed to get the status");
				}
			});
}


function addEvent() {

    var eventName = $('#input_event_name').val();
    var userName = $('#input_user_id').val();
    var startDate = $('#input_start_date').val();
    var startTime = $('#input_start_time').val();
    var endDate = $('#input_end_date').val();
    var endTime = $('#input_end_time').val();


    if (userName) {
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


function deleteEvent(userId, eventId) {
	console.log(userId);
	console.log(eventId);
    $.ajax(
        {
            type : "DELETE",
            url  : "/Frienduler/user/" + userId + "/event/" + eventId,
            data : {
            },
            success : function(result) {
                location.reload();
            },
            error: function (jqXHR, exception) {
                alert("Failed to delete the event.");
            }
        });
}



function deleteUser(userId) {
	$.ajax(
			{
				type : "DELETE",
				url  : "/cs480/user/" + userId,
				data : {
				},
				success : function(result) {
					location.reload();
				},
				error: function (jqXHR, exception) {
					alert("Failed to delete the photo.");
				}
			});
}

function addUser() {

	var userId = $('#input_id').val();
	var userFirstName = $('#input_first_name').val();
	var userLastName = $('#input_last_name').val();

	if (userId) {
		$.ajax(
				{
					type : "POST",
					url  : "/cs480/user/" + userId,
					data : {
						"firstName" : userFirstName,
						"lastName" : userLastName
					},
					success : function(result) {
						location.reload();
					},
					error: function (jqXHR, exception) {
						alert("Failed to add the user. Please check the inputs.");
					}
				});
	} else {
		alert("Invalid user Id");
	}
}

function getUser(userId) {
	var userId = $('#query_id').val();
	if (userId) {
		$.ajax(
				{
					type : "GET",
					url  : "/cs480/user/" + userId,
					data : {
					},
					success : function(result) {
						$('#result_id').text(result.id);
						$('#result_first_name').text(result.firstName);
						$('#result_last_name').text(result.lastName);},
					error: function (jqXHR, exception) {
						alert("Failed to get the user.");
					}
				});
	} else {
		alert("Invalid user Id");
	}
}