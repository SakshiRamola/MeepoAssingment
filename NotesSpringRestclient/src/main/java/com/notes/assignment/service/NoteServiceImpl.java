package com.notes.assignment.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.notes.assignment.model.NoteDTO;

@Service
public class NoteServiceImpl {
    @Value("${resource.notes}")
    private String resource;
    @Value("${resource.notes}/{id}")
    private String idResource;
    @Autowired
    private RestTemplate restTemplate;

    public List<NoteDTO> findAll() {
        return Arrays.stream(restTemplate.getForObject(resource, NoteDTO[].class)).collect(Collectors.toList());
    }

    public NoteDTO update(Long id, NoteDTO note) {
        return restTemplate.exchange(idResource, HttpMethod.PUT, new HttpEntity<>(note), NoteDTO.class, id).getBody();
    }

    public void delete(Long id) {
        restTemplate.delete(idResource, id);
    }

    public NoteDTO create(NoteDTO note) {
        return restTemplate.postForObject(resource, note, NoteDTO.class);
    }

	public NoteDTO findById(Long id) {
		return restTemplate.exchange(idResource, HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), NoteDTO.class, id).getBody();
	}

}
