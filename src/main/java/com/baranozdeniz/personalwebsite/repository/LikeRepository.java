package com.baranozdeniz.personalwebsite.repository;

import com.baranozdeniz.personalwebsite.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Like, UUID> {



}