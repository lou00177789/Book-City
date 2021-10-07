<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
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
			<span class="wel_word">我的订单</span>
			<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
			<c:if test="${empty sessionScope.userOrders}">
				<tr>
					<td colspan="5"><a href="pages/cart/cart.jsp">你还没有产生过订单哦，去购物车结算一个吧！</a></td>
				</tr>
			</c:if>
			<c:forEach items="${sessionScope.userOrders}" var="userOrders" >
				<tr>
					<td>${userOrders.createTime}</td>
					<td>${userOrders.price}</td>
					<c:choose>
						<c:when test="${userOrders.status == 0}"><td>未发货</td></c:when>
						<c:when test="${userOrders.status == 1}"><td>已发货</td></c:when>
						<c:when test="${userOrders.status == 2}"><td>已签收</td></c:when>
					</c:choose>
					<td><a href="orderServlet?action=orderDetail&orderId=${userOrders.orderId}&orderCreateTime=${userOrders.createTime}&orderTotalPrice=${userOrders.price}&orderStatus=${userOrders.status}">查看详情</a></td>
					<c:if test="${userOrders.status == 1}">
						<td><a href="orderServlet?action=receiverOrder&orderId=${userOrders.orderId}">点击签收</a></td>
					</c:if>

				</tr>
			</c:forEach>

		</table>
		
	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>