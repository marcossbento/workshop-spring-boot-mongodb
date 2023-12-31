package com.marcossbento.workshopmongo.services;

import com.marcossbento.workshopmongo.domain.Post;
import com.marcossbento.workshopmongo.domain.User;
import com.marcossbento.workshopmongo.dto.UserDTO;
import com.marcossbento.workshopmongo.repository.PostRepository;
import com.marcossbento.workshopmongo.repository.UserRepository;
import com.marcossbento.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public List<Post> findAll() {
        return repo.findAll();
    }
    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text) {
        return repo.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return repo.fullSearch(text, minDate, maxDate);
    }
}
