package io.manojlearns.springboot_blog_webapp.repository;

import io.manojlearns.springboot_blog_webapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
