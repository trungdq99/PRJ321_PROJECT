<%-- 
    Document   : search
    Created on : Mar 23, 2020, 8:46:44 PM
    Author     : nhocc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.USER.lastname}
        </font>
        <button><a href="logout" style="text-decoration: none;">Logout</a></button>
        <h1>SEARCH PAGE</h1>
        <form action="search_lastname">
            Search Value: <input type="text" name="txtSearch" 
                                 value="${param.txtSearch}" /><br/>
            <input type="submit" value="Search" />
        </form><br/>
        <c:set var="searchValue" value="${param.txtSearch}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Last name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="update_account">
                            <tr>
                                <td>
                                    ${counter.count}.
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>
                                    <input type="text" name="txtLastname" value="${dto.lastname}" />
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked ="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="delete_account">
                                        <c:param name="username" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update"/>
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>
                No record is matched!
            </h2>
        </c:if>
    </c:if>
</body>
</html>
