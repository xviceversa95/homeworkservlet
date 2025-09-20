package repository;
import model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//реализация хранилища, здесь напрямую взаимодействуем с "базой"
public class PostRepository {

    public ConcurrentHashMap<Long, Post> repository = new ConcurrentHashMap<>();
    public long postsCounter = 0;

    //возвращает весь список постов
    public List<Post> all(){
        List<Post> postList = new ArrayList<>();
        for (Map.Entry<Long, Post> entry : repository.entrySet()) {
            postList.add(entry.getValue());
        }
        return postList;
    }

    //возвращает конкретный пост по id
    public Optional<Post> getById(long id){
        return Optional.ofNullable(repository.get(id));
    }

    //сохраняем в мапу конкретный пост и вернуть его
    public Post save(Post post){
        if (post.getId() == 0) {
            postsCounter += 1;
            post.setId(postsCounter);
            repository.put(postsCounter, post);
            return repository.get(postsCounter);
        } else {
            if (repository.containsKey(post.getId())) {
                repository.replace(post.getId(), post);
                return repository.get(post.getId());
            } else {
                postsCounter += 1;
                post.setId(postsCounter);
                repository.put(postsCounter, post);
                return repository.get(postsCounter);
            }
        }
    }

    public void removeById(long id){
        repository.remove(id);
    }
}
