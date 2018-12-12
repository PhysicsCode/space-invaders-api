var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
}

function setActivated(activated) {
    $("#activateHack").prop("disabled", activated);
    $("#deactivateHack").prop("disabled", !activated);
}

function connect() {
    var socket = new SockJS('/web-boquete');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/instruction', function (instruction) {
            showGreeting(instruction.body);
        });
        stompClient.subscribe('/topic/map', function (map) {
            showMap(map.body);
        });
        stompClient.subscribe('/topic/activator', function (active) {
            showActive(active.body);
            setActivated(active.body == "true");
        });
        stompClient.subscribe('/topic/fire', function (fire) {
            if (fire.body == "true") {
                activateFireButtons();
            } else {
                deactivateFireButtons();
            }
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function activateHack() {

    $.ajax({url: "/hack/activator", type: "post", data: "true", contentType: 'text/html'});
}

function deactivateHack() {

    $.ajax({url: "/hack/activator", type: "post", data: "false", contentType: 'text/html'});
}

function showGreeting(message) {
    $("#activeInstruction").html("<tr><td>" + message + "</td></tr>");
}

function showMap(message) {
    $("#realTimeMap").html(message);
}

function showActive(active) {
    $("#isActive").html(active);
}

function activateFireButtons() {
    $(".fire-b").prop("disabled", false);
}

function deactivateFireButtons() {
    $(".fire-b").prop("disabled", true);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#activateHack" ).click(function() { activateHack(); });
    $( "#deactivateHack" ).click(function() { deactivateHack(); });
});