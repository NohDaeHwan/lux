package com.used.lux.repository;

import com.used.lux.domain.UserGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserGradeRepository extends JpaRepository<UserGrade, Long> {

    UserGrade findByGradeStep(int gradeStep);

    @Query(nativeQuery = true,value = "SELECT * FROM user_grade where grade_step = (select max(grade_step) from user_grade)")
    UserGrade findByLastStep();
}
