package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.UserListForm;

@Controller
@RequestMapping("/user")
public class UserListController {

	@Autowired
	private UserService userService;

	private ModelMapper modelMapper;

	// ユーザー一覧画面を表示
	@GetMapping("/list")
	public String getUserList(@ModelAttribute UserListForm form, Model model) {
		MUser user;
		try {
			user = modelMapper.map(form, MUser.class);
		} catch (NullPointerException e) {
			user = null;
		}
		//ユーザ検索 
		List<MUser> userList = userService.getUsers(user);

		// Modelに登録
		model.addAttribute("userList", userList);

		// ユーザー一覧画面を表示
		return "user/list";
	}

	//ユーザ検索処理
	@PostMapping(value = "/list")
	public String postUserList(@ModelAttribute UserListForm form, Model model) {
		
		MUser user = new MUser();
		try {
			//formをMUserクラスに変換
			user = modelMapper.map(form, MUser.class);
		} catch (NullPointerException e) {
			//userIdに""が入るnullにならない
			user.setUserId(form.getUserId());
			user.setUserName(form.getUserName());
		}

		//ユーザ検索
		List<MUser> userList = userService.getUsers(user);

		//Modelに登録
		model.addAttribute("userList", userList);

		//ユーザ一覧画面を表示
		return "user/list";
	}
}
