package com.pyramix.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController {

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/download_file")
	public void downloadFile(HttpServletResponse response) throws IOException {
		// .xlsx
		File file = new File("files\\monthly-budget.xlsx");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + file.getName();

		response.setContentType("application/octet-stream");
		response.setHeader(headerKey, headerValue);
		
		ServletOutputStream outputStream = response.getOutputStream();
		
		BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		
		byte[] buffer = new byte[8192]; // 8KB Buffer
		int bytesRead = -1;
		
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		
		inputStream.close();
		outputStream.close();
		
	}
	
}
