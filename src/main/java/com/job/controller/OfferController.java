package com.job.controller;

import com.job.dto.SaveOfferDto;
import com.job.dto.SearchOfferDto;
import com.job.model.Offer;
import com.job.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("94mart/offer")
public class OfferController {
    @Autowired
    OfferService offerService;

    @PostMapping()
    public HttpStatus saveOffer(@RequestBody SaveOfferDto offerDto){
        return offerService.saveOffer(offerDto);
    }

    @GetMapping("/{id}")
    public Offer findOfferById(@PathVariable long id){
        return offerService.findById(id);
    }

    @PostMapping("/search")
    public List<Offer> searchOffer(@RequestBody SearchOfferDto filter){
        return offerService.searchOffer(filter);
    }

    @GetMapping("/{userId}")
    public List<Offer> getOffersByUserId(@PathVariable long userId){ return offerService.getOfferByUserId(userId);
    }

    @DeleteMapping("delete/{offerId}")
    public void deleteOffer(@PathVariable long offerId){
        offerService.deleteOffer(offerId);
    }

    @GetMapping()
    public List<Offer> getAllOffers(){
        return offerService.getAllOffers();
    }

    @PutMapping("/report-offer/{offerId}")
    public void reportOffer(@PathVariable long offerId){
        offerService.reportOffer(offerId);
    }

}
