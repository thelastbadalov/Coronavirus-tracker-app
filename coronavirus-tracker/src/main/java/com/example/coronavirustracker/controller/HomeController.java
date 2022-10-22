package com.example.coronavirustracker.controller;


import com.example.coronavirustracker.models.LocationStats;
import com.example.coronavirustracker.service.CoronaVirusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final CoronaVirusService coronaVirusService;

    @GetMapping("/")
public String home(Model model){
    List<LocationStats> allStats=coronaVirusService.getAllStats();
int totalReportedCases=allStats.stream().mapToInt(value -> value.getLatestTotalCases()).sum();
int totalNewCases=allStats.stream().mapToInt(value -> value.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
}

}
