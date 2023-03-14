package com.goguma.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goguma.common.vo.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class RoomController {
	private final ChatRoomRepository repository;

	//채팅방 목록 조회
    @GetMapping("/rooms")
    public String rooms(Model model){

        model.addAttribute("list", repository.findAllRooms());

        return "chat/rooms";
    }

    //채팅방 개설
    @PostMapping("/room")
    public String create(@RequestParam String name, RedirectAttributes rttr){

        rttr.addFlashAttribute("roomName", repository.createChatRoomDTO(name));
        return "redirect:/chat/rooms";
    }

  //채팅방 조회
    @GetMapping("/room")
    public void getRoom(String roomId, Model model){
    	
        model.addAttribute("room", repository.findRoomById(roomId));
    }
}
