package com.edwardpinzon.aplicacion.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edwardpinzon.aplicacion.entity.User;



@Repository
public interface UserRepository extends CrudRepository<User, Long>{


}