package com.example.springbootproject;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class HomeController {
    private ChallengeService Cs;

    public HomeController(ChallengeService Cs) {
        this.Cs = Cs;
    }

    @GetMapping("/viewall")
    public List<Challenge> viewAll() {
        return Cs.viewAll();
    }

    @PostMapping("/viewbyid")
    public Challenge viewById(@RequestBody ByProp byID) {
        return Cs.viewById(byID.getId());
    }

    @PostMapping("/create")
    public Response create(@RequestBody Challenge challenge) {
        Cs.addChallenge(challenge);
        return new Response("Created new challenge of title '" + challenge.getTitle() + "'");
    }

    @PostMapping("/delete")
    public Response delete(@RequestBody ByProp byID) {
        Challenge c = Cs.viewById(byID.getId());

        if (!Cs.deleteChallenge(byID.getId())) {
            return new Response("A dependency is not yet completed");
        }

        Response res = new Response("Deleted challenge of title '" + c.getTitle() + "'");
        return res;
    }

    @PostMapping("/update")
    public Response update(@RequestBody ByProp byID, Challenge challenge) {
        String oldTitle = challenge.getTitle();
        Cs.updateChallenge(byID.getId(), challenge);
        return new Response("Updated challenge from title '" + oldTitle + "' to title '" + Cs.viewById(byID.getId()).getTitle() + "'");
    }

    @PostMapping("/setcomplete")
    public Response setComplete(@RequestBody ByProp byID) {

        Challenge c = Cs.viewById(byID.getId());
        c.setComplete(true);
        Cs.addChallenge(c);

        return new Response("Completed challenge of title '" + Cs.viewById(byID.getId()).getTitle() + "'");
    }

    @PostMapping("/sortbytag")
    public List<Challenge> sortByTag(@RequestBody ByProp byTag) {
        return Cs.sortByTag(byTag.getTag());
    }
}
