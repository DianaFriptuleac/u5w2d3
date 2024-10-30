package dianafriptuleac.u5w2d3.services;


import dianafriptuleac.u5w2d3.entities.Autore;
import dianafriptuleac.u5w2d3.entities.BlogPost;
import dianafriptuleac.u5w2d3.exceptions.NotFoundException;
import dianafriptuleac.u5w2d3.payloads.NewBlogPostPayload;
import dianafriptuleac.u5w2d3.repositories.AutoreRepository;
import dianafriptuleac.u5w2d3.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private AutoreRepository autoreRepository;

    //Trovo tutti i blogPost
    public Page<BlogPost> findAll(int page, int size, String sortBy) {
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.blogPostRepository.findAll(pageable);
    }

    // Salvo un nuovo BlogPost alla lista
    public BlogPost saveBlogPost(NewBlogPostPayload body) {
        if (body.getAutoreId() == null) {
            throw new IllegalArgumentException("L'id dell'autore è obbligatorio");
        }

        Autore autore = autoreRepository.findById(body.getAutoreId())
                .orElseThrow(() -> new NotFoundException(body.getAutoreId()));

        BlogPost newBlogPost = new BlogPost(
                body.getCategoria(),
                body.getTitolo(),
                body.getContenuto(),
                body.getTempoDiLettura(),
                autore
        );
        newBlogPost.setCover("https://placedog.net/400x200");
        return this.blogPostRepository.save(newBlogPost);
    }


    //Trovo un blogPost per ID
    public BlogPost findById(long blogPostId) {
        return this.blogPostRepository.findById(blogPostId)
                .orElseThrow(() -> new NotFoundException(blogPostId));
    }

    public BlogPost findByIdAndUpdate(long blogPostId, NewBlogPostPayload body) {
        return this.blogPostRepository.findById(blogPostId).map(blogPost -> {
            blogPost.setTitolo(body.getTitolo());
            blogPost.setContenuto(body.getContenuto());
            blogPost.setCategoria(body.getCategoria());
            blogPost.setTempoDiLettura(body.getTempoDiLettura());
            if (body.getAutoreId() != null) {
                Autore autore = autoreRepository.findById(body.getAutoreId())
                        .orElseThrow(() -> new NotFoundException(body.getAutoreId()));
                blogPost.setAutore(autore);
            }
            return this.blogPostRepository.save(blogPost);
        }).orElseThrow(() -> new NotFoundException(blogPostId));
    }

    //Trovo blogPost per id e cancello
    public void findByIdAndDelete(long blogPostId) {
        BlogPost blogPostDelete = this.blogPostRepository.findById(blogPostId)
                .orElseThrow(() -> new NotFoundException(blogPostId));
        this.blogPostRepository.delete(blogPostDelete);
    }
}
