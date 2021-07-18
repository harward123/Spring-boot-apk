
package com.edwardpinzon.aplicacion.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edwardpinzon.aplicacion.entity.User;



@Repository
public interface UserRepository extends CrudRepository<User, Long>{

  public Optional<User> findByUsername(String username);


}