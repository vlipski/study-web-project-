<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>

<div class="jumbotron">
    <div class="container">
        <form:form method="POST" action="${pageContext.request.contextPath}/user/addDevice" modelAttribute="deviceCmd">
            <legend>Fill in all fields</legend>
            <div class="error">${error}</div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">name device</label>
                <div class="col-sm-4">
                    <form:errors path="name" cssClass="error"/>
                    <input type="text" class="form-control" name="name" maxlength="25" value="${deviceCmd.name}"
                           placeholder="name device">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">serial number</label>
                <div class="col-sm-4">
                    <form:errors path="serialNumber" cssClass="error"/>
                    <input type="text" class="form-control" name="serialNumber" maxlength="20"
                           value="${deviceCmd.serialNumber}"
                           placeholder="serial number">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">pin cod</label>
                <div class="col-sm-4">
                    <form:errors path="pinCod" cssClass="error"/>
                    <input type="text" class="form-control" name="pinCod" maxlength="20" value="${deviceCmd.pinCod}"
                           placeholder="pin cod">
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-10">
                    <button type="submit" class="btn btn-primary">send</button>
                </div>
            </div>
        </form:form>
    </div>
</div>

<jsp:include page="footer.jsp"/>