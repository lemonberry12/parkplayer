//package com.movie.parkplayer.controller;
//
//
//import com.movie.parkplayer.dto.QnADto;
//import com.movie.parkplayer.entity.QnA;
//import com.movie.parkplayer.repository.QnARepository;
//import com.movie.parkplayer.service.QnAService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Date;
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//public class QnAController {
//
//    @Autowired
//    private QnAService qnAService;
//
//    //문의사항  작성페이지
//    @GetMapping("/qna/write")
//    public String
//    write(@RequestParam Long id,@RequestParam String title,@RequestParam String content,
//    @RequestParam String userId, Model model){
//        QnA qnA = QnA.builder()
//                .userId(userId)
//                .title(title)
//                .content(content)
//                .date(new Date())
//                .build();
//        model.addAttribute("qna",qnA);
//        return "/qna";
//    }
//
//
//    //문의사항 페이지
//    @GetMapping("/qna")
//    public List<QnARepository> qna() {
//
//        return QnARepository.findAll();
//    }
//
//
//
//
//    //문의사항 상세페이지
//    @GetMapping("/qna/detail/{id}")
//    public String qnaDetail(@PathVariable Long id, Model model) {
//        return "redirect:/qna/detail";
//    }
//
//    //문의사항 수정페이지
//    @GetMapping("/qna/modify")
//    public String qnaModify(@PathVariable Long id, Model model) {
//        return "redirect:/qna/modify";
//    }
//
//    //문의사항 삭제
//    @GetMapping("/qna/delete")
//    public String qnaDelete(@PathVariable Long id, Model model) {
//        return "redirect:/qna";
//    }
//
//
//
//}
