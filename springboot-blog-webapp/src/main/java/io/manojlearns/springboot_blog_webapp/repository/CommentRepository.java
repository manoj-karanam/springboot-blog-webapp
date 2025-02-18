package io.manojlearns.springboot_blog_webapp.repository;

import io.manojlearns.springboot_blog_webapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository //this is not required as JpaRepository contains simpleJpaRepository and it has @Repository annotation
public interface CommentRepository extends JpaRepository<Comment, Long> {


}
