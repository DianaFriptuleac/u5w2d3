package dianafriptuleac.u5w2d3.services;


import dianafriptuleac.u5w2d3.entities.BlogPost;
import dianafriptuleac.u5w2d3.exceptions.NotFoundException;
import dianafriptuleac.u5w2d3.payloads.NewBlogPostPayload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BlogPostService {
    private List<BlogPost> blogPostList = new ArrayList<>();

    //Trovo la lista
    public List<BlogPost> findAll() {
        return this.blogPostList;
    }

    // Salvo un nuovo BlogPost alla lista
    public BlogPost saveBlogPost(NewBlogPostPayload body) {
        Random random = new Random();
        BlogPost newBlogPost = new BlogPost(body.getCategoria(), body.getTitolo(),
                body.getContenuto(), body.getTempoDiLettura());
        newBlogPost.setId(random.nextInt(1, 5000));
        newBlogPost.setCover("https://placedog.net/400x200");
        this.blogPostList.add(newBlogPost);
        return newBlogPost;
    }

    //Trovo un blogPost per ID
    public BlogPost findById(long blogPostId) {
        return this.blogPostList.stream().filter(blogPost ->
                blogPost.getId() == blogPostId).findFirst().orElseThrow(() ->
                new NotFoundException(blogPostId));
    }

    //Trovo blogPost per id and update
    public BlogPost findByIdAndUpdate(long blogPostId, NewBlogPostPayload body) {
        return this.blogPostList.stream().filter(blogPost -> blogPost.getId() == blogPostId).findFirst().map(blogPost ->
        {
            blogPost.setTitolo(body.getTitolo());
            blogPost.setContenuto(body.getContenuto());
            blogPost.setCategoria(body.getCategoria());
            blogPost.setTempoDiLettura(body.getTempoDiLettura());
            return blogPost;
        }).orElseThrow(() -> new NotFoundException(blogPostId));
    }

    //Trovo blogPost per id e cancello
    public void findByIdAndDelete(long blogPostId) {
        BlogPost blogPostDelete = this.blogPostList.stream().filter(blogPost -> blogPost.getId() == blogPostId).
                findFirst().orElseThrow(() -> new NotFoundException(blogPostId));
        this.blogPostList.remove(blogPostDelete);
    }
}
