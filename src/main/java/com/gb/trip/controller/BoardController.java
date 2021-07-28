package com.gb.trip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gb.trip.config.MyUserDetails;
import com.gb.trip.dto.Pagination;
import com.gb.trip.model.Board;
import com.gb.trip.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 컨트롤로에서 세션을 어떻게 찾는지?
	// @AuthenticationPrincipal PrincipalDetail principal
	@GetMapping("/boardList")
	public String index(Model model, Pagination pagination, String searchText, @AuthenticationPrincipal MyUserDetails myUserDetails) {

		model.addAttribute("boards", boardService.listBoard(pagination));
//		if(myUserDetails.getNickname() == null) {
//			System.out.println(myUserDetails.getNickname());
//			return "/user/nicknameRegister";}
//
//		else
		return "boardList"; // viewResolver 작동!!
	}



	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.detailBoard(id));

		return "board/detail";
	}

	@GetMapping("/board/{id}/updateBoard")
	public String updateBoard(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.detailBoard(id));
		return "board/updateBoard";
	}

	// USER 권한이 필요
	@GetMapping("/board/boardwrite")
	public String write() {
		return "board/boardwrite";
	}


	@GetMapping("/board/search")
    public String search(String keyword, Model model) {

        List<Board> searchList = boardService.search(keyword);
        model.addAttribute("boards", searchList);

        return "board/searchBoard";
    }

}