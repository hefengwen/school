<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>教学中心</title>
<%@include file="../../common/head.jsp"%>
</head>
<body>
  <div class="container">
    <%@include file="../../common/nav.jsp"%>
    <div class="row clearfix allContent">
      <div class="row clearfix" style="height: 100%;">
        <div class="indexContent">
          <div style="overflow: hidden;">
            <div class="indexTitle">
              <img style="width: 32px; height: 32px;"
                src="<c:url value="/image/course/courseInfo.png"/>" /> <b class="indexTitleName">课程概况</b>
            </div>
            <div>
              <div style="float: left; width: 30%; text-align: center; padding: 20px;">
                <img id="picturePre" width="120" height="170"
                  src="<c:url value="/user/filePreView/${course.courseId }"/>" /><br /> <span>${course.name }</span>
              </div>
              <div style="float: left; width: 70%; padding: 20px;">
                <input type="hidden" id="courseId" value="${course.courseId }"/>
                <b>课程名称：${course.name }</b><br /> 
                <b>课程简介：</b><br />
                <p>${course.note }</p>
              </div>
            </div>
          </div>
          <div style="overflow: hidden;">
            <div class="indexTitle">
              <img style="width: 32px; height: 32px;"
                src="<c:url value="/image/course/teacherInfo.png"/>" /> <b class="indexTitleName">教师介绍</b>
            </div>
            <div>
              <div style="float: left; width: 30%; text-align: center; padding: 20px;">
                <img style="width: 72px; height: 72px;"
                  src="<c:url value="/image/course/head.png"/>" /><br />
              </div>
              <div style="float: left; width: 70%; padding: 20px;">
                <b>教师名称：${teacher.name }</b><br /> <b>教师职称：${teacher.teacherLevel }</b><br /> <b>教师简介：</b><br />
                <p>${teacher.teacherDetail }</p>
              </div>
            </div>
          </div>
          <div style="overflow: hidden;">
            <div class="indexTitle">
              <img style="width: 32px; height: 32px;"
                src="<c:url value="/image/course/courseIndex.png"/>" /> <b class="indexTitleName">课程目录</b><b
                style="float: right;"><a id="catalogLink"
                href="javascript:showAll('catalogLink','catalogDetail');" />全部&gt;&gt;</a></b>
            </div>
            <div>
              <div id="catalogDetail" style="margin: 20px; height: 100px; overflow: hidden;">
                <p>${course.catalog }</p>
              </div>
            </div>
          </div>
          <div style="overflow: hidden;">
            <div class="indexTitle">
              <img style="width: 32px; height: 32px;"
                src="<c:url value="/image/course/contentInfo.png"/>" /> <b class="indexTitleName">课程标准</b><b
                style="float: right;"><a id="standardLink"
                href="javascript:showAll('standardLink','standardDetail');" />全部&gt;&gt;</a></b>
            </div>
            <div>
              <div id="standardDetail" style="margin: 20px; height: 100px; overflow: hidden;">
                <p>${course.standard }</p>
              </div>
            </div>
          </div>
        </div>
        <div class="indexRight">
          <div style="margin-left: 10px;">
            <a href="javascript:subscribeCourse(${course.courseId });"> 
              <img src="<c:url value="/image/course/subscribe.png"/>" /><b> 订阅课程</b></a> 
              <br />
              <br /> 
              <c:if test="${empty course.book.bookId }">
                <a href="javascript:hasNoBook();"> <img src="<c:url value="/image/course/book.png"/>" />
                  <b>课程教材</b>
                </a> 
              </c:if>
              <c:if test="${!empty course.book.bookId }">
                <a href="<c:url value="/user/courseBookView/${course.book.bookId }"/>" target="_blank"> <img src="<c:url value="/image/course/book.png"/>" />
                  <b>课程教材</b>
                </a> 
              </c:if>
              <br />
              <br />
          </div>
          <ul class="list-unstyled rightUl">
            <li class="indexTitle"><img src="<c:url value="/image/attention.png"/>" /> <b>&nbsp;关注详情</b>
            </li>
            <li class="rightUlListLi">
              <table style="text-align: left; margin: auto;">
                <tr><td><b>订阅次数：</b></td><td>${course.saveCnt }</td></tr>
                <tr><td><b>浏览次数：</b></td><td>${course.viewCnt }</td></tr>
                <tr><td><b>评价次数：</b></td><td id="scoreCnt">${course.scoreCnt }</td></tr>
              </table>
              <br/>
            </li>
          </ul>
          <ul class="list-unstyled rightUl">
            <li class="indexTitle"><img src="<c:url value="/image/coursescore.png"/>" /> <b>&nbsp;评分详情</b>
            </li>
            <li class="rightUlListLi">
              <table style="text-align: left; margin: auto;">
                <tr><td><b>课程评分：${course.score}分</b></td></tr>
                <tr><td><div id="courseStar" data-score="${course.score }"></div></td></tr>
                <tr><td><b>我的评分：${empty score.score?0:score.score}分</b></td></tr>
                <tr><td><div id="userStar" data-score="${empty score.score?0:score.score }"></div></td></tr>
              </table>
              <br/>
            </li>
          </ul>
          <ul class="list-unstyled rightUl">
            <li class="indexTitle"><img src="<c:url value="/image/likes.png"/>" /><b>&nbsp;相关课程</b></li>
            <li class="rightUlListLi">
              <table style="text-align: left; margin: auto;">
                <tbody>
                    <c:forEach var="c" items="${likesCourses.courseList }" varStatus="status">
                      <c:if test="${c.courseId != course.courseId }">
                        <tr><td><span><a href="<c:url value="/study/courseInfo/${c.courseId }"/>"  target="_blank">${c.name}</a></span></td></tr>
                      </c:if>
                    </c:forEach>
                </tbody>
              </table>
              <br/>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <%@include file="../../common/foot.jsp"%>
  </div>
</body>
<%@include file="../../common/tail.jsp"%>
<script type="text/javascript" src="<c:url value="/js/star/jquery.raty.min.js"/>"></script>
<script type="text/javascript">
	$(function() {
		$("#studyCenter").addClass("active");
		//评分详情
        $("#courseStar").raty({
        	starOff:'${basePath}/image/star/star-off.png',
        	starOn:'${basePath}/image/star/star-on.png',
        	score:function(){
        		//alert($(this).attr("data-score"));
        		return $(this).attr("data-score");
        	},
        	readOnly:true
        });
        $("#userStar").raty({
        	starOff:'${basePath}/image/star/star-off.png',
        	starOn:'${basePath}/image/star/star-on.png',
        	score:function(){
        		//alert($(this).attr("data-score"));
        		return $(this).attr("data-score");
        	},
        	readOnly:function(){
        		return $(this).attr("data-score")!=0;
        	},
        	click:function(score,event){
        		var formData = new FormData();
        		formData.append("score", score);
        		formData.append("refId", $("#courseId").val());
        		//alert($("#courseId").val());
        		$.ajax({
        			url : "${basePath}/user/scoreCourse",
        			type : "POST",
        			success : function(result) {
        				alert(result.msg);
        				window.location.reload();
        			},
        			error : function(result) {
        				alert("评价失败！");
        			},
        			data : formData,
        			processData : false,
        			contentType : false,
        		});
        	}
        });
	});

	function showAll(aid, did) {
		$("#" + did).css("height", "100%");
		$("#" + aid).attr("href",
				"javascript:hiddenAll('" + aid + "','" + did + "');").text(
				"收起>>");
	}
	function hiddenAll(aid, did) {
		$("#" + did).css("height", "100px");
		$("#" + aid).attr("href",
				"javascript:showAll('" + aid + "','" + did + "');")
				.text("全部>>");
	}
	function subscribeCourse(id) {
		$.ajax({
			url : "${basePath}/user/subscribeCourse/" + id,
			type : "POST",
			success : function(result) {
				alert(result.msg);
				window.location.reload();
			},
			error : function(result) {
				alert("订阅失败！");
			},
			processData : false,
			contentType : false,
		});
	}
	function hasNoBook(){
		alert("暂无教材！");
	}
</script>
</html>
