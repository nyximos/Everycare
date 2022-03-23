package wd.team4.everycare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import wd.team4.everycare.entity.HandsOnEnv;
import wd.team4.everycare.repository.HandsOnEnvRepository;

import java.util.List;
import java.util.Optional;

@RestController
@EnableAutoConfiguration
@CrossOrigin("*")
public class HandsOnEnvController {

    @Autowired
    HandsOnEnvRepository handsOnEnvRepository;

    // 실습환경 목록 JSON으로 반환
    @RequestMapping(value = "/api/v1/meetup/envs", method = RequestMethod.GET)
    public List<HandsOnEnv> envs(Sort sort) throws Exception {
        List<HandsOnEnv> handsOnEnv = handsOnEnvRepository.findAll(sort);
        return handsOnEnv;
    }

    // 참석자가 실습 환경 신청을 한다.
    @PostMapping(value = "/api/v1/meetup/env")
    public HandsOnEnv updateEnv(@RequestBody HandsOnEnv handsOnEnv) throws Exception {
        Optional<HandsOnEnv> handsOnEnvById = handsOnEnvRepository.findById(handsOnEnv.getNum());

        if (handsOnEnvById.isPresent()) {
            if (handsOnEnvById.get().getEmail() == null || handsOnEnvById.get().getEmail().isEmpty()) {
                return handsOnEnvRepository.save(handsOnEnv);
            } else {
                throw new Exception(handsOnEnvById.get().getUsername() + "님이 이미 등록하였습니다.");
            }
        } else {
            throw new Exception("Please submit with other email.");
        }
    }

    @PostMapping(value = "/api/v1/admin/meetup/env/reset")
    public HandsOnEnv resetEnv(@RequestBody HandsOnEnv handsOnEnv) throws Exception {
        return handsOnEnvRepository.save(handsOnEnv);
    }
    // @PostMapping(value = "/api/v1/admin/meetup/env/remove")
    // public void removeEnv(@RequestBody HandsOnEnv handsOnEnv) throws Exception {
    // handsOnEnvRepository.deleteById(handsOnEnv.getNum());
    // }

    // 실습 환경 삭제
    @RequestMapping(value = "/api/v1/admin/meetup/env/{num}", method = RequestMethod.DELETE)
    public void removeEnv(@PathVariable("num") int num) throws Exception {
        handsOnEnvRepository.deleteById(num);
    }

    // 실습환경 ID로 조회
    @RequestMapping(value = "/api/v1/meetup/env/{num}", method = RequestMethod.GET)
    public Optional<HandsOnEnv> getEnvById(@PathVariable("num") int num) throws Exception {
        return handsOnEnvRepository.findById(num);
    }

    @RequestMapping(value = "/api/v1/meetup/env", method = RequestMethod.GET)
    public HandsOnEnv getEnv(@RequestParam("username") String username, @RequestParam("email") String email)
            throws Exception {
        return handsOnEnvRepository.findByUsernameAndEmail(username, email);
    }

    public static void main(String[] args) {
        SpringApplication.run(HandsOnEnvController.class, args);
    }
}