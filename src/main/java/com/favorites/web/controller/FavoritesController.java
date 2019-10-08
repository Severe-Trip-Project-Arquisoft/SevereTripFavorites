package com.favorites.web.controller;

import com.favorites.web.entity.Favorites;
import com.favorites.web.service.FavoritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites")
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;

    @GetMapping
    public String msg(){
        return "CLIENTS AVAILABLE";
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Favorites>> getAllFavorites(@PathVariable(value = "id") String clientId){
        return ResponseEntity.ok(favoritesService.getAllFavorites(clientId));
    }

    @PostMapping(path ="/", consumes = "application/json")
    public ResponseEntity<Long> createClient(@RequestBody Favorites favorites) {
            Favorites cli = favoritesService.getFavorite(favorites.getId());
            if (cli==null){
                favoritesService.createFavorite(favorites);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    }

    @DeleteMapping(path ="/{id}")
    public ResponseEntity<Long> deleteFavorite(@PathVariable(value = "id") long FavoriteId) {
        Favorites cli = favoritesService.getFavorite(FavoriteId);
        if (cli!=null){
            favoritesService.deleteFavorite(cli);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
