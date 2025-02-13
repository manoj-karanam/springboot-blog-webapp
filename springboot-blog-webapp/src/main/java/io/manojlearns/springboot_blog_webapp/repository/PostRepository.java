package io.manojlearns.springboot_blog_webapp.repository;

import io.manojlearns.springboot_blog_webapp.entity.Post;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByUrl(String url);

    Optional<Post> findPostByUrl(String postUrl);

//    void deleteById(Long Id);
}
