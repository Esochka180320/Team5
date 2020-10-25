package team5.repositories;

import team5.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser,Long> {


    CustomUser findByEmail(String email);


    boolean existsByEmail(String email);

    boolean existsByName(String name);

    CustomUser findByUuid(String uuid);

    CustomUser findByName(String name);
}
