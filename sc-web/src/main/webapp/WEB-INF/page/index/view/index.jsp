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
#indexPic>ul>li>img {
  width: 280px;
}
</style>
</head>
<body>
  <div class="container">
    <%@include file="../../common/nav.jsp"%>

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
                  <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                  </tr>
                  <tr>
                    <c:forEach var="course" items="${courses.courseList }" varStatus="status">
                      <td><a href="<c:url value="/study/courseInfo/${course.courseId }"/>"
                        target="_blank"> <img id="picturePre" width="120" height="170"
                          src="<c:url value="/user/filePreView/${course.courseId }"/>" /><br /> <span>${course.name }</span>
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
        </div>
        <div class="indexRight">
          <ul class="list-unstyled rightUl" style="min-height: 300px; height: auto;">
            <li class="indexTitle"><img style="width: 32px; height: 32px;"
              src="<c:url value="/image/score.png"/>" /><b>&nbsp;&nbsp;积分排行</b><a
              style="float: right;" href="<c:url value="/user/teacherScoreList"/>" target="_blank">更多>></a></li>
            <table class="table" style="text-align: center; width: 98%; margin: auto;">
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
                    <td style="width: 20%;"><span>${status.index+1}</span></td>
                    <td><span>${teacher.name } </span></td>
                    <td style="width: 20%;"><span>${teacher.score }</span></td>
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
			centered : true,
			start_item : 1,
			autoplay : true,
			infinite : true
		});

		//评分详情
		$(".resourceStar").raty({
			starOff : '${basePath}/image/star/star-off.png',
			starOn : '${basePath}/image/star/star-on.png',
			score : function() {
				//alert($(this).attr("data-score"));
				return $(this).attr("data-score");
			},
			readOnly : true
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
