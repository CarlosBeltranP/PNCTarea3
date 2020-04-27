package com.uca.capas.tarea.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	//index
	@RequestMapping("/ingresar")
	public String ingresar() {
		return "/ingresar";
	}

	@RequestMapping("/formulario")
	public ModelAndView formulario(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String name = request.getParameter("name");
		String lname = request.getParameter("lname");
		
		String bdateString = request.getParameter("birthdate");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date birthdate = dateFormat.parse(bdateString);
		
		String birthplace = request.getParameter("birthplace");
		String school = request.getParameter("school");
		String telephone  = request.getParameter("telephone");
		String celphone = request.getParameter("celphone");
		
		String added = "Alumno ingresado con éxito";
		mav.addObject("ready", added);
		mav.setViewName("/result");

		
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.setTime(birthdate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int cont = 0;
		String errorList = " ";

		if (name.length() == 0 || name.length() > 25) { 
			cont ++ ;
			errorList = errorList +  "- El campo" + "<strong>" +" Nombres "+ "</strong>"+ "debe tener entre 1 y 25 caracteres.";
		}
		if (lname.length() == 0 || lname.length() > 25) {
			cont ++;
			errorList = errorList  + "<br />" + "- El campo" + "<strong>"+" Apellidos "+ "</strong>"+ "debe tener entre 1 y 25 caracteres.";
		}
		if  (year < 2003) {
			cont++;
			errorList = errorList  + "<br />" + "- El campo" + "<strong>"+ " Fecha de nacimiento "+ "</strong>"+"debe ser igual o mayor al 01/01/2003";
		}
		if (birthplace.length()==0 || birthplace.length() > 25) { 
			cont ++;
			errorList = errorList + "<br />" + "- El campo" + "<strong>" + " Lugar de nacimiento "+"</strong>"+" debe tener entre 1 y 25 caracteres.";
		}

		if (school.length()==0 || school.length() > 100) { 
			cont ++;
			errorList = errorList + "<br />" + "- El campo" + "<strong>" + " Instituto/Colegio "+"</strong>"+" debe tener entre 1 y 100 caracteres."; 
		}

		if (telephone.length() != 8) {
			cont ++;
			errorList = errorList + "<br />" + "- El campo" + "<strong>" + " Teléfono fijo "+"</strong>"+" debe tener 8 caracteres.";		
		}
		if (celphone.length() != 8) {
			cont ++;
			errorList = errorList + "<br />" + "- El campo" + "<strong>" + " Teléfono movil "+"</strong>"+" debe tener 8 caracteres.";
		}

		if (cont > 0) {
			mav.addObject("list", errorList);
			mav.setViewName("/errors");

		}
		
		return mav;
	}

}
