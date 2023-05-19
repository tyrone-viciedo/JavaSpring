package com.example.aspect;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//このアノテーションをクラスにつけるとすべてのコントローラーで共有するメソッドを用意することができる
//ただし、アノテーションをつける必要あり
@ControllerAdvice
public class GlobalControllAdvice {
	
	//データベース関連の例外処理
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExeptionHandler(DataAccessException e,Model model) {
		
		//空文字をセット
		model.addAttribute("error", "");
		
		//メッセージをモデルに追加
		model.addAttribute("message","DataAccessExceptionが発生しました");
		
		//HTTPのエラーコード（５００）をmodelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}
	
	//その他の例外処理
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e,Model model) {
		
		//空文字をセット
		model.addAttribute("error", "");
		
		//メッセージをModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}

}
