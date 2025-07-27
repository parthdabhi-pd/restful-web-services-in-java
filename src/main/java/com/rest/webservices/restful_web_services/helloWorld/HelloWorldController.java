package com.rest.webservices.restful_web_services.helloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // http://localhost:8080/hello-world-bean
    @GetMapping("/hello-world-bean")
    public HelloWorldBean getHelloWorldJSON() {
        return new HelloWorldBean("Hello Radhe Radhe");
    }

    // Path Parameters
    // /users/{id}/todos/{id}  => /users/2/todos/200
    // /hello-world/path-variable/{name}
    // /hello-world/path-variable/Krishna

    // http://localhost:8080/hello-world/path-variable/Krishna
    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable (@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello, %s", name));
    }
}
