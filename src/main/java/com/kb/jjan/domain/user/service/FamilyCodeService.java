package com.kb.jjan.domain.user.service;

import com.kb.jjan.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class FamilyCodeService {
    private final UserRepository userRepository;

    private final List<String> a = Arrays.asList("귀여운", "상큼한", "커다란", "촉촉한", "재밌는", "빨간", "차가운", "매끈한", "깨끗한", "행복한");
    private final List<String> b = Arrays.asList("강아지", "도깨비", "코끼리", "냉장고", "감자", "빨대", "키보드", "휴대폰", "놀이터", "미용실");

    @Autowired
    public FamilyCodeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateFamilyCode() {
        String famcode = getRandomCombination();
        if (!isCodeExists(famcode)) {
            return famcode;
        }
        return generateFamilyCode();
    }

    private String getRandomCombination() {
        Random random = new Random();
        String wordA = a.get(random.nextInt(a.size()));
        String wordB = b.get(random.nextInt(b.size()));
        return wordA + wordB;
    }

    private boolean isCodeExists(String famcode) {
        return userRepository.existsByFamCode(famcode);
    }
}
