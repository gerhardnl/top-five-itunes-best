package com.example.topfiveitunesbest.resource;


import com.example.topfiveitunesbest.data.DataFromApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/g/artist")
public class Resource {
    List<String> favoriteArtists = new ArrayList<>();

    @GetMapping("/top five")
    public List topFive(String artist) throws IOException {
        if (favoriteArtists.contains(artist.toLowerCase())) {
            return DataFromApi.toObject(artist);
        } else {
            return Collections.singletonList("this artist is not i your list of favorites");
        }
    }

    @GetMapping("/favorite")
    public List favorites(String artist) throws IOException {
        if (DataFromApi.toObject(artist) != null) {
            if (!favoriteArtists.contains(artist)) {
                favoriteArtists.add(artist.toLowerCase());
            }

        } else {
            return Collections.singletonList("not valid search");
        }
        return favoriteArtists;
    }

    @GetMapping("/Delete")
    public List Delete(String artist) throws IOException {
        if (favoriteArtists.contains(artist.toLowerCase())) {
            favoriteArtists.remove(artist.toLowerCase());
            return favoriteArtists;
        } else {
            return Collections.singletonList("this artist is not i your list of favorites");
        }
    }
}
