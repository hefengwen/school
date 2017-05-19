<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="breadcrumb">
  <li><b>当前位置：个人中心</b></li>
  <li id="currentMenu"></li>
  <li><b id="currentPage"></b></li>
</ul>
<ul class="list-unstyled leftUl" >
  <li class="leftUlTitleLi">
    <b class="leftUlTitleLiFont">功能列表</b>
  </li>
  <a href="<c:url value="/user/userInfo"/>" >
    <li id="userInfo" class="menu leftUlListLi">
      <b class="leftUlListLiFont">个人信息</b>
    </li>
  </a>
  <a href="<c:url value="/user/updPasswdView"/>" >
    <li id="updPasswd" class="menu leftUlListLi">
      <b class="leftUlListLiFont">修改密码</b>
    </li>
  </a>
  <a href="<c:url value="/user/courseSubscribe"/>" >
    <li id="courseSubscribe" class="menu leftUlListLi">
      <b class="leftUlListLiFont">我的订阅</b>
    </li>
  </a>
  <a href="<c:url value="/user/resourceSave"/>" >
    <li id="resourceSave" class="menu leftUlListLi">
      <b class="leftUlListLiFont">我的收藏</b>
    </li>
  </a>
</ul>