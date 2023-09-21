package com.used.lux.service;

import com.used.lux.domain.CategoryM;
import com.used.lux.dto.CategoryMDto;
import com.used.lux.mapper.CategoryMMapper;
import com.used.lux.repository.CategoryBRepository;
import com.used.lux.repository.CategoryMRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryMService {

    private final CategoryMRepository cateMRepo;
    private final CategoryMMapper cateMMapper;

    private final CategoryBRepository cateBRepo;

    public List<CategoryMDto> getMiddleCategoryList() {
        return cateMMapper.toDtoList(cateMRepo.findAll());
    }

    public  CategoryMDto getMcategoryid(Long id){
        return cateMMapper.toDto(cateMRepo.findById(id).orElse(null));
    }

    public void middleCategoryCreate(String st, Long id)
    {
        CategoryM categoryM = new CategoryM();
        //같은 이름이 있는지 확인
        if(!cateMRepo.existsByCateMNm(st)) {
            //없을시 추가
            categoryM.setCateMNm(st);
            categoryM.setCateB(cateBRepo.findById(id).orElse(null));
            cateMRepo.save(categoryM);
        }
    }
    public void middleCategoryDelete(String st){
        cateMRepo.deleteByCateMNm(st);
    }
    public void middelCategoryDeleteByBCategoryId(Long categoryId) {
        cateMRepo.deleteAllByCateB_Id(categoryId);

    }

    public boolean middleCategoryExsist(String st){
        //해당 카테고리가 존재하는지 확인
        return cateMRepo.existsByCateMNm(st);

    }

    public List<String> middlecategoryExsistByBCategory(Long categoryId) {
        return cateMRepo.findAllByCateB_Id(categoryId);
    }

    public void middleCategoryDeleteById(Long categoryId) {
        cateMRepo.deleteById(categoryId);
    }

    public CategoryMDto findById(Long id) {
        return cateMMapper.toDto(cateMRepo.findById(id).orElse(null));
    }
}