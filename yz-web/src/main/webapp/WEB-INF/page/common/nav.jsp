<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row clearfix">
  <div class="col-md-12 column" style="background: url('${basePath}/image/yizhou_logo.png') no-repeat;">
    <div class="row clearfix">
        <div style="height:110px;text-align: right;margin-right: 20px;">
			<c:choose>
				<c:when test="${empty currentUser }">
					<a href="<c:url value="/user/jsp/login"/>"><span style="color:#99CCFF;">登录</span></a>
				</c:when>
				<c:otherwise>
					<span style="color:#99CCFF;">当前用户：${currentUser.name }</span>&nbsp;&nbsp;<a href="<c:url value="/user/logout"/>"><span style="color:#99CCFF;">退出</span></a>
				</c:otherwise>
			</c:choose>
		</div>
    </div>
    <div class="row clearfix" style="height: 58px; background-color: #99CCFF;padding: 2px 0;">
      <!-- <div class="col-md-6 column" style="height: 54px; background-color: #99CCFF;padding: 2px 0;"></div> -->
      <div class="col-md-6 column pull-right" style="height: 54px; background-color: #99CCFF;padding: 2px 0;">
        <ul class="nav nav-pills nav-justified" style="line-height: 30px;font-size:18px; text-align: center;">
          <li id="index" style="border-left:1px solid #fff;"><a href="<c:url value="/"/>" ">首页</a></li>
          <li id="studyCenter" style="border-left:1px solid #fff;"><a href="<c:url value="/study/studyInfo"/>">教学中心</a></li>
          <li id="downloadCenter" style="border-left:1px solid #fff;"><a href="<c:url value="/download/downloadInfo"/>">资源中心</a></li>
          <li id="qaCenter" style="border-left:1px solid #fff;"><a href="<c:url value="/qa/questionInfo"/>">问答中心</a></li>
          <li id="userCenter" style="border-left:1px solid #fff;"><a id="toLogin" href="<c:url value="/user/userInfo"/>">个人中心</a></li>
        </ul>
      </div>
    </div>
  </div>
</div>
