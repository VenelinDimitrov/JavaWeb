package com.example.musicdb.repository;

import com.example.musicdb.model.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Set<Album> findAllByAddedFrom_Id(Long id);
}
