package ru.kpfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.entity.Travel;

import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {
    List<Travel> findByUserId(Long userId);
}