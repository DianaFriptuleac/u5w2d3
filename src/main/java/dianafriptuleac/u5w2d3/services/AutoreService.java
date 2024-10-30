package dianafriptuleac.u5w2d3.services;


import dianafriptuleac.u5w2d3.entities.Autore;
import dianafriptuleac.u5w2d3.exceptions.BadRequestException;
import dianafriptuleac.u5w2d3.exceptions.NotFoundException;
import dianafriptuleac.u5w2d3.payloads.NewAutorePayload;
import dianafriptuleac.u5w2d3.repositories.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AutoreService {
    @Autowired
    private AutoreRepository autoreRepository;


    //Salvo un nuovo autore
    public Autore saveAutore(NewAutorePayload body) {
        this.autoreRepository.findByEmail(body.getEmail()).ifPresent(
                autore -> {
                    throw new BadRequestException("Email " + body.getEmail() + " gia in uso/1");
                }
        );
        Autore newAutore = new Autore(body.getNome(), body.getCognome(), body.getEmail(), body.getDataDiNascita());
        return this.autoreRepository.save(newAutore);
    }

    //Cerco tutti gli autori
    public Page<Autore> findAllAutori(int page, int size, String sortBy) {
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.autoreRepository.findAll(pageable);
    }

    //cerco autore per id
    public Autore findAutoreById(long autoreId) {
        return this.autoreRepository.findById(autoreId).orElseThrow(() -> new NotFoundException(autoreId));
    }

    //Trovo autore per id and update
    public Autore findAutoreByIdAndUpdate(long autoreId, NewAutorePayload body) {
        Autore foundAutore = this.findAutoreById(autoreId);
        if (!foundAutore.getEmail().equals(body.getEmail())) {
            this.autoreRepository.findByEmail(body.getEmail()).ifPresent(
                    autore -> {
                        throw new BadRequestException("Email " + body.getEmail() + " gia in uso!");
                    }
            );
        }
        foundAutore.setNome(body.getNome());
        foundAutore.setCognome(body.getCognome());
        foundAutore.setEmail(body.getEmail());
        foundAutore.setDataDiNascita(body.getDataDiNascita());
        foundAutore.setAvatar("https://ui-avatars.com/api/?name=" + body.getNome() + "+" + body.getCognome());
        return this.autoreRepository.save(foundAutore);
    }

    //Trovo autore per id e cancello
    public void findByIdAutoreAndDelete(long autoreId) {
        Autore foundAutore = this.findAutoreById(autoreId);
        this.autoreRepository.delete(foundAutore);
    }
}
