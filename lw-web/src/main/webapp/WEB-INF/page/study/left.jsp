<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <ul class="breadcrumb">
  <li><a href="#">教学中心</a></li>
  <li class="active">个人信息</li>
</ul> -->
<ul class="list-unstyled leftUl">
  <li class="leftUlTitleLi">
    <b class="leftUlTitleLiFont">专业列表</b>
  </li>
  <c:forEach var="major" items="${majors }">
    <a href="javascript:queryCourse(${major.majorId });">
      <li id="${major.majorId }" class="menu leftUlListLi">
        <b class="leftUlListLiFont">${major.name }</b>
      </li>
    </a>
  </c:forEach>
</ul>