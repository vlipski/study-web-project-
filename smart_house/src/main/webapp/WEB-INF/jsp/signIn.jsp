<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"/>
<div class="jumbotron">
    <div class="container">
        <form method="POST" action="${pageContext.request.contextPath}/loginCheck">
            <c:if test= "${not empty param.error}">
                <div class="error">Invalid login or password</div>
            </c:if>
            <legend>Enter login and password</legend>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">login</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="login" maxlength="20"
                           placeholder="login">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">password</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" name="password" maxlength="20"
                           placeholder="password">
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-10">
                    <button type="submit" class="btn btn-primary">signIn</button>
                </div>
            </div>
        </form>
    </div>
</div>

<jsp:include page="footer.jsp"/>