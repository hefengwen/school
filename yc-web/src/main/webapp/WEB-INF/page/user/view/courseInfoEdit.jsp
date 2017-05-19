<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>修改课程</title>
<%@include file="../../common/head.jsp"%>
<link href="<c:url value="/js/editor/themes/default/default.css"/>" rel="stylesheet" />
<script type="text/javascript" src="<c:url value="/js/editor/kindeditor-all-min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/editor/lang/zh_CN.js"/>"></script>

<script>
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="catalog"]', {
			allowFileManager : true
		});
	});
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="standard"]', {
			allowFileManager : true
		});
	});
</script>
</head>
<body>
  <div class="container">
    <%@include file="../../common/nav.jsp"%>
    <div class="row clearfix allContent">
      <div class="row clearfix" style="height: 100%;">
        <%@include file="../left1.jsp"%>
        <div class="content">
          <div style="padding: 20px;">
            <form class="form-horizontal" role="form" action="<c:url value="/user/courseInfoEdit"/>"
              method="post" enctype="multipart/form-data" onsubmit="return check();">
              <input type="hidden" class="form-control" id="courseId" name="courseId"
                value="${result.courseId }" />
              <div class="form-group">
                <label for="name" class="control-label">课程名称：</label><span id="nameMsg"></span><br /><br /> <br />
                <div class="col-sm-10" style="width: 100%;">
                  <input type="text" class="form-control" id="name" name="name" autofocus
                    value="${result.name }" />
                </div>
              </div>
              <div class="form-group">
                <label for="note" class="control-label">课程简介：</label><span id="noteMsg"></span><br /> <br />
                <div class="col-sm-10" style="width: 100%;">
                  <textarea class="form-control" id="note" name="note" rows="5" style="width: 100%;">${result.note }</textarea>
                </div>
              </div>
              <div class="form-group">
                <label for="picture" class="control-label">课程封面：</label><br /> <br />
                <div class="col-sm-10" style="width: 100%;">
                  <input type="file" class="form-control" id="pictureFile" name="pictureFile" /> 
                  <br/>
                  <img id="picturePre" width="240" height="300"
                    src="<c:url value="/user/filePreView/${result.courseId }"/>" /><br /> <br />
                </div>
              </div>
              <div class="form-group">
                <label for="catalog" class="control-label">课程目录：</label><br /> <br />
                <div class="col-sm-10" style="width: 100%;">
                  <textarea name="catalog" style="width: 100%; height: 600px; visibility: hidden;">${result.catalog }</textarea>
                  <br /> <br />
                </div>
              </div>
              <div class="form-group">
                <label for="standard" class="control-label">课程标准：</label><br /> <br />
                <div class="col-sm-10" style="width: 100%;">
                  <textarea name="standard" style="width: 100%; height: 600px; visibility: hidden;">${result.standard }</textarea>
                  <br /> <br />
                </div>
              </div>
              <div class="form-group">
                <label for="type" class="control-label">课程类型：</label><br /> <br />
                <div class="col-sm-10" style="width: 100%;">
                  <select id="type" name="type" class="form-control">
                    <c:forEach var="t" items="${courseTypes}">
                      <c:if test="${domain.type==t.type }">
                        <option value="${t.type }" selected="selected">${t.name }</option>
                      </c:if>
                      <c:if test="${domain.type!=t.type }">
                        <option value="${t.type }">${t.name }</option>
                      </c:if>
                    </c:forEach>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-5">
                  <button type="submit" class="btn btn-success">修改</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <%@include file="../../common/foot.jsp"%>
  </div>
</body>
<%@include file="../../common/tail.jsp"%>
<script type="text/javascript" src="<c:url value="/js/image.js"/>"></script>
<script type="text/javascript">
	$(function() {
		$("#userCenter").addClass("active");
		$("#courseInfo").addClass("onSelect");
		$("#currentMenu").html($(".onSelect").html());
		$("#currentPage").html($("title").html());
		$(".menu").hover(function() {
			$(this).addClass("onSelect");
		}, function() {
			$(this).removeClass("onSelect");
			$("#courseInfo").addClass("onSelect");
		});
		$("#pictureFile").uploadPreview({
			Img : "picturePre",
			Width : 240,
			Height : 240
		});
		$("#name").blur(function() {
			var name = $("#name").val();
			if ($.trim(name) == "") {
				$("#nameMsg").html("课程名称不能为空").css("color", "red");
				$("#name").focus();
				return false;
			}
			$("#nameMsg").html("");
			return true;
		});
		$("#note").blur(function() {
			var note = $("#note").val();
			if ($.trim(note) == "") {
				$("#noteMsg").html("课程简介不能为空").css("color", "red");
				$("#note").focus();
				return false;
			}
			$("#noteMsg").html("");
			return true;
		});
	});
	function check(){
		var name = $("#name").val();
		var note = $("#note").val();
		if ($.trim(name) == ""||$.trim(note) == "") {
			$("#name").blur();
			$("#note").blur();
			return false;
		}
		return true;
	}
</script>

</html>
