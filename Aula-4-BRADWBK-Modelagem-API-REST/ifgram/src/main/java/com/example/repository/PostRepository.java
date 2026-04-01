package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.model.Post;

@Repository
public class PostRepository {
    private List<Post> posts = new ArrayList<>();

    public List<Post> listarTodos() {
        return posts;
    }

   
public void salvar(Post postagem) {
    if (postagem.getDataCriacao() == null) {
        postagem.setDataCriacao(java.time.LocalDateTime.now());
    }
    posts.add(postagem);
}

    public Post buscarPorId(Long id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean deletar(Long id) {
        return posts.removeIf(p -> p.getId().equals(id));
    }
}