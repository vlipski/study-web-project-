<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header.jsp"/>

<div class="jumbotron">
    <div class="container">
        <form:form method="POST" action="${pageContext.request.contextPath}/signUp" modelAttribute="userSignUpCmd">
            <legend>Fill in all fields</legend>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">name</label>
                <div class="col-sm-4">
                    <form:errors path="name" cssClass="error"/>
                    <input type="text" class="form-control" name="name" maxlength="25" value="${userSignUpCmd.name}"
                           placeholder="name">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">login</label>
                <div class="col-sm-4">
                    <form:errors path="login" cssClass="error"/>
                    <div class="error">${error}</div>
                    <input type="text" class="form-control" name="login" maxlength="20" value="${userSignUpCmd.login}"
                           placeholder="login">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">password</label>
                <div class="col-sm-4">
                    <form:errors path="password" cssClass="error"/>
                    <input type="password" class="form-control" name="password" maxlength="20"
                           placeholder="password">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">repeat password</label>
                <div class="col-sm-4">
                    <form:errors path="repeatPassword" cssClass="error"/>
                    <input type="password" class="form-control" name="repeatPassword" maxlength="20"
                           placeholder="repeat password">
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-10">
                    <button type="submit" class="btn btn-primary">signUp</button>
                </div>
            </div>
        </form:form>
    </div>
</div>

<jsp:include page="footer.jsp"/>