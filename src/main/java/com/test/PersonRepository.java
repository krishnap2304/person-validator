package com.test;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Integer> {

  Optional<Person> findByName(String name);
  Optional<Person> findByAge(Integer age);
  Optional<Person> findByZipcode(String zipcode);
  Optional<Person> findByEmail(String email);
  void deleteByName(String name) ;


}
