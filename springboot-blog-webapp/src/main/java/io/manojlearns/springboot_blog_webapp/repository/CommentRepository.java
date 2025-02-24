package io.manojlearns.springboot_blog_webapp.repository;

import io.manojlearns.springboot_blog_webapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

//@Repository //this is not required as JpaRepository contains simpleJpaRepository and it has @Repository annotation
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select c.* from comments c inner join posts p\n"+
                    "where c.post_id=p.id and p.created_by=:userId;", nativeQuery=true)
    List<Comment> findCommentsByPost(Long userId);

}
