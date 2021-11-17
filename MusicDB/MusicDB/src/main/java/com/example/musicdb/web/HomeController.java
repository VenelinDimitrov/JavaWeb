package com.example.musicdb.web;

import com.example.musicdb.model.entity.Album;
import com.example.musicdb.model.service.AlbumServiceModel;
import com.example.musicdb.sec.CurrentUser;
import com.example.musicdb.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Set;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final AlbumService albumService;

    public HomeController(CurrentUser currentUser, AlbumService albumService) {
        this.currentUser = currentUser;
        this.albumService = albumService;
    }

    @GetMapping()
    public String index(AlbumServiceModel albumServiceModel, Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }

        Set<Album> allAlbumsForUser = albumService.findAllAlbumsForUser(currentUser.getId());

        int totalSoldAlbums = allAlbumsForUser.stream().map(Album::getCopies)
                        .reduce(Integer::sum).orElse(0);

        model.addAttribute("userAddedAlbums", allAlbumsForUser);
        model.addAttribute("albumServiceModel", albumServiceModel);
        model.addAttribute("totalSoldAlbums", totalSoldAlbums);

        return "home";
    }

    @ModelAttribute
    public AlbumServiceModel albumServiceModel() {
        return new AlbumServiceModel();
    }
}
