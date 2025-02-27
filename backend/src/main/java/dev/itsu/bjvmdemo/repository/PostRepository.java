package dev.itsu.bjvmdemo.repository;

import dev.itsu.bjvmdemo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {}
