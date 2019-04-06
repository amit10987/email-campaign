$(function () {
    var connectPromise = null;
    var stompClient = null;

     $("#loadDataId").on("click", function(){
        $("#loader").show();
          $.ajax({
              url: '/loadData',
              type: 'POST',
              success: function (response) {
                  $("#loader").hide();
              }
          });
     });

    var graphColumns = [];
    function updateGraph(templateList) {
        const data = [];

        templateList.forEach(function (template) {
             const categories = ['totalClicked', 'totalDelivered', 'totalFailed', 'totalOpened'];
             categories.forEach(function (category) {
                 var existingData = data.find(function(e){
                     return e.group_name === `${template.campaignName}:${template.templateName}` && e.name === category;
                 });
                 if(!existingData){
                    existingData = {
                        group_name: `${template.campaignName}:${template.templateName}`,
                        name: category,
                        value: 0,
                    };
                    data.push(existingData);
                 }

                 existingData.value += template[category];
             });
         });

        $('#chtAnimatedBarChart').empty();

        $('#chtAnimatedBarChart').animatedBarChart({
            data: data,
            show_legend: true
        });
    }

    function connect() {
        return new Promise(function (resolve, reject) {
            var socket = new SockJS('/email-campaign');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                resolve();
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/homeStats', function (response) {
                    stats = JSON.parse(response.body);
                    updateGraph(stats);
                    console.log(stats)
                });
            });
        });
    }

    connectPromise = connect();

    function askForStats() {
        connectPromise.then(function () {
            stompClient.send("/app/stats", {});
        });
    }

    askForStats();
});
