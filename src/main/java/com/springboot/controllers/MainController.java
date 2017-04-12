package com.springboot.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
	
	private static String DEST_FOLDER = "W:/";
	
	@GetMapping("/")
	public String index() {
		
		return "upload";
		
		
	}
	
	public String FileUpload( @RequestParam("file") MultipartFile file, 
								RedirectAttributes redirectAttributes) {
		
		if(file.isEmpty()) {
			
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(DEST_FOLDER + file.getOriginalFilename());
			Files.write(path,bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/uploadStatus";
	}
		
		@GetMapping("/uploadStatus")
		public String uploadStatus() {
			
			return "uploadStatus";
			
		}
		
				
	}
	
	
	

}
