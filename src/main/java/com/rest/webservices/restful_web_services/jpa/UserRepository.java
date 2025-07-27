//                                              "જય શ્રી ગણેશ"

/*
    A DAO service is responsible for providing CRUD operations and interacting with the database.

    DAO by extending JpaRepository to provide CRUD operations for User entities.

    -> it is same as UserRepository
*/

package com.rest.webservices.restful_web_services.jpa;

import com.rest.webservices.restful_web_services.socialMedia.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    // we can add more methods here if we want to perform more operations on User entity.
}
