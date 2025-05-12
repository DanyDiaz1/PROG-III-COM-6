package dany.ImagesJPAExample.repositories;

import dany.ImagesJPAExample.entities.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagenRepository extends JpaRepository<Imagen,Long> {
    
}
