var stompClient = null;


function connect() {
    var socket = new SockJS('/email-campaign');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/stats', function (response) {
            stat = JSON.parse(response.body);
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

function askForStats() {
    stompClient.send("/app/stats", {}, JSON.stringify({'name': $("#name").val()}));
}


