package dianafriptuleac.u5w2d3.controllers;


import dianafriptuleac.u5w2d3.entities.Autore;
import dianafriptuleac.u5w2d3.payloads.NewAutorePayload;
import dianafriptuleac.u5w2d3.services.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/autori")
public class AutoreController {
    @Autowired
    private AutoreService autoreService;

    //1.Get http://localhost:3002/autori
    @GetMapping
    public Page<Autore> getAutore(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String sortBy) {
        return this.autoreService.findAllAutori(page, size, sortBy);
    }

    //2. Post http://localhost:3002/autori = payload
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autore createAutore(@RequestBody NewAutorePayload body) {

        return this.autoreService.saveAutore(body);
    }

    //3. Get http://localhost:3002/autori/{autoreId}
    @GetMapping("/{autoreId}")
    public Autore findAutoreById(@PathVariable long autoreId) {
        return this.autoreService.findAutoreById(autoreId);
    }

    //4. PUT http://localhost:3002/autori/{autoreId} + (payload)
    @PutMapping("/{autoreId}")
    public Autore findAutoreAndUpdate(@PathVariable long autoreId, @RequestBody NewAutorePayload body) {
        return this.autoreService.findAutoreByIdAndUpdate(autoreId, body);
    }


    //5.DELETE http://localhost:3002/autori/{autoreId}
    @DeleteMapping("/{autoreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAutoreAndDelete(@PathVariable long autoreId) {
        this.autoreService.findByIdAutoreAndDelete(autoreId);
    }
}
