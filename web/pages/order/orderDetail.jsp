<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <%@include file="/pages/common/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">订单详情</span>
    <c:if test="${requestScope.sourecePath == user}">
        <%@include file="/pages/common/login_success_menu.jsp"%>
    </c:if>
    <c:if test="${requestScope.sourecePath == manager}">
        <%@include file="/pages/common/manager_menu.jsp"%>
    </c:if>


</div>
<div style="text-align: center">
    <span>订单编号：${sessionScope.orderId}</span>&nbsp;&nbsp;&nbsp;&nbsp;
    <span>订单日期：${sessionScope.orderCreateTime}</span>&nbsp;&nbsp;&nbsp;&nbsp;
    <span>总金额：${sessionScope.orderTotalPrice}</span>&nbsp;&nbsp;&nbsp;&nbsp;
    <span>订单状态：<c:choose>
        <c:when test="${sessionScope.orderStatus == 0}">未发货</c:when>
        <c:when test="${sessionScope.orderStatus == 2}">已发货</c:when>
        <c:when test="${sessionScope.orderStatus == 3}">已签收</c:when>
    </c:choose></span>
</div>

<div id="main">


    <table>
        <tr>
            <td>书名</td>
            <td>数量</td>
            <td>金额</td>
        </tr>
        <c:forEach items="${sessionScope.orderDetail}" var="orderItem" >
            <tr>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
            </tr>
        </c:forEach>
    </table>


</div>

<%@include file="/pages/common/footer.jsp"%>
</body>
</html>