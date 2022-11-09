package com.used.lux.service;

import com.used.lux.domain.UserGrade;
import com.used.lux.dto.UserGradeDto;
import com.used.lux.repository.UserGradeRepository;
import com.used.lux.request.GradeCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserGradeService {
    //UserGrade DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final UserGradeRepository userGradeRepository;

    public UserGradeDto createGrade(GradeCreateRequest gradeCreateRequest) {
        return UserGradeDto.from(userGradeRepository.save(UserGrade.of(gradeCreateRequest.id(),
                gradeCreateRequest.gradeStep(), gradeCreateRequest.gradeName(),
                gradeCreateRequest.discount(), gradeCreateRequest.rankUp())));
    }

    public void deleteGrade(Long gradeId) {
        userGradeRepository.deleteById(gradeId);
    }

    public List<UserGradeDto> getGradeList() {
        return userGradeRepository.findAll().stream()
                .map(UserGradeDto::from).collect(Collectors.toCollection(ArrayList::new));
    }

    @Transactional
    public UserGradeDto getNextGrade(int gradeStep) {
        return UserGradeDto.from(userGradeRepository.findByGradeStep(gradeStep+1));
    }

    public Long countAll() {
        return userGradeRepository.count();
    }
}
