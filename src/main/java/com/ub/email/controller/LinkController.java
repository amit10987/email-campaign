package com.ub.email.controller;

import com.ub.email.entity.EmailStats;
import com.ub.email.repository.EmailStatsRepository;
import com.ub.email.service.StatsService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
public class LinkController {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    EmailStatsRepository emailStatsRepository;

    @Autowired
    StatsService statsService;

    @ResponseBody
    @RequestMapping(value = "/offer", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getOffer() throws IOException {
        InputStream in = applicationContext.getResource("classpath:assets/offer.png").getInputStream();
        return IOUtils.toByteArray(in);
    }

    @RequestMapping(value = "/click/{uuid}", method = RequestMethod.GET)
    public void click(@PathVariable("uuid") String uuid) {
        EmailStats stats = emailStatsRepository.findByUuid(uuid);
        stats.incrementTotalClicked();
        emailStatsRepository.save(stats);
        statsService.pushStats(uuid);
    }
}
