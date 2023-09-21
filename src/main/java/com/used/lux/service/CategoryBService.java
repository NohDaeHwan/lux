package com.used.lux.service;

import com.used.lux.domain.CategoryB;
import com.used.lux.dto.CategoryBDto;
import com.used.lux.mapper.CategoryBMapper;
import com.used.lux.repository.CategoryBRepository;
import com.used.lux.request.CategoryCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryBService {

    private final CategoryBRepository cateBRepo;
    private final CategoryBMapper cateBMapper;

    @Transactional
    public void createCategory(CategoryCreateRequest categoryCreateRequest) {
        cateBRepo.save(CategoryB.builder()
                .cateBNm(categoryCreateRequest.categoryName())
                .build()
        );
    }

    @Transactional(readOnly = true)
    public List<CategoryBDto> categoryList() {
        return cateBMapper.toDtoList(cateBRepo.findAll());
    }

    @Transactional(readOnly = true)
    public boolean bigCategoryExist( String st){
        return cateBRepo.existsByCateBNm(st);
    }

    @Transactional
    public void bigCategoryDelete(Long categoryId) {cateBRepo.deleteById(categoryId);}

    @Transactional(readOnly = true)
    public CategoryBDto findById(Long id){
        return cateBMapper.toDto(cateBRepo.findById(id).orElse(null));
    }
}
