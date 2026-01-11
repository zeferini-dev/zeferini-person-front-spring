package com.zeferini.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeferini.config.ApiConfig;
import com.zeferini.model.Person;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PersonService {
    private final WebClient queryClient;
    private final WebClient commandClient;
    private final ObjectMapper objectMapper;

    public PersonService(ApiConfig apiConfig, ObjectMapper objectMapper) {
        this.queryClient = WebClient.create(apiConfig.getQuery().getUrl());
        this.commandClient = WebClient.create(apiConfig.getCommand().getUrl());
        this.objectMapper = objectMapper;
    }

    public List<Person> getAllPersons() {
        try {
            log.info("Fetching all persons from Query API");
            String response = queryClient.get()
                    .uri("/persons")
                    .retrieve()
                    .onStatus(status -> !status.is2xxSuccessful(), 
                        clientResponse -> clientResponse.bodyToMono(String.class)
                            .doOnNext(body -> log.error("API returned error: {}", body))
                            .flatMap(body -> Mono.error(new RuntimeException("API Error: " + body))))
                    .bodyToMono(String.class)
                    .block();

            log.info("Response from Query API: {}", response);
            List<Person> persons = objectMapper.readValue(response, new TypeReference<List<Person>>() {});
            log.info("Successfully parsed {} persons", persons.size());
            return persons;
        } catch (Exception e) {
            log.error("Error fetching persons: {}", e.getMessage(), e);
            return List.of();
        }
    }

    public Person getPersonById(String id) {
        try {
            return queryClient.get()
                    .uri("/persons/{id}", id)
                    .retrieve()
                    .bodyToMono(Person.class)
                    .block();
        } catch (Exception e) {
            log.error("Error fetching person with id: {}", id, e);
            return null;
        }
    }

    public Person createPerson(Person person) {
        try {
            // Create a simple Map with only the fields expected by the API
            Map<String, String> requestBody = Map.of(
                "name", person.getName(),
                "email", person.getEmail()
            );
            
            log.info("Creating person with data: {}", requestBody);
            
            return commandClient.post()
                    .uri("/persons")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Person.class)
                    .block();
        } catch (Exception e) {
            log.error("Error creating person", e);
            throw new RuntimeException("Error creating person", e);
        }
    }

    public Person updatePerson(String id, Person person) {
        try {
            // Create a simple Map with only the fields expected by the API
            Map<String, String> requestBody = Map.of(
                "name", person.getName(),
                "email", person.getEmail()
            );
            
            log.info("Updating person {} with data: {}", id, requestBody);
            
            return commandClient.patch()
                    .uri("/persons/{id}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Person.class)
                    .block();
        } catch (Exception e) {
            log.error("Error updating person with id: {}", id, e);
            throw new RuntimeException("Error updating person", e);
        }
    }

    public void deletePerson(String id) {
        try {
            commandClient.delete()
                    .uri("/persons/{id}", id)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        } catch (Exception e) {
            log.error("Error deleting person with id: {}", id, e);
            throw new RuntimeException("Error deleting person", e);
        }
    }
}
