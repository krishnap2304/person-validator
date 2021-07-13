package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/persons-api")
public class PersonController {
	@Autowired
	private PersonRepository personRepository;

	@PostMapping(path="/add")
	public @ResponseBody String addNewPerson (@RequestBody Person person) {

		personRepository.save(person);
		return "Saved";
	}

	@GetMapping(path="/get-all")
	public @ResponseBody Iterable<Person> getAllPersons() {
		return personRepository.findAll();
	}

	@DeleteMapping
	public @ResponseBody void deletePerson(@RequestParam String name) {
		 personRepository.deleteByName(name);
	}

	@GetMapping(path="/is-adult")
	public @ResponseBody Boolean isAdult(@RequestParam Integer age) {
		Optional<Person> person = personRepository.findByAge(age);
		if(person.isPresent()){
			return (person.get().getAge() >= 20);
		}
		return Boolean.FALSE;
	}

	/**
	 *
	 * @param age
	 * @return
	 */
	@GetMapping(path="/is-teenager")
	public @ResponseBody Boolean isTeenager(@RequestParam Integer age) {
		Optional<Person> person = personRepository.findByAge(age);
		if(person.isPresent()){
			return person.get().getAge() >= 11 && person.get().getAge() <= 19;
		}
		return Boolean.FALSE;
	}

	/**
	 *
	 * @param zipcode
	 * @return
	 */
	@GetMapping(path="/validate-zipcode")
	public @ResponseBody boolean isValidZipcode(@RequestParam String zipcode) {
		Optional<Person> person = personRepository.findByZipcode(zipcode);
		if(person.isPresent()){
			return person.get().getZipcode().length() ==5 ;
		}
		return Boolean.FALSE;
	}
}
