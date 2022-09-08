package com.used.lux.component;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.used.lux.domain.AppraisalImage;
import com.used.lux.domain.ForAppraisal;
import com.used.lux.domain.UserAccount;
import com.used.lux.dto.ForAppraisalDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileHandler {

	public List<AppraisalImage> parseFileInfo(ForAppraisalDto dto, UserAccount userAccount, ForAppraisal forAppraisal) throws Exception {
		// 반환할 파일 리스트
		List<AppraisalImage> imageList =  new ArrayList<>();
		
		// 전달되어 온 파일이 존재할 경우
        if(!CollectionUtils.isEmpty(dto.images())) {
            // 파일명을 업로드 한 날짜로 변환하여 저장
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyyMMdd");
            String current_date = now.format(dateTimeFormatter);
            
            // 프로젝트 디렉터리 내의 저장을 위한 절대 경로 설정
            // 경로 구분자 File.separator 사용
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
            System.out.println(absolutePath);

            // 파일을 저장할 세부 경로 지정
            String path = absolutePath + "src" + File.separator + "main" + File.separator + "resources" + File.separator + 
            		"static" + File.separator + "assets" + File.separator + "img" + File.separator + 
            		"appraisal_img" + File.separator + current_date;
            File file = new File(path);
            System.out.println(path);
            System.out.println(file.exists());
            
            // 디렉터리가 존재하지 않을 경우
            if(!file.exists()) {
                boolean wasSuccessful = file.mkdirs();
  
                // 디렉터리 생성에 실패했을 경우
                if(!wasSuccessful) {
                	System.out.println("file: was not successful");
                }
            }
 
            // 다중 파일 처리
            for(MultipartFile multipartFile : dto.images()) {
  
                    // 파일의 확장자 추출
                    String originalFileExtension;
                    String contentType = multipartFile.getContentType();

                    // 확장자명이 존재하지 않을 경우 처리 x
                    if(ObjectUtils.isEmpty(contentType)) {
                        break;
                    }
                    else {  // 확장자가 jpeg, png인 파일들만 받아서 처리
                        if(contentType.contains("image/jpeg"))
                            originalFileExtension = ".jpg";
                        else if(contentType.contains("image/png"))
                            originalFileExtension = ".png";
                        else  // 다른 확장자일 경우 처리 x
                            break;
                    }
                
                    // 파일명 중복 피하고자 나노초까지 얻어와 지정
                    String new_file_name = System.nanoTime() + originalFileExtension;
                        
                    // AppraisalImage 엔티티 생성
                    AppraisalImage image = AppraisalImage.of(
                            forAppraisal,
                            userAccount,
                            multipartFile.getOriginalFilename(),
                            "/static/assets/img/appraisal_img/" + current_date + "/" + new_file_name,
                            multipartFile.getSize()
                    );
  
                    // 생성 후 리스트에 추가
                    imageList.add(image);
  
                    // 업로드 한 파일 데이터를 지정한 파일에 저장
                    file = new File(absolutePath + "src" + File.separator + "main" + File.separator + "resources" + File.separator + 
                    		"static" + File.separator + "assets" + File.separator + "img" + File.separator + 
                    		"appraisal_img" + File.separator + current_date + File.separator + new_file_name);
                    multipartFile.transferTo(file);
                
                    // 파일 권한 설정(쓰기, 읽기)
                    file.setWritable(true);
                    file.setReadable(true);
            }
        }
        return imageList;
	}

}
