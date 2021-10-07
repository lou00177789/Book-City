<%--
  Created by IntelliJ IDEA.
  User: 123
  Date: 2021/9/2
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}" >上一页</a>
    </c:if>
    <c:choose>
        <%--总页数小于5--%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>
        <%--总页数大于5--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--前3条--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>

                <%--后三条--%>
                <c:when test="${requestScope.page.pageNo >= requestScope.page.pageTotal-2}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>

                <%--中间位置--%>
                <c:when test="${requestScope.page.pageNo > 3 && requestScope.page.pageNo < requestScope.page.pageTotal-2}">
                    <c:set var="begin" value="${requestScope.page.pageNo - 2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo + 2}"/>
                </c:when>

            </c:choose>

        </c:when>

    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>

    </c:forEach>


    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">

        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}" >下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>


    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function (){
            $("#searchPageBtn").click(function (){

                var pageNo =$("#pn_input").val();
                if(pageNo < 0||pageNo > ${requestScope.page.pageTotal}){
                    alert("你可能输错了，我们没有这一页哦！麻烦重新选择一下吧！");
                    return false;
                }
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo="+pageNo;
            })
        })
    </script>
</div>
