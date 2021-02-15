package com.job.controller;

import com.job.model.ReportOffer;
import com.job.service.ReportOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("94mart/report-offer")
public class ReportOfferController {
    @Autowired
    ReportOfferService reportOfferService;

    @GetMapping("/admin/{offerId}")
    public List<ReportOffer> getReportsByOfferId(@PathVariable long offerId){
        return reportOfferService.getReportsByOfferId(offerId);
    }
    @PostMapping
    public void saveReportOffer(@RequestBody com.demo.dto.ReportOfferDto reportOfferDto){
        reportOfferService.saveReport(reportOfferDto);
    }
    @PutMapping("/admin/markViewed/{reportOfferId}")
    public void markAsViewed(@PathVariable long reportOfferId){
        reportOfferService.markAsViewed(reportOfferId);
    }

    @GetMapping("/admin/not-viewed")
    public List<ReportOffer> getAllNotViewed(){
        return reportOfferService.getAllUnviewed();
    }

    @GetMapping("/admin/not-viewed-number")
    public int getNoOfNotViewed(){
        return reportOfferService.getNoOfUnviewed();
    }

}

