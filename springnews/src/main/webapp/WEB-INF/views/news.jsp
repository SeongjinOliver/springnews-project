<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "vo.NewsVO, java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
   *{
      font-family: "jua";
   }
   @font-face {
       src: url("/springnews/resources/fonts/BMJUA_ttf.ttf");
       font-family: "jua";
   }
   body{
   		background : url("/springnews/resources/images/travel.jpg") no-repeat fixed 50% 50%/100% 100%; /* 가로 50% 세로 50% 위치에 가로세로 크기 100% */

   }
	a{
		color: blue;
		text-decoration:none;
	}
	a:hover{
		color:red;
		font-weight:bolder;
	}
	input, textarea{
		margin-top:6px;
	}
	div{
		text-align : center;
	}
	div.btn	
	{
		padding-top : 10px;
	    margin: auto;
	    width: 50%;
	}
	button{
		width:100px;
		height:35px;
		background-color:#6699ff;
		color:white;
	}
	h1{
		text-align : center;
	}
	table, button{
		margin-left:auto; 
		margin-right:auto;
	}
	td{
		border-bottom : 2px dotted green;
	}
	tr:hover{
		/* background-color : pink; */	
		/* font-weight : bold; */
	}
	td:nth-child(2){
		width : 300px;
	}
	/* td:nth-child(3){
		width : 400px;
	} */
</style>
<body>
<c:set var="list" value="${ requestScope.list }"/>
<c:set var="one" value="${ requestScope.one }"/>
<c:if test="${!empty list}">

	<h1>성진 company 뉴스 게시판</h1>
	<table>
		<tr>
			<th style="color:blue">번호</th>
			<th style="color:red">제목</th>
			<th style="color:pink">작성자</th>
			<th style="color:#ff9900">작성일</th>
			<th style="color:green">조회수</th>
		</tr>
		<c:forEach var="NewsVO" items="${list}">
			<tr>
		    	<td><c:out value="${NewsVO.newsId}" /></td>
		    	<td>
			    	<a href ='/springnews/newsmain?action=read&newsId=${NewsVO.newsId}'>
			    	<c:out value="${NewsVO.title}" /></a>
		    	</td>
		    	<td><c:out value="${NewsVO.writer}" /></td>
		    	<td><c:out value="${NewsVO.writedate}" /></td>
				<td style="text-align:center;"><c:out value="${NewsVO.cnt}" /></td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<%-- <c:if test="${!empty msg}">
	<script>
		alert('${ msg }');
	</script>
</c:if>
 --%>

<div class="searchsearch">
<form method = "get" action ="/springnews/newsmain">
<input type="hidden" name="action" value="search">
	<select name = "searchType">
	  <option value="title">제목</option>
	  <option value="writer">작성자</option>
	</select>
	<input type = "search" name = "keyword" style="width:250px;"/>
	<input type = "submit" value = "검색">
	
	<!-- <button onclick="location.href='/mvc/news?action=search">뉴스검색</button>

	<input type = "submit" value = "검색">
	<button type="button" onclick="location.href='/mvc/meeting2' ">미팅 스케쥴 보기</button> -->
</form>
</div>
<div class="btn">
	<button onclick="displayDiv(1)">뉴스 작성</button>	
</div>

<div id="write" style="display:none">
	<form method = "post" action = "/springnews/newsmain">
	<input type="hidden" name="action" value="insert">
		<input type="text" name = "writer" placeholder="작성자명을 입력해주세요"/>
		<br>
		<input type="text" name = "title" placeholder="제목을 입력해주세요">
		<br>
		<textarea rows="5" cols="30" name = "content" placeholder="내용을 입력해주세요"></textarea>
		<br>

		<button type="submit" value="Submit">저장</button>
  		<button type="reset" value="Reset">재작성</button>
  		<button type="button" onclick="displayDiv(3);return false;">취소</button>
  	</form>
</div>
<div id="edit" style="display:none">
	<form method = "post" action = "/springnews/newsmain?newsId=${one.newsId}">
	<input type="hidden" name="action" value="update">

		<input type="text" width=300 name = "writer" value="${one.writer}"/>
		<br>
		<input type="text" name = "title" value="${one.title}">
		<br>
		<textarea rows="5" cols="30" name = "content">${one.content}</textarea>
		<br>
		
		<button onclick="displayDiv(3);return false;">확인</button>
  		<button type="submit" >수정</button>
  		<button onclick="location.href='/springnews/newsmain?action=delete&newsId=${one.newsId}';return false;">삭제</button>
  	</form>
</div>

<script>
function displayDiv(type) {
	if(type == 1) {
		document.getElementById("edit").style.display='none';
		document.getElementById("write").style.display='block';
	} else if(type == 2) {
		document.getElementById("write").style.display='none';
		document.getElementById("edit").style.display='block';
	}
	else if(type == 3){
		document.getElementById("write").style.display='none';
		document.getElementById("edit").style.display='none';
	}
}

window.onload = function () {
	var id = <%=request.getParameter("newsId")%>;
	var action = "<%=request.getParameter("action")%>";
	if ( id != null && action== "read") {
		document.getElementById("edit").style.display = 'block';
	}
}

</script>
		
</body>
</html>
