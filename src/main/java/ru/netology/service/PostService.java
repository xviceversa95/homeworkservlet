package ru.netology.service;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepositoryStub;

import java.util.List;
//тут у нас бизнес-логика, нет прямого контакта с БД, всё через методы репозитория

public class PostService {
    private final PostRepositoryStub repository;

    public PostService(PostRepositoryStub repository) {
        this.repository = repository;
    }

    //список всех постов
    public List<Post> all(){
        return repository.all();
    }

    //принимает id, возвращает найденный по id в репозитории пост
    public Post getById(long id){
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    //принимает пост, кладет в репозиторий, возвращает сохраненный пост
    public Post save(Post post){
        return repository.save(post);
    }

    //принимает id, удаляет из репозитория, не возвращает ничего
    public void removeById(long id) {
        repository.removeById(id);
    }

}
