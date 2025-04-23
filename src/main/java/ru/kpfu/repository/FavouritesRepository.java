package ru.kpfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.entity.Favourites;
import ru.kpfu.entity.FavouritesId;
import ru.kpfu.entity.User;

import java.util.List;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, FavouritesId> {
    List<Favourites> findByUser(User user);
}