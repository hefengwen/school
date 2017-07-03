<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>新增课程</title>
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
          <div style="padding:20px;">
            <form class="form-horizontal" role="form" action="<c:url value="/user/courseInfoAdd"/>"
              method="post" enctype="multipart/form-data"  onsubmit="return check();">
              <div class="form-group">
                <label for="name" class="control-label">课程名称：</label><span id="nameMsg"></span><br /> <br />
                <div class="col-sm-10" style="width: 100%;">
                  <input type="text" class="form-control" id="name" name="name" autofocus />
                </div>
              </div>
              <div class="form-group">
                <label for="note" class="control-label">课程简介：</label><span id="noteMsg"></span><br /> <br />
                <div class="col-sm-10" style="width: 100%;">
                  <textarea class="form-control" id="note" name="note" rows="5" style="width: 100%;"></textarea>
                </div>
              </div>
              <div class="form-group">
                <label for="picture" class="control-label">课程封面：</label><span id="pictureMsg"></span><br /> <br />
                <div class="col-sm-10" style="width: 100%;">
                  <input type="file" class="form-control" id="pictureFile" name="pictureFile" /> 
                  <br/>
                  <img id="picturePre" width="240" height="300" /><br /> <br />
                </div>
              </div>
              <div class="form-group">
                <label for="catalog" class="control-label">课程目录：</label><br /> <br />
                <div class="col-sm-10" style="width: 100%;">
                  <textarea name="catalog" style="width: 100%; height: 600px; visibility: hidden;"></textarea>
                  <br /> <br />
                </div>
              </div>
              <div class="form-group">
                <label for="standard" class="control-label">课程标准：</label><br /> <br />
                <div class="col-sm-10" style="width: 100%;">
                  <textarea name="standard" style="width: 100%; height: 600px; visibility: hidden;"></textarea>
                  <br /> <br />
                </div>
              </div>
              <div class="form-group">
                <label for="type" class="control-label">课程类型：</label><br /> <br />
                <div class="col-sm-10" style="width: 100%;">
                  <select id="type" name="type" class="form-control">
                    <c:forEach var="t" items="${courseTypes}">
                      <option value="${t.type }">${t.name }</option>
                    </c:forEach>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-5">
                  <button type="submit" class="btn btn-success">新增</button>
                </div>
              </div>
            </form>
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
				return false;
			}
			$("#noteMsg").html("");
			return true;
		});
		$("#pictureFile").blur(function() {
			var pictureFile = $("#pictureFile").val();
			if ($.trim(pictureFile) == "") {
				$("#pictureMsg").html("课程封面不能为空").css("color", "red");
				return false;
			}
			$("#pictureMsg").html("");
			return true;
		});
	});
	function check(){
		var name = $("#name").val();
		var note = $("#note").val();
		var pictureFile = $("#pictureFile").val();
		if ($.trim(name) == ""||$.trim(note) == ""||$.trim(pictureFile) == "") {
			$("#name").blur();
			$("#note").blur();
			$("#pictureFile").blur();
			return false;
		}
		return true;
	}
</script>

</html>
