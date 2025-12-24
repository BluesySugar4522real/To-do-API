package com.example.springbootproject;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Service
public class ChallengeService {
    private ChallengeRepo Cr;

    public ChallengeService(ChallengeRepo Cr) {
        this.Cr = Cr;
    }

    public List<Challenge> viewAll() {
        return Cr.findAll();
    }

    public Challenge viewById(Long id) {
        return Cr.findById(id).orElseThrow();
    }

    public void addChallenge(Challenge challenge) {
        challenge.setOrderId((long) Cr.findAll().size() + 1);
        Cr.save(challenge);
    }

    public void updateChallenge(Long id, Challenge challenge) {
        Cr.findById(id).orElseThrow().setTitle(challenge.getTitle());
        Cr.findById(id).orElseThrow().setTag(challenge.getBody());
        Cr.findById(id).orElseThrow().setBody(challenge.getBody());
        Cr.findById(id).orElseThrow().setDepends(challenge.getDepends());
    }


    public boolean deleteChallenge(Long id) {
        long oldId = Cr.findById(id).orElseThrow().getOrderId();

        ArrayList<Long> depends = Cr.findById(id).orElseThrow().getDepends();

        for (Challenge c : Cr.findAll()) {
            if (depends.contains(c.getId())) {
                if (!c.getComplete()) {
                    return false;
                }
            }
        }

        Cr.deleteById(id);

        for (Challenge c : Cr.findAll()) {
            if (c.getOrderId() > oldId) {
                c.setOrderId(c.getOrderId() - 1);
                Cr.save(c);
            }
        }

        return true;
    }
}
