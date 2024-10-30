package dianafriptuleac.u5w2d3.controllers;


import dianafriptuleac.u5w2d3.entities.BlogPost;
import dianafriptuleac.u5w2d3.payloads.NewBlogPostPayload;
import dianafriptuleac.u5w2d3.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogpost")
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;

    // 1. GET http://localhost:3002/blogpost
    @GetMapping
    public List<BlogPost> getAllBlogPost() {
        return this.blogPostService.findAll();
    }

    //3. POST http://localhost:3002/blogpost (+ payload)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost createBlogPost(@RequestBody NewBlogPostPayload body) {
        return this.blogPostService.saveBlogPost(body);
    }

    //2. GET http://localhost:3002/blogpost/{blogPostId}
    @GetMapping("/{blogPostId}")
    public BlogPost findBlogPostById(@PathVariable long blogPostId) {
        return this.blogPostService.findById(blogPostId);
    }

    //4. PUT http://localhost:3002/blogpost/{blogPostId} + (payload)
    @PutMapping("/{blogPostId}")
    public BlogPost findBlogPostAndUpdate(@PathVariable long blogPostId, @RequestBody NewBlogPostPayload body) {
        return this.blogPostService.findByIdAndUpdate(blogPostId, body);
    }

    //5.DELETE http://localhost:3002/blogpost/{blogPostId}
    @DeleteMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findBlogPostAndDelete(@PathVariable long blogPostId) {
        this.blogPostService.findByIdAndDelete(blogPostId);
    }

}
