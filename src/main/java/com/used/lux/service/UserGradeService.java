package com.used.lux.service;

import com.used.lux.domain.UserGrade;
import com.used.lux.dto.UserGradeDto;
import com.used.lux.mapper.UserGradeMapper;
import com.used.lux.repository.UserGradeRepository;
import com.used.lux.request.GradeCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserGradeService {
    private final UserGradeRepository userGradeRepo;

    private final UserGradeMapper userGradeMapper;

    public UserGradeDto createGrade(GradeCreateRequest gradeCreateRequest) {
        return userGradeMapper.toDto(userGradeRepo.save(UserGrade.builder()
                .gradeStep(gradeCreateRequest.gradeStep())
                .gradeName(gradeCreateRequest.gradeName())
                .discount(gradeCreateRequest.discount())
                .rankUp(gradeCreateRequest.rankUp())
                .build()
        ));
    }

    public void deleteGrade(Long gradeId) {
        userGradeRepo.deleteById(gradeId);
    }

    public List<UserGradeDto> getGradeList() {
        return userGradeMapper.toDtoList(userGradeRepo.findAll());
    }

    public UserGradeDto getNextGrade(int gradeStep) {
        int lastGrade = userGradeRepo.findByLastStep().getGradeStep();
        if (lastGrade == gradeStep) {
            return null;
        }
        return userGradeMapper.toDto(userGradeRepo.findByGradeStep(gradeStep+1));
    }

    public Long countAll() {
        return userGradeRepo.count();
    }

    public UserGrade getGradeName(int bronze) {
        return userGradeRepo.findByGradeStep(bronze);
    }
}
