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
		<form>
			<input type="hidden" id="userId" value="${principal.user.id}" /> <input type='hidden' id="boardId" value="${board.id }" />
			<div class="card-body">
				<textarea id="reply-content" class="form-control" row="1"></textarea>
			</div>
			<div class="card-footer">
				<button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
			</div>
		</form>
	</div>
	<br>
	<div class="card">
		<div class="card-header">댓글 리스트</div>
		<ul id="reply-box" class="list-group">
			<c:forEach var="reply" items="${board.replys}">
				<li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
					<div class="d-flex">
						<div class="font-italic">${reply.user.username }&nbsp;: &nbsp;</div>
						<div>${reply.content}</div>
					</div> <c:if test="${reply.user.id==principal.user.id }">
						<button onClick="index.replyDelete(${board.id}, ${reply.id})" calss="badge">삭제</button>
					</c:if>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>

