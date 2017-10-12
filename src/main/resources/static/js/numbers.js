/**
 * Created by Connor on 10/12/2017.
 */

function addNumber() {

    var number = $('#input_number').val();

    if (number) {
        $.ajax(
            {
                type : "POST",
                url  : "/cs480/addNumber?number=" + number,
                success : function(result) {
                    location.reload();
                },
                error: function (jqXHR, exception) {
                    alert("Failed to add the number. Please check the inputs.");
                }
            });
    } else {
        alert("Invalid number");
    }
}

function getAverage() {
        $.ajax(
            {
                type : "GET",
                url  : "/cs480/getAverage",
                data : {
                },
                success : function(result) {
                    $('#id_average').text(result);
                },
                error: function (jqXHR, exception) {
                    alert("Failed to get the average.");
                }
            });
}
