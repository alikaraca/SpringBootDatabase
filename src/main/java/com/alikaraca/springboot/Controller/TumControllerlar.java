package com.alikaraca.springboot.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alikaraca.springboot.Model.User;
import com.alikaraca.springboot.Service.UserService;

@Controller
public class TumControllerlar {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= {"/","/giris"},method=RequestMethod.GET)
	public ModelAndView giris() {
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("giris");
		return modelAndView;
	}
	@RequestMapping(value="/kayit",method=RequestMethod.GET)
	public ModelAndView kayit() {
		ModelAndView modelAndView =new ModelAndView();
		User user=new User();
		modelAndView.addObject("user",user);
		modelAndView.setViewName("kayit");
		return modelAndView;
	}
	@RequestMapping(value="/kayit", method=RequestMethod.POST)
	public ModelAndView yeniKullanici(@Valid User user , BindingResult bindingResult) {
		ModelAndView modelAndView=new ModelAndView();
		User userExist= userService.findUserByEmail(user.getEmail());
		if(userExist!=null) {
			bindingResult.rejectValue("email","error.user","Girdiğiniz mail adresi kayıtlıdır");
		}
		if(bindingResult.hasErrors()) {
			modelAndView.setViewName("kayit");
		}else {
			userService.saveUser(user);
			modelAndView.addObject("KayitMesaji", "Kullanıcı başarılı bir şekilde kayıt yaptı");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("kayit");
		}
		return modelAndView;
	}
	@RequestMapping(value="/yonetici/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Hoşgeldiniz " + user.getName() + " " + user.getLast_name() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("yonetici/home");
		return modelAndView;
	}
}
