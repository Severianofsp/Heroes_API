package com.katalid.heroesapi.service;

import com.katalid.heroesapi.document.Heroes;
import com.katalid.heroesapi.repository.HeroesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroesService {

    @Autowired
    private HeroesRepository heroesRepository;

    public Flux<Heroes> findAll(){
        return Flux.fromIterable(this.heroesRepository.findAll());
    }

    public Mono<Heroes> findById(String id){
        return Mono.justOrEmpty(heroesRepository.findById(id));
    }

    public Mono<Heroes> save(Heroes heroes){
        return Mono.justOrEmpty(heroesRepository.save(heroes));
    }

    public Mono<Boolean> deleteById(String id){
        heroesRepository.deleteById(id);
        return Mono.just(true);
    }
}
