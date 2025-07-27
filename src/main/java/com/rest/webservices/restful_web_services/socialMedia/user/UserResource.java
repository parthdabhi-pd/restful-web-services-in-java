package com.rest.webservices.restful_web_services.socialMedia.user;

import com.rest.webservices.restful_web_services.jpa.PostRepository;
import com.rest.webservices.restful_web_services.jpa.UserRepository;
import com.rest.webservices.restful_web_services.socialMedia.post.Post;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserResource {

    private UserRepository userRepository;
    private PostRepository postRepository;

    // wiring
    public UserResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // retrieveAllUsers
    // GET /users
    // output - List<User>
//    http://localhost:8080/users
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    // retrieveUser(int id)
    // GET /users/{id}
    // output - User
//    http://localhost:8080/users/1
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveOneUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id : " + id);
        }

        // concept applied - HATEOAS
        // adding the link that, retrieve all users, when someone finding one user!
        EntityModel<User> entityModel = EntityModel.of(user.get()); // wrapping user into EntityModel
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link1.withRel("all_users"));

//        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).deleteById(id));
//        entityModel.add(link2.withRel("delete_user"));

        return entityModel;
    }

    // createUser(User user)
    // input - details of user
    // output - CREATED & Return the created URI
//    http://localhost:8080/users
    // POST
    // input - details of user
    // sending response status as CREATED - 201
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        // sending response status as CREATED - 201
        return ResponseEntity.created(location).build();
    }

    // deleteUser(int id)
    // DELETE /users/{id}
    //    http://localhost:8080/users/5
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteById (@PathVariable int id) {
        if(userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException("id = " + id + ", not exits!");
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok(null); // Return 200
    }

    // getAllPostsOfUser(int id)
    //    http://localhost:8080/users/1/posts
    @GetMapping("/users/{id}/posts")
    public List<Post> getAllPostsOfUser (@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id : " + id);
        }

        return user.get().getPosts();
    }

    //    http://localhost:8080/users/1/posts
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser (@PathVariable int id, @RequestBody @Valid Post post) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id : " + id);
        }

        post.setUser(user.get());

        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}