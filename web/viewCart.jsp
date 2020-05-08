<%-- 
    Document   : viewCart
    Created on : Mar 24, 2020, 4:43:43 PM
    Author     : nhocc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
    </head>
    <body>
        <font color="blue">
        Welcome, ${sessionScope.USER.lastname}
        </font>
        <button><a href="logout" style="text-decoration: none;">Logout</a></button>
        <button><a href="show_all_book" style="text-decoration: none;">Back</a></button>
        <h1>View your cart</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart.books}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="entry" items="${cart.books}" varStatus="counter">
                    <form action="update_quantity">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${entry.key.title}
                            </td>
                            <td>
                                ${entry.key.price}
                            </td>
                            <td>
                                <input type="text" name="txtQuantity" value="${entry.value}" />
                            </td>
                            <td>
                                ${entry.key.price * entry.value}
                            </td>
                            <td>
                                <input type="hidden" name="bookID" value="${entry.key.bookID}" />
                                <input type="submit" value="Update" />
                            </td>
                            <td>
                                <c:url var="urlRewriting" value="remove_item_from_cart">
                                    <c:param name="bookID" value="${entry.key.bookID}"/>
                                </c:url>
                                <button><a href="${urlRewriting}" style="text-decoration: none;">Delete</a></button>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                <tr>
                    <td colspan="4"></td>
                    <td>
                        <font color="blue">${cart.total}</font>
                    </td>
                    <td colspan="2">
                        <button><a href="check_out" style="text-decoration: none;">Check out</a></button>
                    </td>
                </tr>
            </tbody>
        </table>


    </c:if>
    <c:if test="${empty cart.books}">
        <h2>Cart is empty!!!</h2>
    </c:if>
</body>
</html>
