package com.polarisdigitech.backendchallenge;

import com.polarisdigitech.backendchallenge.model.Game;
import com.polarisdigitech.backendchallenge.model.GameDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.modelmapper.ModelMapper;

@SpringBootTest
@Slf4j
class BackendChallengeApplicationTests {
	private ModelMapper modelMapper;

	@BeforeEach
	public void setUp(){
		this.modelMapper = new ModelMapper();
	}

	@Test
	void contextLoads() {
	}
	public void whenMapGameWithExactMatch_thenConvertsToDTO(){
		//when similiar source object is provided
		Game game = new Game();
		GameDTO gameDTO = this.modelMapper.map(game,GameDTO.class);
		log.info("gameDTO=={}",gameDTO);
		log.info("game=="+game);

	}

}
