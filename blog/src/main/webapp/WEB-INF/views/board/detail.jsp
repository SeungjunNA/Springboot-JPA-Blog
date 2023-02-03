<%@ page language="java" contentType="text/html; charset=UTF-16" pageEncoding="UTF-16"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">

	<button class="btn btn-secondary" onclick="history.back( )">돌아가기</button>
	<c:if test="${board.user.id==principal.user.id }">
		<button id="btn-delete" class="btn btn-danger">삭제</button>
		<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
	</c:if>
	<br> <br>
	<div>
		글 번호 : <span id="id"><i>${board.id } </i></span> 작성자 : <span><i>${board.user.username } </i></span>
	</div>
	<br>
	<h3>${board.title }</h3>
	<hr>
	<div>${board.content }</div>
	<hr>

	<div class="card">
		<div class="card-body">
			<textarea class="form-control" row="1"></textarea>
		</div>
		<div class="card-footer">
			<button class="btn btn-primary">등록</button>
		</div>
	</div>
	<br>
	<div class="card">
		<div class="card-header">댓글 리스트</div>
		<ul id="comment--box" class="list-group">
			<li id="comment--1" class="list-group-item d-flex justify-content-between">
				<div class="d-flex">
					<div class="font-italic">작성자 :  &nbsp</div>
					<div>댓글 내용</div>
				</div>
				<button calss="badge">삭제</button>
			</li>
		</ul>
	</div>
</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>

