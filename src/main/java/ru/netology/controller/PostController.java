package ru.netology.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

@Controller
public class PostController {
    //тут работаем с запросами, сериализуем и десериализуем
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;

    public PostController(PostService service){
        this.service = service;
    }

    public void all(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var data = service.all();
        final var gson = new Gson();
        response.getWriter().print(gson.toJson(data));
    }

    //в методе save() мы получаем на вход json, потом мы этот json парсим в объект класса
    // ...Post, затем уже с объектом в кач-ве параметра вызываем service.save()
    // И когда сохранили, чтобы отдать пост клиенту, сериализуем обратно и отдаём в json сохранённое
    public void save(Reader body, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var gson = new Gson();
        final var post = gson.fromJson(body, Post.class);
        final var data = service.save(post);
        response.getWriter().print(gson.toJson(data));
    }


    public void getById(long id, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        Gson gson = new Gson();
        Post post = service.getById(id);
        response.getWriter().print(gson.toJson(post));
    }

    public void removeById(long id, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        service.removeById(id);
        response.getWriter().print("Deleted" + "id:" + id);
    }
}

