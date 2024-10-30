package dianafriptuleac.u5w2d3.services;


import dianafriptuleac.u5w2d3.entities.Autore;
import dianafriptuleac.u5w2d3.exceptions.NotFoundException;
import dianafriptuleac.u5w2d3.payloads.NewAutorePayload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AutoreService {
    private List<Autore> autoriList = new ArrayList<>();

    //Cerco la lista di autori
    public List<Autore> findAllAutori() {
        return this.autoriList;
    }

    //Salvo un nuovo autore
    public Autore saveAutore(NewAutorePayload body) {
        Random rdm = new Random();
        Autore newAutore = new Autore(body.getNome(), body.getCognome(), body.getEmail(), body.getDataDiNascita());
        newAutore.setId(rdm.nextInt(1, 500000));
        this.autoriList.add(newAutore);
        return newAutore;
    }

    //cerco autore per id
    public Autore findAutoreById(long autoreId) {
        return this.autoriList.stream().filter(autore -> autore.getId() == autoreId).findFirst().
                orElseThrow(() -> new NotFoundException(autoreId));
    }

    //Trovo autore per id and update
    public Autore findAutoreByIdAndUpdate(long autoreId, NewAutorePayload body) {
        return this.autoriList.stream().filter(autore -> autore.getId() == autoreId).findFirst().map(autore ->
        {
            autore.setNome(body.getNome());
            autore.setCognome(body.getCognome());
            autore.setEmail(body.getEmail());
            autore.setDataDiNascita(body.getDataDiNascita());
            return autore;
        }).orElseThrow(() -> new NotFoundException(autoreId));
    }

    //Trovo autore per id e cancello
    public void findByIdAutoreAndDelete(long autoreId) {
        Autore autoreDelete = this.autoriList.stream().filter(autore -> autore.getId() == autoreId).
                findFirst().orElseThrow(() -> new NotFoundException(autoreId));
        this.autoriList.remove(autoreDelete);
    }
}
