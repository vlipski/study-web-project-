<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<jsp:include page="header.jsp"/>
<script>
    jQuery(function ($) {
        $('tbody tr[data-href]').addClass('clickable').click(function () {
            window.location = $(this).attr('data-href');
        });
    });
</script>
<div class="jumbotron">
    <div class="container">
        <div class="row">
            <div class="col-10">
                <h1>Devices</h1>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">serial number</th>
                    </tr>

                    </thead>
                    <tbody>
                    <c:forEach var="device" items="${devices.showDevicesDtoList}">
                        <tr class="even" data-href="${pageContext.request.contextPath}/user/showHistory/${device.id}"
                            title="Show device">
                            <td>${device.name}</td>
                            <td>${device.serialNumber}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="${pageContext.request.contextPath}/user/addDevice" class="btn btn-primary"
                   role="button">addDevice</a>
            </div>
            <div class="col-2">
                <c:if test="${devices.showSensorsDtoList != null}">

                    <form method="GET">
                        <h5>${devices.name}</h5>
                        <h6>List sensors</h6>
                        <c:forEach var="sensor" items="${devices.showSensorsDtoList}" varStatus="status">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="id"
                                       value="${String.join("amp&", sensor.name, String.valueOf(sensor.id))}"
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
                            <input type="date" name="from">
                        </div>
                        <div class="form-group">
                            <input type="date" name="to">
                            <button class="btn btn-primary my-2 my-sm-0" name="idDevice"
                                    formaction="${pageContext.request.contextPath}/user/chart"
                                    value=${devices.id} type="submit"><b>CHART</b></button>
                            <button class="btn btn-primary my-2 my-sm-0" name="idDevice"
                                    formaction="${pageContext.request.contextPath}/user/pdf"
                                    value=${devices.id} type="submit"><b>PDF</b></button>
                        </div>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>