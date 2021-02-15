package com.job.service;

import com.job.dto.SaveOfferDto;
import com.job.dto.SearchOfferDto;
import com.job.model.Offer;
import com.job.model.OfferStatus;
import com.job.model.RedList;
import com.job.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {
    @Autowired
    OfferRepository offerRepository;
    @Autowired
    LocationService locationService;
    @Autowired
    UserService userService;
    @Autowired
    RedListService redListService;
    @Autowired
    DistrictService districtService;

    public HttpStatus saveOffer(SaveOfferDto offerDto) {
        Offer offer = new Offer();
        offer.setCompanyName(offerDto.getCompanyName());
        offer.setCategory(offerDto.getCategory());
        offer.setWorkingNature(offerDto.getWorkingNature());
        offer.setPosition(offerDto.getPositions());
        offer.setPhoto(offerDto.getPhoto());
        offer.setDistrict(districtService.findDistrictByDistrictName(offerDto.getDistrict()));
        offer.setUser(userService.findById(offerDto.getUser()));
        offer.setPostedDate(offerDto.getPostedDate());
        offer.setExpiredDate(offerDto.getExpiredDate());

        if (offerRepository.save(offer) != null)
            return HttpStatus.OK;
        return HttpStatus.BAD_REQUEST;

    }

    public Offer findById(Long offerId){
        return offerRepository.findById(offerId).orElseThrow(NullPointerException::new);
    }
//
//    public HttpStatus updateOffer(long offerId,SaveOfferDto updateDto){
//        try{
//            findById(offerId);
//        }catch (NullPointerException ex){
//            return HttpStatus.NOT_FOUND;
//        }
//        Offer updatedOffer = findById(offerId);
//        updatedOffer.setId(offerId);
//
//        if(updateDto.getCompanyName()!=null)updatedOffer.setCompanyName(updateDto.getCompanyName());
//        if(updateDto.getCategory()!=null)updatedOffer.setCategory(updateDto.getCategory());
//        if(updateDto.getPositions()!=null)updatedOffer.setPosition(updateDto.getPositions());
//        if(updateDto.getCategory()!=null)updatedOffer.setCategory(updateDto.getCategory());
//
//        offerRepository.save(updatedOffer);
//        return HttpStatus.OK;
//    }


    public List<Offer> searchOffer(SearchOfferDto filter){
        List<Offer> offers = offerRepository.findAll(new Specification<Offer>() {

            @Override
            public Predicate toPredicate(Root<Offer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();

                if (filter.getKeyword() != null) {
                    predicates.add(cb.like(root.get("position"), filter.getKeyword()+"%"));
                }
                if (filter.getCompanyName() != null) {
                    predicates.add(cb.like(root.get("companyName"), filter.getKeyword()+"%"));
                }
                if(filter.getCategory()!= null){
                    predicates.add(cb.equal(root.get("category"),filter.getCategory()));
                }
                if(filter.getWorkingNature()!= null){
                    predicates.add(cb.equal(root.get("workingNature"),filter.getWorkingNature()));
                }
                if(filter.getDistrict()!= null){
                    predicates.add(cb.equal(root.get("district"),districtService.findDistrictByDistrictName(filter.getDistrict())));
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        });
        switch (filter.getSortedBy()){
            case "Newest first": return offers.stream().sorted(Comparator.comparing(Offer::getPostedDate).reversed()).collect(Collectors.toList());
            case "Oldest first": return offers.stream().sorted(Comparator.comparing(Offer::getPostedDate)).collect(Collectors.toList());

        }
        return offers;
    }
    public void deleteOffer(long offerId){
        offerRepository.delete(findById(offerId));

    }
    public void acceptOffer(long offerId){
        Offer offer = findById(offerId);
        offer.setOfferStatus(OfferStatus.ACCEPTED);
        offerRepository.save(offer);
    }
    public void rejectOffer(long offerId){
        Offer offer = findById(offerId);
        offer.setOfferStatus(OfferStatus.REJECTED);
        offerRepository.save(offer);
    }

    public List<Offer> getAllOffers(){
        return offerRepository.findAll();
    }

    public void reportOffer(long offerId){
        Offer offer = findById(offerId);
        if(offer.getOfferStatus()!=OfferStatus.REPORTED) {
            offer.setOfferStatus(OfferStatus.REPORTED);
            List<Offer> reports = offer.getUser().getReportedOffers();
            reports.add(offer);
            offer.getUser().setReportedOffers(reports);
            if (reports.size()==3)redListService.save(new RedList(offer.getUser(), LocalDateTime.now()));
            offerRepository.save(offer);
        }
    }
    public List<Offer> getOfferByUserId(long userId){
        return offerRepository.findOffersByUserId(userId);
    }

}
