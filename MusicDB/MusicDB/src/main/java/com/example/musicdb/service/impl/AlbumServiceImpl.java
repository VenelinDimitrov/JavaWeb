package com.example.musicdb.service.impl;

import com.example.musicdb.model.entity.Album;
import com.example.musicdb.model.service.AlbumServiceModel;
import com.example.musicdb.repository.AlbumRepository;
import com.example.musicdb.repository.ArtistRepository;
import com.example.musicdb.repository.UserRepository;
import com.example.musicdb.sec.CurrentUser;
import com.example.musicdb.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public AlbumServiceImpl(AlbumRepository albumRepository, ArtistRepository artistRepository, UserRepository userRepository, CurrentUser currentUser, ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public AlbumServiceModel addAlbum(AlbumServiceModel albumServiceModel) {
        Album album = modelMapper.map(albumServiceModel, Album.class);
        album.setArtist(artistRepository.findByName(albumServiceModel.getArtist().getName()).orElse(null));
        album.setAddedFrom(userRepository.findById(currentUser.getId()).orElse(null));
        albumRepository.save(album);

        return modelMapper.map(album, AlbumServiceModel.class);
    }

    @Override
    public Set<Album> findAllAlbumsForUser(Long id) {
        return albumRepository.findAllByAddedFrom_Id(id);
    }

    @Override
    public void deleteAlbumById(Long id) {
        albumRepository.deleteById(id);
    }
}
