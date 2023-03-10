package com.nsj.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nsj.blog.model.RoleType;
import com.nsj.blog.model.User;
import com.nsj.blog.repository.UserRepository;

// html파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {

	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			return "삭제에 실해하였습니다. 해당 id는 DB에 없습니다.";
		}
		return "삭제되었습니다. id : "+id;
	}
	
	
	// save함수는 id를 전달하지 않으면 insert해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해줌.
	// email, password
	@Transactional //함수 종료시 자동 commit //더티체킹
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) { // json 데이터를 요청 => Java Object(MessageConverter의 Jackson라이브러리가 변환해서 받아줌.)
		System.out.println("id : "+ id);
		System.out.println("password : "+ requestUser.getPassword());
		System.out.println("email : "+ requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		
		return user;
	}
	
	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한페이지당 2건에 데이터를 리턴 받을 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> paginUser = userRepository.findAll(pageable);
		List<User> users = paginUser.getContent();
		return users;
	}
	
	//{id} 주소로 파라메터를 전달 받을 수 있음.
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4을 찾으면 내가 데이터베이스에서못찾으면 user는 null이 됨.
		// 그럼 return null 이된다. 그럼 프로그램에 문제가 있음.
		// Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해라.
		
		//람다식
		//User user = userRepository.findById(id).orElseThrow(()->{
		//		return new IllegalArgumentException("해당 사용자는 없음.");
		//});
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
		@Override
		public IllegalArgumentException get() {
			// TODO Auto-generated method stub
			return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
			}
		});
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환 (웹브라우저가 이해해 할 수 있는 데이터) -> json
		// 스프링부트는  MessageConverter가 응답시 자동 작동
		// 만약에 자바 오브젝트를 리턴하게되면 MessageConverter가 Jackson라이브러리를  호출해서
		//User 오브젝트를 json으로 변환해서 브라우저에게 준다.
		return user;
	}
	
	//http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join(User user) { // key = value
		System.out.println("id : "+user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("username : "+user.getUsername());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
