<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<jsp:include page="header.jsp"/>
<script>
    jQuery(function ($) {
        window.setTimeout(function () {
            $('#my-alert').alert('close');
        }, 1000);
    });

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
                        <tr class="even" data-href="${pageContext.request.contextPath}/user/myDevices/${device.id}"
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
                <c:if test="${devices.sendCmd != null}">
                    <c:set var="sendCmd" scope="page" value="${devices.sendCmd}"/>

                    <form method="get" action="${pageContext.request.contextPath}/user/myDevices/change">
                        <fieldset>
                            <legend><h3>${devices.name}</h3></legend>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="inputPassword4">temp</label>
                                    <input type="number" min="10" max="30" value="${sendCmd.temp}"
                                           name="temp" class="form-control" id="inputPassword4">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="inputEmail4">power</label>
                                    <input type="number" min="0" max="10" value="${sendCmd.power}"
                                           name="power" class="form-control" id="inputEmail4">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary" name="idDevice" value="${devices.id}">apply
                            </button>
                        </fieldset>
                    </form>
                </c:if>
                <c:if test="${devices.sendCmd == null}">
                    <c:if test="${devices.name != null}">
                        <div class="error">${devices.name} is disable</div>
                    </c:if>
                </c:if>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>