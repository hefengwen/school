<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <ul class="breadcrumb">
  <li><a href="#">资源中心</a></li>
  <li class="active">个人信息</li>
</ul> -->
<%-- <ul class="list-unstyled" style="background-color: #99FF99;width:15%;height:100%;float: left;border:1px solid #66FF66;">
  <li style="text-align: center;border-bottom:1px solid #66FF66">
    <b style="height: 40px;line-height: 40px;font-size:18px;">专业列表</b>
  </li>
  <c:forEach var="major" items="${majors }">
    <a href="javascript:queryDownload(${major.majorId });" style="">
      <li id="${major.majorId }" class="menu" style="text-align: center;border-bottom:1px solid #66FF66">
        <b style="height: 40px;line-height: 40px;font-size:14px;">${major.name }</b>
      </li>
    </a>
  </c:forEach>
</ul> --%>
<ul class="list-unstyled leftUl">
  <li class="leftUlTitleLi">
    <b class="leftUlTitleLiFont">专业列表</b>
  </li>
  <c:forEach var="major" items="${majors }">
    <a href="javascript:queryDownload(${major.majorId });" style="">
      <li id="${major.majorId }" class="menu leftUlListLi">
        <b class="leftUlListLiFont">${major.name }</b>
      </li>
    </a>
  </c:forEach>
</ul>