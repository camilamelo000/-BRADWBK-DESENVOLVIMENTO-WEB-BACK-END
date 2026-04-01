package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Post;
import com.example.repository.PostRepository;

@RestController
@RequestMapping("/posts") 
public class PostController {

    @Autowired
    private PostRepository repository;

 
    @GetMapping
    public List<Post> listar() {
        return repository.listarTodos();
    }

 
    @PostMapping
    public ResponseEntity<Post> criar(@RequestBody Post post) {
        repository.salvar(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post); 
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Post> buscar(@PathVariable Long id) {
        Post post = repository.buscarPorId(id);
        if (post != null) {
            return ResponseEntity.ok(post); 
        }
        return ResponseEntity.notFound().build(); 
    }

 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        boolean removido = repository.deletar(id);
        if (removido) {
            return ResponseEntity.ok().build(); 
        }
        return ResponseEntity.notFound().build(); 
    }
}