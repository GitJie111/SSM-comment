<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
	<title></title>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/all.css"/>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/pop.css"/>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/main.css"/>
	<script type="text/javascript" src="${basePath}/js/common/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${basePath}/js/common/common.js"></script>
	<script type="text/javascript" src="${basePath}/js/content/commentList.js"></script>
</head>
<body style="background: #e1e9eb;">
<form action="${basePath}/comments" id="mainForm" method="post">
	<input type="hidden" id="id" name="id"/>
	<input type="hidden" id="message" value="${pageCode.msg}"/>
	<input type="hidden" id="basePath" value="${basePath}"/>
	<input type="hidden" name="page.currentPage" id="currentPage" value="1"/>
	<div class="right">
		<div class="current">当前位置：<a href="#">内容管理</a> &gt; 评论查询</div>
		<div class="rightCont">
			<p class="g_title fix">评论列表</p>
			<table class="tab1">
				<tbody>
				<tr>
					<td align="right" width="80">评论内容：</td>
					<td>
						<input name="comment" id="comment" value="${searchParam.comment}" class="allInput" type="text"/>
					</td>
					<td style="text-align: right;" width="150">
						<input class="tabSub" value="查询" onclick="search('1');" type="button"/>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				</tbody>
			</table>
			<div class="zixun fix">
				<table class="tab2" width="100%">
					<tbody>
					<tr>
						<th>序号</th>
						<th>手机号</th>
						<th>订单号</th>
						<th>商品名称</th>
						<th>评论星级</th>
						<th>评论时间</th>
					</tr>
					<c:forEach items="${list}" var="item" varStatus="s">
						<tr>
							<td>${s.index + 1}</td>
							<td>${item.orders.member.phone}</td>
							<td>${item.ordersId}</td>
							<td>${item.orders.business.title}</td>
							<td>${item.star}</td>
							<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>

				<!-- 分页 -->
				<t:page jsMethodName="search" page="${searchParam.page}"></t:page>
			</div>
		</div>
	</div>
</form>
</body>
</html>