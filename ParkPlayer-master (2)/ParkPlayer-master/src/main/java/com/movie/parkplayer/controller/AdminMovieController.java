package com.movie.parkplayer.controller;


import com.movie.parkplayer.dto.AdminMovieDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AdminMovieController {

    private String uploadDir = "C:/uploads"; // 파일 업로드 디렉토리 경로 설정

    private static final Logger logger = LoggerFactory.getLogger(AdminMovieController.class);

    @GetMapping("/listGt")
    public String listGt(Model model) {
        return "mvbutton";
    }

    @GetMapping("/")
    public String index(Model model) {
        return "adminpage";
    }

    @GetMapping("/admin/modify")
    public String showModifyForm(@RequestParam("fileName") String fileName, Model model) {
        model.addAttribute("fileName", fileName);
        return "modify"; // modify.html과 매칭되는 뷰 이름입니다.
    }

    @PostMapping("/admin/modify")
    public String modifyFile(@RequestParam("fileName") String fileName,
                             @RequestParam("movieName") String movieName,
                             @RequestParam("director") String director,
                             @RequestParam("actor1") String actor1,
                             @RequestParam("actor2") String actor2,
                             Model model) {
        logger.info("수정된 영화 정보:");
        logger.info("영화 제목: " + movieName);
        logger.info("감독: " + director);
        logger.info("배우1: " + actor1);
        logger.info("배우2: " + actor2);

        model.addAttribute("movieName", movieName);
        model.addAttribute("director", director);
        model.addAttribute("actor1", actor1);
        model.addAttribute("actor2", actor2);

        List<String> resultList = new ArrayList<>();
        resultList.add("영화 제목 수정 완료: " + movieName);
        resultList.add("감독 수정 완료: " + director);
        resultList.add("배우1 수정 완료: " + actor1);
        resultList.add("배우2 수정 완료: " + actor2);
        model.addAttribute("resultList", resultList);

        return "redirect:/admin/files"; // 수정 후 파일 목록 페이지로 리다이렉트
    }

    @GetMapping("/admin")
    public String showUploadForm(Model model) {
        model.addAttribute("adminMovieDto", new AdminMovieDto());
        return "admin-upload-form";
    }

    @PostMapping("/admin")
    public String handleFileUpload(@ModelAttribute AdminMovieDto adminMovieDto,
                                   @RequestParam("file") MultipartFile file,
                                   Model model) {
        // 파일 처리 로직
        if (file.isEmpty()) {
            model.addAttribute("fileResult", "파일이 비어 있습니다.");
        } else {
            try {
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File destFile = new File(uploadDir + File.separator + file.getOriginalFilename());
                file.transferTo(destFile);

                // 파일 업로드 성공 메시지
                model.addAttribute("fileResult", "파일 업로드 성공: " + file.getOriginalFilename());

                // 파일 업로드 시간 추가
                LocalDateTime currentTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedTime = currentTime.format(formatter);
                model.addAttribute("uploadTime", "파일 업로드 시간: " + formattedTime);

            } catch (IOException e) {
                // 파일 업로드 실패 메시지
                model.addAttribute("fileResult", "파일 업로드 실패: " + e.getMessage());
                logger.error("파일 업로드 실패: " + e.getMessage());
            }
        }

        // 입력된 영화 정보 출력
        logger.info("입력된 영화 정보:");
        logger.info("영화 제목: " + adminMovieDto.getMovieName());
        logger.info("감독: " + adminMovieDto.getDirector());
        logger.info("배우1: " + adminMovieDto.getActor1());
        logger.info("배우2: " + adminMovieDto.getActor2());

        // 모델에 결과 추가
        model.addAttribute("movieName", adminMovieDto.getMovieName());
        model.addAttribute("director", adminMovieDto.getDirector());
        model.addAttribute("actor1", adminMovieDto.getActor1());
        model.addAttribute("actor2", adminMovieDto.getActor2());

        return "list";
    }

    @GetMapping("/admin/files")
    @ResponseBody
    public List<String> getFileList() {
        File directory = new File(uploadDir);
        return Arrays.asList(directory.list());
    }

    @PostMapping("/admin/delete")
    @ResponseBody
    public String deleteFile(@RequestParam("fileName") String fileName) {
        try {
            File fileToDelete = new File(uploadDir + File.separator + fileName);
            if (fileToDelete.delete()) {
                return "File deleted successfully: " + fileName;
            } else {
                logger.error("Failed to delete file: " + fileName);
                return "Failed to delete file: " + fileName;
            }
        } catch (Exception e) {
            logger.error("Error deleting file: " + fileName + ", " + e.getMessage());
            return "Error deleting file: " + fileName;
        }
    }
}


