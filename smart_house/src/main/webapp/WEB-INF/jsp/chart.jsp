<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Date'
                <c:forEach var="metaData" items="${dataChart.metaData}" varStatus="status">
                , '${metaData}'
                </c:forEach>
            ],
            <c:forEach var="sensorValue" items="${dataChart.sensorValueDtoList}" varStatus="status">
            <c:set var="date" scope="page" value="${sensorValue.date}" />
            [new Date(${date.get(1)}, ${date.get(2)}, ${date.get(5)}, ${date.get(11)}, ${date.get(12)})
                <c:forEach var="value" items="${sensorValue.values}" varStatus="status">
                , ${value}
                </c:forEach>
            ],
            </c:forEach>
        ]);
        var options = {
            focusTarget: 'category',
            title: '${dataChart.device.name}',
            hAxis: {title: 'Date', titleTextStyle: {color: '#333'}},
            vAxis: {minValue: 0}
        };
        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
        chart.draw(data, options);
    }
</script>

<div class="jumbotron">
    <div class="container">
        <div class="row">
            <div class="col-10">
                <h1>Chart</h1>
                <div id="chart_div" style="width: 900px; height: 500px;"></div>
            </div>
            <div class="col-2">
                <form method="GET">
                    <h6>List sensors</h6>
                    <c:set var="metaData" scope="page" value="${dataChart.metaData}"/>
                    <c:forEach var="sensor" items="${dataChart.device.sensors}" varStatus="status">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" name="id"
                                   value="${String.join("amp&", sensor.name, String.valueOf(sensor.id))}"
                            <c:if test="${metaData.contains(sensor.name)}"> checked </c:if>
                                   id="defaultCheck1">
                            <label class="form-check-label" for="defaultCheck1">
                                    ${sensor.name}
                            </label>
                        </div>
                    </c:forEach>
                    <div class="form-group">
                        <label>
                            Chose date:
                        </label>
                        <input type="date" name="from" value="${dataChart.from}">
                    </div>
                    <div class="form-group">
                        <input type="date" name="to" value="${dataChart.to}">
                        <button class="btn btn-primary my-2 my-sm-0" name="idDevice"
                                formaction="${pageContext.request.contextPath}/user/chart"
                                value=${dataChart.device.id} type="submit"><b>CHART</b></button>
                        <button class="btn btn-primary my-2 my-sm-0" name="idDevice"
                                formaction="${pageContext.request.contextPath}/user/pdf"
                                value=${dataChart.device.id} type="submit"><b>PDF</b></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>