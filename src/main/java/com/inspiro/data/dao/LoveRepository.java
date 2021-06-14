package com.inspiro.data.dao;

import com.inspiro.data.entity.Love;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoveRepository extends JpaRepository<Love, Long> {
}
