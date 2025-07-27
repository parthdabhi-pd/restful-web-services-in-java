package com.rest.webservices.restful_web_services.jpa;

import com.rest.webservices.restful_web_services.socialMedia.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
