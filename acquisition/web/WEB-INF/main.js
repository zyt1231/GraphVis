var App = function () {
    this.init();
};

App.prototype.visualize = function (json) {
    var nodes = new vis.DataSet(json['nodes']);
    var edges = new vis.DataSet(json['edges']);
    // create a Network
    var container = document.getElementById("canvasNetwork");
    // provide the data in the vis format
    var data = {
        nodes: nodes,
        edges: edges
    };
    var options = {
        layout: {
            improvedLayout: false
        }
    };
    // initialize your Network!
    var network = new vis.Network(container, data, options);
};

App.prototype.chart = function (json) {
    App.prototype.keywordArray= [];
    //header
    var row = ["Date"]
    row = row.concat(App.prototype.keywords);
    App.prototype.keywordArray.push(row);

    //rows
    for (var date in json) {
        var row = [date];

        var keywordsOnDateMap = json[date];
        App.prototype.keywords.forEach(function (item) {
            var ct = 0;
            //each keyword
            for (var keyword in keywordsOnDateMap) {
                // console.log(keyword + "|" + item.data + (keyword == item.data));
                if (keyword == item) {
                    ct = keywordsOnDateMap[keyword];
                    break;
                }
            }
            row.push(ct);
        })
        App.prototype.keywordArray.push(row);
    }


    $('#canvasChart').height(500);
    $('#canvasTable').hide();
    $('#canvasChart').show();
    $('#canvasNetwork').hide();

    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = google.visualization.arrayToDataTable(App.prototype.keywordArray);
        var options = {
            title: 'Keyword Frequency',
            curveType: 'function',
            width:1000,
            heigh:500,
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
        item['verticalDegree'] = 0;
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
    for (var key in nodeMap) {
        if ((nodeMap[key])['group'] == 'K') {
            kwMap[nodeMap[key]['label']] = nodeMap[key]['verticalDegree'];
        }
    }

    App.prototype.KeywordTable = sortOnValues(kwMap);
    //create checkbox list
    $.each(App.prototype.KeywordTable, function (i) {
        var listItem = $('<tr>').append($('<td><input type="checkbox">' + App.prototype.KeywordTable[i][0] + '</input></td><td><span class="badge">' + App.prototype.KeywordTable[i][1] + '</span></td>'));
        $('#nodeTable').append(listItem);
    });

    //add verticaldegree field to nodes
    for (i = 0; i < nodes.length; i++) {
        var id = nodes[i]['id'];
        nodes[i]['verticalDegree'] = nodeMap[id]['verticalDegree'];
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


    $("#dateBtn").click(function () {
        var fromDate = $("#datepickerfrom").val();
        var toDate = $("#datepickerto").val();
        $("#nodeTable").empty();
        $('#canvasTable').empty();
        App.prototype.json = {};
        App.prototype.jsonArticle = {};
        App.prototype.KEYWORDLIMIT = 5;
        App.prototype.KeywordTable = [];
        App.prototype.keywordArray= [];

        $.getJSON("http://localhost:8080/network?from=" + fromDate + "&to=" + toDate + "", function (json) {
            App.prototype.json = json;
            App.prototype.visualize(json);
            $('#canvasNetwork').height(500);
            $('#canvasNetwork').show();
            json = App.prototype.getKeywordMap(json);
            App.prototype.table(json);
        });
        $.getJSON("http://localhost:8080/articles?from=" + fromDate + "&to=" + toDate + "", function (json) {
            App.prototype.jsonArticle = json;
        });

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
    $("#keywordBtn").click(function () {
        App.prototype.keywords = [];
        var selectedKeywords = $("input:checked");
        for (var i = 0; i < Math.min(App.prototype.KEYWORDLIMIT, selectedKeywords.length); i++) {
            App.prototype.keywords.push(selectedKeywords[i].nextSibling.data);
        }
        App.prototype.chart(App.prototype.jsonArticle);
    })
};

//main
app = new App();


