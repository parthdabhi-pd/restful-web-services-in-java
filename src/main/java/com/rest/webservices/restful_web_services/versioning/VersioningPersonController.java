package com.rest.webservices.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    // URI versioning
//    http://localhost:8080/v1/person
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson () {
        return new PersonV1("Krishna");
    }

//    http://localhost:8080/v2/person
    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson () {
        return new PersonV2(new Name("Radha", "Krishna"));
    }

    // Parameter versioning
//    http://localhost:8080/person?version=1
    @GetMapping(path="/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParam() {
        return new PersonV1("Krishna");
    }

//    http://localhost:8080/person?version=2
    @GetMapping(path="/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParam() {
        return new PersonV2(new Name("Radha", "Krishna"));
    }

    // Header versioning

//    http://localhost:8080/person/header
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonHeader() {
        return new PersonV1("Krishna");
    }

//    http://localhost:8080/person/header
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonHeader() {
        return new PersonV2(new Name("Radha", "Krishna"));
    }

    // Media type versioning (a.k.a “content negotiation” or “accept header”) - GitHub

//    http://localhost:8080/person/accept
    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonAccept() {
        return new PersonV1("Krishna");
    }

//    http://localhost:8080/person/accept
    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonAccept() {
        return new PersonV2(new Name("Radha", "Krishna"));
    }

}
