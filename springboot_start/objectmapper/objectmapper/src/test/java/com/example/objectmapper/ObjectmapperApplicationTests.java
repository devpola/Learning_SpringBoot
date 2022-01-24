package com.example.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectmapperApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {

		//controller 외부에서 object와 json(text)간 변환이 필요한 경우 아래와 같이 직접 사용할 수 있음
		System.out.println("object mapper test");

		//object mapper
		// req json(text) -> object
		// res object -> json(text)

		var objectMapper = new ObjectMapper();

		//object -> text
		//object mapper는 get method를 활용하기 때문에 object에 해당하는 class에 getter 선언되어 있어야함
		var user = new User("steve", 21, "010-1111-1111");
		var text = objectMapper.writeValueAsString(user);
		System.out.println(text);

		//text -> object
		//이 과정에서 object mapper는 object에 해당하는 class의 default 생성자를 필요로한다.
		var objectUser = objectMapper.readValue(text, User.class);
		System.out.println(objectUser);
	}

}
