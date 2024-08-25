package org.treinamento.springPokemon.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="api/pokemon")
public class PokemonController {

    @GetMapping
    public ResponseEntity<?> pokemonGet(
        @RequestParam(name="id",required=false) Integer id,
        @RequestParam(name="name",required=false) String name
    ){
        StringBuilder url = new StringBuilder("https://pokeapi.co/api/v2/pokemon/");

        if (id != null) {
            url.append(id);
        } else  if (name != null) {
            url.append(name);
        } else {
            return ResponseEntity.badRequest().body("Você deve fornecer um ID ou um nome de Pokémon.");
        }

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url.toString(), String.class);

        return ResponseEntity.ok(response.getBody());
    }
}