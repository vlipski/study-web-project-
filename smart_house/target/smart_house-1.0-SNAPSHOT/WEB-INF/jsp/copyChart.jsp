function drawChart() {
var data = google.visualization.arrayToDataTable([
['Date', 'Power', 'Temperature'],
<c:forEach var="signal" items="${signals}" varStatus="status">
    [${signal.date}, ${signal.power}, ${signal.temp}],
</c:forEach>
])
;
function drawChart() {
var data = google.visualization.arrayToDataTable([
['Date', 'Power', 'Temperature'],
<c:forEach var="signal" items="${signals}" varStatus="status">
    [${signal.values.get(0)}, ${Integer.valueOf(signal.values.get(1))}, ${Integer.valueOf(signal.values.get(2))}],
</c:forEach>
])
;
function drawChart() {
var data = google.visualization.arrayToDataTable([
['Date'
<c:forEach var="metaData" items="${dataChart.metaData}" varStatus="status">
    ,'${metaData}'
</c:forEach>
],
<c:forEach var="sensorValue" items="${dataChart.sensorValueDtoList}" varStatus="status">
    ['${sensorValue.date}'
    <c:forEach var="value" items="${sensorValue.values}" varStatus="status">
        ,${value}
    </c:forEach>
    ],
</c:forEach>
]);
var options = {
focusTarget: 'category',
title: 'The charts temperature and power',
vAxis: {title: 'Accumulated Rating', ticks: [5, 10, 15, 20, 25, 30]},
isStacked: false
};

var chart = new google.visualization.SteppedAreaChart(document.getElementById('chart_div'));

chart.draw(data, options);
}

function drawChart() {
var data = google.visualization.arrayToDataTable([
['Year'
<c:forEach var="metaData" items="${dataChart.metaData}" varStatus="status">
    ,'${metaData}'
</c:forEach>
],
<c:forEach var="sensorValue" items="${dataChart.sensorValueDtoList}" varStatus="status">
    ['${sensorValue.date}'
    <c:forEach var="value" items="${sensorValue.values}" varStatus="status">
        ,${value}
    </c:forEach>
    ],
</c:forEach>
]);

var options = {
focusTarget: 'category',
title: 'Company Performance',
hAxis: {title: 'Year',  titleTextStyle: {color: '#333'}},
vAxis: {minValue: 0}
};

var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
chart.draw(data, options);
}