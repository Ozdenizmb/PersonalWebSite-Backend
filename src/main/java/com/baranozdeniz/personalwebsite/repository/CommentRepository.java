package com.baranozdeniz.personalwebsite.repository;

import com.baranozdeniz.personalwebsite.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    Page<Comment> findAllByProjectId(UUID projectId, Pageable pageable);
    Page<Comment> findAllByUserId(UUID userId, Pageable pageable);
    List<Comment> findAllByProjectId(UUID projectId);

}
