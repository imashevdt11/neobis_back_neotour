package kg.neobis.neobis_back_neotour.repositories;

import kg.neobis.neobis_back_neotour.entities.Image;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
}