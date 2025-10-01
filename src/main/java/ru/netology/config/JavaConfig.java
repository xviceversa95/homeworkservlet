package ru.netology.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.repository.PostRepositoryStub;
import ru.netology.service.PostService;

@Configuration
public class JavaConfig {

    @Bean
    public PostRepositoryStub postRepository() {
        return new PostRepositoryStub();
    }

    @Bean
    public PostController postController(PostService service) {
        return new PostController(service);
    }

    @Bean
    public PostService postService(PostRepositoryStub repository){
        return new PostService(repository);
    }

}
