package com.polarisdigitech.backendchallenge.helpers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.polarisdigitech.backendchallenge.model.Game;
import com.polarisdigitech.backendchallenge.model.GameDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;

@Slf4j
public class Helpers {

    private ModelMapper modelMapper;
    public Helpers() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }
    public <S,T> List mapList(List<S> source, Class<T> targetClass){
        return  source
                .stream()
                .map(s -> modelMapper.map(s, targetClass))
                .collect(Collectors.toList());
    }
    public void myMapping() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        List<String> characters = new ArrayList<>();
        modelMapper.map(integerList,new TypeToken<List<Character>>(){}.getType());
        log.info("Mapped Results::{}",characters);
        TypeMap<Game, GameDTO> typeMapPropertyMapper = this.modelMapper.createTypeMap(Game.class, GameDTO.class);
        typeMapPropertyMapper.addMapping(Game::getTimestamp, GameDTO::setCreationTime);
        Game game = new Game(1L,"Ciga Gufu",123456L,Arrays.asList("A","B","C"),new HashMap<>());
        game.setTimestamp(Instant.now().getEpochSecond());
        GameDTO gameDTO = modelMapper.map(game,GameDTO.class);
        log.info("game=="+game);
        log.info("gameDTO=={}",gameDTO);

    }
}
