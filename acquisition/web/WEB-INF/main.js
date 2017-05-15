var App = function () {
    this.init();
};

App.prototype.visualize = function (json) {
    // $('#canvasNetwork').height(500);
    // $('#canvasChart').height(0);
    // $('#canvasTable').height(0);
    var nodes = new vis.DataSet(json['nodes']);
    var edges = new vis.DataSet(json['edges']);
    // create a Network
    var container = document.getElementById("canvasNetwork");
    // provide the data in the vis format
    var data = {
        nodes: nodes,
        edges: edges
    };
    var options = {};
    // initialize your Network!
    var network = new vis.Network(container, data, options);
};

App.prototype.chart = function () {
    $('#canvasChart').height(500);
    $('#canvasChart').hide();
    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Date', 'Trump, Donald J', 'Syria', 'United States Politics and Government'],
            ['2017-01-01', 8, 1, 9],
            ['2017-01-02', 9, 2, 4],
            ['2017-01-03', 15, 3, 10],
            ['2017-01-04', 4, 1, 5]
        ]);

        var options = {
            title: 'Keyword Frequency',
            curveType: 'function',
            legend: {position: 'bottom'}
        };

        var chart = new google.visualization.LineChart(document.getElementById('canvasChart'));

        chart.draw(data, options);
    }
};

App.prototype.getKeywordMap = function (json) {
    var edges = json['edges'];
    var nodes = json['nodes'];
    var nodeMap = new Object();
    var edgeMap = new Object();

    //build node map
    nodes.forEach(function (item) {
        item['verticalDegree']=0;
        nodeMap[item['id']] = item;
    });
    var add = function (key) {
        if (key != null) {
            (nodeMap[key])['verticalDegree'] += 1;
        }
    };
    //count vertical degree
    edges.forEach(function (item) {
        var fromId = item['from'];
        var toId = item['to'];
        add(fromId);
        add(toId);
    });
    //keyword map
    var kwMap = new Object();
    for(var key in nodeMap){
        if((nodeMap[key])['group']=='K'){
            kwMap[nodeMap[key]['label']] = nodeMap[key]['verticalDegree'];
        }
    }

    App.prototype.KeywordTable = sortOnValues(kwMap);
    //create checkbox list
    $.each(App.prototype.KeywordTable, function (i) {
        var listItem = $('<tr>').append($('<td><input type="checkbox">'+App.prototype.KeywordTable[i][0]+'</input></td><td><span class="badge">'+App.prototype.KeywordTable[i][1]+'</span></td>'));
        $('#nodeTable').append(listItem);
    });

    //add verticaldegree field to nodes
    for(i=0;i<nodes.length;i++){
        var id = nodes[i]['id'];
        nodes[i]['verticalDegree']=nodeMap[id]['verticalDegree'];
    }
    json['nodes'] = nodes;
    return json;
};

App.prototype.table = function (json) {
    $('#canvasTable').hide();
    $('#canvasTable').append($('<table id="nwTable" class="table"><thead><tr class="bg-primary"><th>Label</th><th>Type</th><th>VerticalDegree</th></tr></thead><tbody></tbody></table>'))
    $('#nwTable').dynatable({
        dataset: {
            records: json['nodes']
        }
    });
};

App.prototype.init = function () {
    $("#datepickerfrom").datepicker();
    $("#datepickerto").datepicker();

    $.getJSON("http://localhost:8080/network?from=2017-01-01&to=2017-01-03", function (json) {
        App.prototype.json = json;
        App.prototype.visualize(json);
        json = App.prototype.getKeywordMap(json);
        App.prototype.chart();
        App.prototype.table(json);

    });
    $("#visualizationBtn").click(function () {
        $('#canvasNetwork').show();
        $('#canvasChart').hide();
        $('#canvasTable').hide();
    });
    $("#chartBtn").click(function () {
        $('#canvasChart').height(500);
        $('#canvasTable').hide();
        $('#canvasChart').show();
        $('#canvasNetwork').hide();
    });
    $("#tableBtn").click(function () {
        $('#canvasTable').show();
        $('#canvasChart').hide();
        $('#canvasNetwork').hide();
    });
};

//main
app = new App();


