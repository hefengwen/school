<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>我要提问</title>
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
        <div>
          <div style="padding:20px;">
            <form class="form-horizontal" role="form" action="<c:url value="/qa/questionInfoAdd"/>"
              method="post" onsubmit="return check();">
              <div class="form-group">
                <label for="title" class="control-label">提问标题：</label><span id="titleMsg"></span><br /> <br />
                <div class="col-sm-10" style="width: 100%;">
                  <input type="text" class="form-control" id="title" name="title" autofocus />
                </div>
              </div>
              <div class="form-group">
                <label for="content" class="control-label">问题描述：</label><span id="contentMsg"></span><br /> <br />
                <div class="col-sm-10" style="width: 100%;">
                  <textarea class="form-control" id="content" name="content" rows="5" style="width: 100%;"></textarea>
                </div>
              </div>
              <div class="form-group">
                <label for="type" class="control-label">所属课程：</label><br /> <br />
                <div class="col-sm-10" style="width: 100%;">
                  <select id="courseId" name="courseId" class="form-control">
                    <c:forEach var="c" items="${courses}">
                      <option value="${c.courseId }">${c.name }</option>
                    </c:forEach>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-5">
                  <button type="submit" class="btn btn-success">提问</button>
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
		$("#qaCenter").addClass("active");
		$("#title").blur(function() {
			var title = $("#title").val();
			if ($.trim(title) == "") {
				$("#titleMsg").html("提问标题不能为空").css("color", "red");
				$("#title").focus();
				return false;
			}
			$("#titleMsg").html("");
			return true;
		});
	});
	function check(){
		var title = $("#title").val();
		if ($.trim(title) == "") {
			$("#title").blur();
			return false;
		}
		return true;
	}
</script>

</html>
