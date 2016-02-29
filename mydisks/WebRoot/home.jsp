<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>信息工程学院网盘</title>
	<link rel="shortcut icon" href="images/logo_small.png">
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/bootstrap.css">
	<script type="text/javascript" src='<c:url value="/js/jquery.min.js"></c:url>'></script>
</head>
<script type="text/javascript">
	
	$(function(){
			$("tbody tr td[jq='true']").find("a:last").click(function(){
			
				setTimeout(function(){
				
				location.href = "<c:url value='CatalogServlet?method=myCatalog&cid=${catalog.cId}' />";
				},1000);
		
			});
	$("tbody tr td[jq='true']").find("a:first").click(function(){
	
		var iids=$(this).attr("iid");
		
		$.ajax({
			url:"/mydisks/FileServlet",
			data:{method:"deleteFile",fid:iids},
			type:"POST",
			dataType:"json",
			async:false,
			cache:false,
			success:function(result){
				if(result){
					alert("删除成功！");
					$("."+iids).remove();
					if($(".abc").length==0){
						$("#msg").text("没有了");
				}
				}else{
					alert("删除失败");
				}
			}
			
		});
		
	});
		
	
	
	
	})
	



</script>
<body>
	<div class="top" id="all">
		<!--顶部导航-->
		<div class="top_nav">
			<nav class="navbar navbar-dark bg-inverse">
				<div class="nav_width">
					<a class="navbar-brand" href="#"> <img src="images/logo1.png" />
						<p>信息工程学院网盘</p>
					</a>
					<ul class="nav navbar-nav pull-right">
					<!-- <li class="nav-item active"><a class="nav-link" href="#">主页<span
					class="sr-only">(current)</span></a></li> -->
					<li class="nav-item"><a class="nav-link"
						href="<c:url value='/CatalogServlet?method=myCatalog' />">我的文件<span
						class="sr-only">(current)</span></a></li>
						
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/SharedServlet?method=myShared' />">我的分享</a></li>
								
							<li class="nav-item"><a class="nav-link"
								href="<c:url value='/SharedServlet?method=myShared' />">公共分享</a></li>
								<li class="nav-item"><a class="nav-link"
									href="<c:url value='/User/UserServlet?method=quit' />">退出登录</a></li>
								</ul>
							</div>
						</nav>
					</div>
					<div class="leftnav">
						<div class="container-fluid">
							<div class="row">
								<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
									<div class="admin">
										<img src="images/user.jpg">
										<p>${sessionScope.session_user.username}</p>
										<table class="table">
											<tr><td>分享</td><td>订阅</td><td>粉丝</td></tr>
											<tr><td><a href="<c:url value='/SharedServlet?method=myShared' />">${sessionScope.session_user.shareCount }</a></td>
											<td><a href="<c:url value='/FollowServlet?method=myFollow' />">${sessionScope.session_user.followcount }</a></td>
											<td><a href="<c:url value='/FollowServlet?method=myFans' />">${sessionScope.session_user.follower}</a></td></tr>
										</table>
									</div>
								</div>
								<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
									<!--右侧内容区域-->
									<div class="rightarea">
										<div class="righthead">
											<p>我的文件</p>
											<button onclick="upload()" 	type="button"
											class="btn btn-primary-outline btn-sm" >
											<span><i
												class="glyphicon glyphicon glyphicon-circle-arrow-up"></i></span>
												上传文件
											</button>
											<span id="filepath">
											
											</span>
											</br>
											<button onclick="prom()" type="button"
											class="btn btn-primary-outline btn-sm">
											<span><i
												class="glyphicon glyphicon glyphicon-circle-arrow-up"></i></span>
												新建文件夹
											</button>
											<p></p>
											<div class="myprogress clearfix" id="myprogress" style="display:none;">
                     							<progress class="progress progress-striped progress-info progress-animated" value="0" max="100"></progress>
                     						 	<p class="myprogress_text" >0</p>
                   						 	</div>
										</div>
										<a href="<c:url value='/CatalogServlet?method=myCatalog' />">我的文件</a>>>
										<c:forEach items="${path}" var="p">
										<a
										href="<c:url value='/CatalogServlet?method=findCatalogByPid&pid=${p.id }' /> ">${p.name}</a>>></c:forEach>
										
										<table class="table fp">
											<tr>
												<td></td>
												<td>文件名</td>
												<td>修改日期</td>
												<td>大小</td>
												<td>类型</td>
												<td>下载次数</td>
												<td>操作</td>
											</tr>
										<c:choose>
											<c:when test="${ empty catalog.children  and  empty catalog.myFile  }">
													<tr>
														<td>对不起，没有文件了</td>
													</tr>
											</c:when>
											
											<c:otherwise>
											
													<c:forEach items="${catalog.children }" var="c">
											<tr>
												<td><img width="50" height="30" src='<c:url value="/images/1024.png"></c:url>' /></td>
													
												<td><a href='<c:url value="/CatalogServlet?method=myCatalog&cid=${c.cId }"></c:url>'>${c.cName }</a></td>	
												<td>${c.cDate }</td>
												<td></td>
												<td></td>
												<td></td>
												<td><a href='<c:url value="/CatalogServlet?method=deleteCatalog&cid=${c.cId }&pid=${c.parent.cId }"></c:url>'>删除</a><a>重命名</a></td>
											</tr>
											</c:forEach>
										
										<c:forEach items="${catalog.myFile }" var="f">
											<tr class="${f.fId } abc">
												<td><img width="40" height="20" src='<c:url value="/images/02.png"></c:url>' /></td>
												<td>${f.fName }</td>	
												<td>${f.fUploadtime }</td>
												<td><fmt:formatNumber type="number" value="${f.fSize/1048576}" maxFractionDigits="4"/>MB</td>
												<td>${f.fType }</td>
												<td>${f.fDowncount }</td>
												<td jq="true" ><a href="#" iid=${f.fId }>删除</a><a>重命名</a> <a href='<c:url value="/DownLoadServlet?fid=${f.fId }&cid=${c.cId }"></c:url>'>下载</a></td>
<!-- 											</tr> -->
										
										
										</c:forEach>
											
											</c:otherwise>
										
										</c:choose>
										
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--底部-->
	<div class="footer">
		<p>Copyright &copy; 2015 信息工程学院版权所有</p>
	</div>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery.min.js"></script>
	<script type="text/javascript">
	window.onload=function()
	{
		var obj2 = document.getElementById("uploadform").children;
		obj2[0].value="";
	}
		function shared(id, name) {
			var s = confirm("是否将" + name + "公开分享,点否为私密分享");
			if (s) {
				location.href = "<c:url value='SharedServlet?method=addShared' />&id="
				+ id + "&isPublic=true";
			} else {
				location.href = "<c:url value='SharedServlet?method=addShared' />&id="
				+ id + "&isPublic=false";
			}
		}
	
		function rename(id, name, pid) {
			var name = prompt("将" + name + "重命名为:", name);
			if (name === "") {
				alert("文件名不能为空");
			}
			if (name) {
				location.href = "<c:url value='CatalogServlet?method=rename' />&id="
				+ id + "&name=" + name + "&pid=" + pid;
			}
		}
	
		function download(fid, name) {
			location.href = "<c:url value='DownloadServlet' />?filename="
			+ name + "&fid=" + fid;
		}
	
		function prom()
	
		{
			var name = prompt("请输入文件夹名字", "新建文件夹");
	
			if (name === "") {
				alert("文件名不能为空");
			}
	
				if (name)//如果返回的有内容
				{
					location.href = "<c:url value='CatalogServlet?method=createCatalog&cid=${catalog.cId}' />&name="
					+ encodeURI(encodeURI(name));
				}
	
			}
	
			function deleteFile(name, id, pid) {
				var r = confirm("是否删除" + name);
				if (r == true) {
					location.href = "<c:url value='CatalogServlet?method=removeCatalogById' />&id="
					+ id + "&pid=" + pid;
				}
			}
	
			function upload() {
				var obj = document.getElementById("uploadform").children;
				
				if (obj[0].value === "") {
					obj[0].click();
					
				} else {
					obj[3].click();
					$('#myprogress').show();
					window.setTimeout("getProgressBar()", 100);
				}
			}
			function fileName()
			{
			var obj1= document.getElementById("uploadform").children;
			var inf = document.getElementById('filepath');
			var Name=obj1[0].value;
			inf.innerHTML="您要上传的文件名是:"+Name;
			}
			//进度条
			function getProgressBar(){
				var timestamp = (new Date()).valueOf();
				$.getJSON("<c:url value='/ProgressServlet' />",function(json){
					var myprogress = $('#myprogress').children(".progress");
					var myprogress_text = $('#myprogress').children(".myprogress_text");	
					myprogress.attr('value',function(){
						var value = json.pBytesRead/json.pContentLength*100;
						myprogress_text.text((value+"").substring(0, 4)+"%");
						
						return value;
					});
					if(json.pBytesRead == json.pContentLength){
						
					//	location.href = "<c:url value='CatalogServlet?method=myCatalog&cid=${catalog.cId}' />";
					}else{
						window.setTimeout("getProgressBar()", 100);
					}
				});
			}
			$(function() {
				var msg = "${msg}";
				if (!msg == "")
					alert(msg);
				
				var fileicontype=$("td>p.icontype");
				var foldericontype=$("a>p.icontype");
				foldericontype.each(function(index,element){
					element.className="folder";
				});
				fileicontype.each(function(index,element){
					element.className=checktype(element.innerText);
				});
				
				function checktype(filename){
					filename=filename.toLocaleLowerCase();
					var b=filename.split('.');
					if(b.length<2){
						return "others";
					}
					var lastword = b[b.length-1];
					switch(lastword){
						case "exe":
							return "exe";
							break;
						case "bmp":
						case "gif":
						case "jpeg":
						case "png":
						case "jpg":
							return "jpg";
							break;
						case "apk":
							return "apk";
							break;
						case "wav":
						case "mp3":
						case "wma":
							return "mp3";
							break;
						case "zip":
						case "rar":
						case "7z":
							return "zip";
							break;
						case "mp4":
						case "avi":
						case "wmv":
						case "rmvb":
							return "mp4";
							break;
						default:
							return "others";
							break;
					}
				}
		});
			</script>
			<form id="uploadform" action="<c:url value='UploadServlet' />" method="post" enctype="multipart/form-data" style="display:none;">
			<input type="file" name="filename" onChange="fileName()" /><br /> 
			<input type="hidden" name="cid" value="${catalog.cId }" />
			<input type="submit" value="上传">
		</form>
	</body>