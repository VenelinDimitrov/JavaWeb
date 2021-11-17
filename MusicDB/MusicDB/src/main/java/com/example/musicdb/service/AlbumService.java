package com.example.musicdb.service;

import com.example.musicdb.model.entity.Album;
import com.example.musicdb.model.service.AlbumServiceModel;

import java.util.Set;

public interface AlbumService {
    AlbumServiceModel addAlbum(AlbumServiceModel albumServiceModel);

    Set<Album> findAllAlbumsForUser(Long id);

    void deleteAlbumById(Long id);
}
