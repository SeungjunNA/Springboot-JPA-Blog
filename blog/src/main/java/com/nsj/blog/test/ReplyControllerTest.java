package com.nsj.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nsj.blog.model.Board;
import com.nsj.blog.repository.BoardRepository;

@RestController
public class ReplyControllerTest {
	
	@Autowired
	private BoardRepository boardRespoitory;

	@GetMapping("/test/board/{id}")
	public Board getBoard(@PathVariable int id) {
		return boardRespoitory.findById(id).get(); // jackson 라이브러리 (오브젝트를 json으로 리턴) => 모델의 getter를 호출
	}
}