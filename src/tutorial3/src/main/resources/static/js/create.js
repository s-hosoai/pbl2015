$(document).ready(function() {
	update();
	setInterval("update()", 1000);
});

function update(){
    $.ajax({
        url: "http://localhost:8080/light"
    }).then(function(data) {
       $('#light_sensor_value').text(data.value);
    });
}

function forward(){
	$.ajax({
        url: "http://localhost:8080/create",
        data: "action=forward"
    });
}
function back(){
	$.ajax({
        url: "http://localhost:8080/create",
        data: "action=back"
    });
}
function left(){
	$.ajax({
        url: "http://localhost:8080/create",
        data: "action=left"
    });
}
function right(){
	$.ajax({
        url: "http://localhost:8080/create",
        data: "action=right"
    });
}
function stop(){
	$.ajax({
        url: "http://localhost:8080/create",
        data: "action=stop"
    });
}
