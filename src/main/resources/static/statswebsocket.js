var stompClient = null;

function connect() {
    var socket = new SockJS('/email-campaign');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/stats', function (response) {
            stat = JSON.parse(response.body);
             tr = document.getElementById(stat.uuid);
             tds = tr.getElementsByTagName("td");
             tds[4].innerText = stat.totalOpened;
             tds[5].innerText = stat.totalClicked;
        });
    });
}