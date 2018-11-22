package br.com.polizello.SpringBoot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.polizello.SpringBoot.entities.Person;
import br.com.polizello.SpringBoot.repositories.PersonRepository;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value="/person")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/getPersons")
	public List<Person> getPersons() {
		return personRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public void savePerson(@RequestBody Person person) {
		try {
			personRepository.save(person);
		} catch (Exception err) {
			throw err;
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/delete")
	public void deletePerson(@RequestParam Long id) {
		try {
			personRepository.deleteById(id);
		} catch (Exception err) {
			throw err;
		}
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/getPerson")
	public Person getPerson(@RequestParam Long id) {
		return personRepository.findById(id).orElse(null);
	}
}
