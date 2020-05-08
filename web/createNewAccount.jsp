<%-- 
    Document   : createNewAccount
    Created on : Mar 24, 2020, 11:15:22 PM
    Author     : nhocc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="create_account" method="POST">
            <c:set var="errors" value="${requestScope.CREATEERRORS}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" /> (6 - 30 chars)<br/>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                ${errors.usernameLengthError}
                </font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" /> (6 - 20 chars) <br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                ${errors.passwordLengthError}
                </font><br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /> <br/>
            <c:if test="${not empty errors.confirmNotMatchError}">
                <font color="red">
                ${errors.confirmNotMatchError}
                </font><br/>
            </c:if>
            Full name* <input type="text" name="txtFullname" value="${param.txtFullname}" /> (2 - 50 chars)<br/>
            <c:if test="${not empty errors.fullnameLengthError}">
                <font color="red">
                ${errors.fullnameLengthError}
                </font><br/>
            </c:if>
            <input type="submit" value="Create New Account" name="btnAction" />
            <input type="reset" value="Reset" />
        </form><br/>
        <c:if test="${not empty errors.usernameIsExisted}">
            <font color="red">
            ${errors.usernameIsExisted}
            </font><br/>
        </c:if>
    </body>
</html>
