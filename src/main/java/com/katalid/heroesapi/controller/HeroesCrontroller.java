package com.katalid.heroesapi.controller;

import com.katalid.heroesapi.constants.HeroesConstant;
import com.katalid.heroesapi.document.Heroes;
import com.katalid.heroesapi.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.katalid.heroesapi.constants.HeroesConstant.HEROES_ENDPOINT_LOCAL;

@Slf4j
@RestController
@RequestMapping(HEROES_ENDPOINT_LOCAL)
public class HeroesCrontroller {

    @Autowired
    private HeroesService heroesService;

    @GetMapping
    public Flux<Heroes> getAllHeroes(){
        log.info("requesting the list off all heroes");
        return heroesService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Heroes>> findHeroById(@PathVariable String id){
        log.info("requesting the hero with id {}",id);
        return heroesService.findById(id)
                .map((item)-> new ResponseEntity<>(item,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Mono<ResponseEntity<Heroes>> createHero(@RequestBody Heroes heroes){
        log.info("a new hero was created with name: {}", heroes.getName());
        return heroesService.save(heroes)
                .map(item -> new ResponseEntity<>(item,HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_GATEWAY));
    }

    @DeleteMapping("/{id}")
    public Mono <HttpStatus> deleteById(@PathVariable String id){
        log.info("deleting a Hero with id: {}",id);
        heroesService.deleteById(id);
        return Mono.just(HttpStatus.NOT_FOUND);
    }
}
