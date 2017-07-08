<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>资源库首页</title>

<link rel="stylesheet" type="text/css" href="<c:url value="/indexpic/css/bootstrap.min.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/indexpic/css/bootstrap-theme.min.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/indexpic/css/font-awesome.min.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/indexpic/css/zzsc.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/indexpic/dist/jquery.vm-carousel.css"/>">
<%@include file="../../common/head.jsp"%>
<style>
#indexPic>ul>li>img{
  width:280px;
}
</style>
</head>
<body>
  <div class="container">
    <%@include file="../../common/nav.jsp"%>
    
    <div class="row clearfix allContent">
      <div id="indexPic">
        <ul class="vmcarousel-centered-infitine">
          <li><img src="<c:url value="/indexpic/images/1.png"/>" alt="" width="180"></li>
          <li><img src="<c:url value="/indexpic/images/2.png"/>" alt="" width="180"></li>
          <li><img src="<c:url value="/indexpic/images/3.png"/>" alt="" width="180"></li>
          <li><img src="<c:url value="/indexpic/images/4.png"/>" alt="" width="180"></li>
          <li><img src="<c:url value="/indexpic/images/5.png"/>" alt="" width="180"></li>
          <li><img src="<c:url value="/indexpic/images/6.png"/>" alt="" width="180"></li>
         </ul>
      </div>
    </div>
    <div class="row clearfix allContent">
      <div class="row clearfix" style="height: 100%;">
        <div class="indexContent">
          <div>
            <div class="indexTitle">
              <img style="width: 65px; height: 36px;" src="<c:url value="/image/indexTitle.png"/>" />
              <b class="indexTitleName">专业课程</b><b style="float: right;"><a
                href="<c:url value="/study/studyInfo"/>" />更多&gt;&gt;</a></b>
            </div>
            <div>
              <table class="table course" style="text-align: center; border: 0px;">
                <tbody>
                  <tr><td></td><td></td><td></td><td></td></tr>
                  <tr>
                    <c:forEach var="course" items="${courses.courseList }" varStatus="status">
                      <td>
                        <a href="<c:url value="/study/courseInfo/${course.courseId }"/>"  target="_blank"> 
                          <img id="picturePre" width="120" height="170" src="<c:url value="/user/filePreView/${course.courseId }"/>" /><br /> 
                        <span>${course.name }</span>
                      </a></td>
                      <c:if test="${(status.index+1)%4==0 }">
                  </tr>
                  <tr>
                    </c:if>
                    </c:forEach>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div>
            <div class="indexTitle">
              <img style="width: 65px; height: 36px;" src="<c:url value="/image/indexTitle.png"/>" />
              <b class="indexTitleName">课程标准</b> <b style="float: right;"><a
                href="<c:url value="/download/downloadInfo?type=${standardsType }"/>" />更多&gt;&gt;</a></b>
            </div>
            <div>
              <table class="table" style="text-align: center;">
                <tbody>
                  <c:forEach var="resource" items="${standards.resourceList }" varStatus="status">
                  <tr>
                    <td>${(result.curPage-1)*result.pageCount+(status.index+1) }</td>
                    <td style="text-align: left;"><img style="width: 32px; height: 32px;"
                      src="<c:url value="/image/filetype/${fn:toLowerCase(resource.suffix) }.png"/>" /> <span>
                        <strong>&nbsp;&nbsp;<a href="${basePath }/download/resourceInfo/${resource.resourceId}" target="_blank">${resource.name }</a></strong><br /> <small>专业：<span
                          style="color: red">${resource.majorName }</span>&nbsp;&nbsp;
                      </small> 
                      <small>上传者：<span style="color: red">${resource.userName }</span>&nbsp;&nbsp;
                      </small> 
                      <small>文件大小：<span style="color: red">${resource.fileSize }MB</span>&nbsp;&nbsp;
                      </small> 
                      <small>收藏：<span style="color: red">${resource.saveCnt }</span>次&nbsp;&nbsp;
                      </small>
                      <small>下载：<span style="color: red">${resource.loadCnt }</span>次&nbsp;&nbsp;
                      <small>评分：<span style="color: red">${resource.score }</span>分&nbsp;&nbsp;
                      </small>
                      <span class="resourceStar" data-score="${resource.score }"></span>
                      </small><br /> 
                      <small>资源简介：
                      <c:if test="${fn:length(resource.note) > 20 }">
                          ${fn:substring(resource.note,0,10) }...
                      </c:if>
                      <c:if test="${fn:length(resource.note) <= 20 }">
                          ${resource.note }
                      </c:if>
                      </small>
                    </span></td>
                    <td><small><fmt:formatDate value="${resource.createTime }"
                          pattern="yyyy-MM-dd HH:mm" /></small></td>
                    <td><a href="javascript:downloadFile(${resource.resourceId });" />下载</a> <a
                      href="javascript:saveFile(${resource.resourceId });" />收藏</a></td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
          <div>
            <div class="indexTitle">
              <img style="width: 65px; height: 36px;" src="<c:url value="/image/indexTitle.png"/>" />
              <b class="indexTitleName">电子教案</b> <b style="float: right;"><a
                href="<c:url value="/download/downloadInfo?type=${booksType }"/>" />更多&gt;&gt;</a></b>
            </div>
            <div>
              <table class="table" style="text-align: center;">
                <tbody>
                  <c:forEach var="resource" items="${books.resourceList }" varStatus="status">
                  <tr>
                    <td>${(result.curPage-1)*result.pageCount+(status.index+1) }</td>
                    <td style="text-align: left;"><img style="width: 32px; height: 32px;"
                      src="<c:url value="/image/filetype/${fn:toLowerCase(resource.suffix) }.png"/>" /> <span>
                        <strong>&nbsp;&nbsp;<a href="${basePath }/download/resourceInfo/${resource.resourceId}" target="_blank">${resource.name }</a></strong><br /> <small>专业：<span
                          style="color: red">${resource.majorName }</span>&nbsp;&nbsp;
                      </small> <small>上传者：<span style="color: red">${resource.userName }</span>&nbsp;&nbsp;
                      </small> <small>文件大小：<span style="color: red">${resource.fileSize }MB</span>&nbsp;&nbsp;
                      </small> <small>收藏：<span style="color: red">${resource.saveCnt }</span>次&nbsp;&nbsp;
                      </small> <small>下载：<span style="color: red">${resource.loadCnt }</span>次&nbsp;&nbsp;
                      </small>
                      <small>评分：<span style="color: red">${resource.score }</span>分&nbsp;&nbsp;
                      </small>
                      <span class="resourceStar" data-score="${resource.score }"></span>
                      </small><br /> 
                      <small>资源简介：
                      <c:if test="${fn:length(resource.note) > 20 }">
                          ${fn:substring(resource.note,0,10) }...
                      </c:if>
                      <c:if test="${fn:length(resource.note) <= 20 }">
                          ${resource.note }
                      </c:if></small>
                    </span></td>
                    <td><small><fmt:formatDate value="${resource.createTime }"
                          pattern="yyyy-MM-dd HH:mm" /></small></td>
                    <td><a href="javascript:downloadFile(${resource.resourceId });" />下载</a> <a
                      href="javascript:saveFile(${resource.resourceId });" />收藏</a></td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="indexRight">
          <ul class="list-unstyled rightUl" style="min-height:262px;height:auto;">
            <li class="indexTitle"><img style="width: 32px; height: 32px;"
              src="<c:url value="/image/notice.png"/>" /><b>&nbsp;&nbsp;系统公告</b><a  style="float: right;" href="<c:url value="/user/systemNoticeList"/>"  target="_blank">更多>></a></li>
            <table class="table" style="text-align: center;width:98%;margin: auto;">
              <tbody>
                  <c:forEach var="notice" items="${systemNotices.noticeList }" varStatus="status">
                    <tr>
                        <td style="width: 65%;"><a href="<c:url value="/user/noticeDetail/${notice.noticeId }"/>"  target="_blank">
                            <c:if test="${fn:length(notice.title) > 10 }">
                                ${fn:substring(notice.title,0,10) }...
                            </c:if>
                            <c:if test="${fn:length(notice.title) <= 10 }">
                                ${notice.title }
                            </c:if>
                        </a></td>
                        <td><fmt:formatDate value="${notice.createTime }" pattern="yyyy-MM-dd" /></td>
                    </tr>
                  </c:forEach>
              </tbody>
            </table>
          </ul>
          <ul class="list-unstyled rightUl" style="min-height:262px;height:auto;">
            <li class="indexTitle"><img style="width: 32px; height: 32px;"
              src="<c:url value="/image/notice.png"/>" /><b>&nbsp;&nbsp;课程公告</b><a style="float: right;" href="<c:url value="/user/courseNoticeList"/>"  target="_blank">更多>></a></li>
            <table class="table" style="text-align: center;width:98%;margin: auto;">
              <tbody>
                  <c:forEach var="notice" items="${courseNotices.noticeList }" varStatus="status">
                    <tr>
                        <td style="width: 65%;"><a href="<c:url value="/user/noticeDetail/${notice.noticeId }"/>"  target="_blank">
                            <c:if test="${fn:length(notice.title) > 10 }">
                                ${fn:substring(notice.title,0,10) }...
                            </c:if>
                            <c:if test="${fn:length(notice.title) <= 10 }">
                                ${notice.title }
                            </c:if>
                        </a></td>
                        <td><fmt:formatDate value="${notice.createTime }" pattern="yyyy-MM-dd" /></td>
                    </tr>
                  </c:forEach>
              </tbody>
            </table>
          </ul>
          <ul class="list-unstyled rightUl" style="min-height:300px;height:auto;">
            <li class="indexTitle"><img style="width: 32px; height: 32px;"
              src="<c:url value="/image/score.png"/>" /><b>&nbsp;&nbsp;积分排行</b><a style="float: right;" href="<c:url value="/user/teacherScoreList"/>"  target="_blank">更多>></a></li>
            <table class="table" style="text-align: center;width:98%;margin: auto;">
              <thead>
                <tr>
                  <th style="text-align: center;">名次</th>
                  <th style="text-align: center;">教师</th>
                  <th style="text-align: center;">积分</th>
                </tr>
              </thead>
              <tbody>
                  <c:forEach var="teacher" items="${teachers.userList }" varStatus="status">
                    <tr>
                      <td style="width:20%;"><span>${status.index+1}</span></td>
                      <td><span>${teacher.name } </span></td>
                      <td style="width:20%;"><span>${teacher.score }</span></td>
                    </tr>
                  </c:forEach>
              </tbody>
            </table>
          </ul>
        </div>
      </div>
    </div>
    <%@include file="../../common/foot.jsp"%>
  </div>
</body>
<%@include file="../../common/tail.jsp"%>
<script src="<c:url value="/indexpic/dist/jquery.vm-carousel.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/star/jquery.raty.min.js"/>"></script>
<script type="text/javascript">
	$(function() {
	  $("#index").addClass("active");
	  $('.vmcarousel-centered-infitine').vmcarousel({
		 centered: true,
		 start_item: 1,
		 autoplay: true,
		 infinite: true
	  });
	  
	//评分详情
      $(".resourceStar").raty({
      	starOff:'${basePath}/image/star/star-off.png',
      	starOn:'${basePath}/image/star/star-on.png',
      	score:function(){
      		//alert($(this).attr("data-score"));
      		return $(this).attr("data-score");
      	},
      	readOnly:true
      });
	});
	function saveFile(id) {
		$.ajax({
			url : "${basePath}/user/saveResource/" + id,
			type : "POST",
			success : function(result) {
				alert(result.msg);
				window.location.reload();
			},
			error : function(result) {
				alert("收藏失败！");
			},
			processData : false,
			contentType : false,
		});
	}
	function downloadFile(id) {
		$.ajax({
			url : "${basePath}/user/queryDownloadResource/" + id,
			type : "POST",
			success : function(result) {
				if (result.status != "000000")
					alert(result.msg);
				else {
					window.open("${basePath}/user/" + result.data + "/" + id);
					window.location.reload();
				}
			},
			error : function(result) {
				alert("下载失败！");
			},
			processData : false,
			contentType : false,
		});
	}
</script>
</html>
