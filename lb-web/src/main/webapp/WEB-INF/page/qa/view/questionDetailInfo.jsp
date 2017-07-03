<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="../../common/tag.jsp"%>
<!doctype html>
<html>
<head>
<title>提问详情</title>
<%@include file="../../common/head.jsp"%>
</head>
<body>
  <div class="container">
    <%@include file="../../common/nav.jsp"%>
    <div class="row clearfix allContent">
      <div class="row clearfix" style="height: 100%;">
        <div>
          <div class="info">
            <div>
                <span>
                      <strong><a href="${basePath }/qa/questionDetailInfo/${result.quesId}">${result.title }</a></strong><br /> 
                      <small><span style="">${result.content }</span>&nbsp;&nbsp;</small><br /> 
                      <small>所属课程：<span style="">${result.course.name }</span>&nbsp;&nbsp;</small>
                      <small>提问者：<span style="">${result.user.name }</span>&nbsp;&nbsp;</small>
                      <small>提问时间：<span style=""><fmt:formatDate value="${result.createTime }" pattern="yyyy-MM-dd HH:mm" /></span>&nbsp;&nbsp;</small> 
                </span>
            </div>
            <table class="table table-striped table-hover" style="text-align: center;">
              <tbody>
                <c:forEach var="answer" items="${result.answers.answerList }" varStatus="status">
                  <tr>
                    <td style="text-align: left;">
                      <small><span style="">${answer.content }</span>&nbsp;&nbsp;</small><br /> 
                      <small>回答者：<span style="">${answer.auser.name }</span>&nbsp;&nbsp;</small>
                      <small>回答时间：<span style=""><fmt:formatDate value="${answer.createTime }" pattern="yyyy-MM-dd HH:mm" /></span>&nbsp;&nbsp;</small> 
                    </span></td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
            <div>
                <form class="form-horizontal" role="form" action="<c:url value="/qa/answerInfoAdd"/>"
                  method="post" onsubmit="return check();">
                  <div class="form-group">
                    <label for="content" class="control-label">我的回答：</label><span id="contentMsg"></span><br /> <br />
                    <div class="col-sm-10" style="width: 100%;">
                      <textarea class="form-control" id="content" name="content" rows="5" style="width: 100%;"></textarea>
                    </div>
                  </div>
                  <input type="hidden" name="quesId" value="${result.quesId }"/>
                  <div class="form-group">
                    <div class="col-sm-offset-5">
                      <button type="submit" class="btn btn-success">提交</button>
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
<script type="text/javascript">
	$(function() {
		$("#qaCenter").addClass("active");
		
	});
	function check(){
        var content = $("#content").val();
        if ($.trim(content) == "") {
            $("#content").blur();
            return false;
        }
        return true;
    }
</script>
</html>
