<%-- 
    Document   : store
    Created on : Mar 24, 2020, 11:25:06 AM
    Author     : nhocc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store Page</title>
    </head>
    <body>
        <font color="blue">
        Welcome, ${sessionScope.USER.lastname}
        </font>
        <button><a href="logout" style="text-decoration: none;">Logout</a></button>
        <h1>STORE PAGE</h1>
        <button><a href="view_cart_page" style="text-decoration: none;">View cart</a></button>
        <c:set var="listBook" value="${requestScope.LISTBOOK}"/>
        <c:if test="${not empty listBook}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Price</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${listBook}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}.   
                            </td>
                            <td>
                                ${dto.title}
                            </td>
                            <td>
                                ${dto.price}
                            </td>
                            <td>
                                <c:url var="urlRewriting" value="add_item_to_cart">
                                    <c:param name="bookID" value="${dto.bookID}"/>
                                    <c:param name="title" value="${dto.title}"/>
                                    <c:param name="price" value="${dto.price}"/>
                                </c:url>
                                <button><a href="${urlRewriting}" style="text-decoration: none;">Add to cart</a></button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty listBook}">
            <h2>There is no book!</h2>
        </c:if>

    </body>
</html>
