package com.mycompany.thirtys.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.thirtys.commons.MediaUtils;
import com.mycompany.thirtys.commons.UploadFileUtils;
import com.mycompany.thirtys.service.BoardService;
import com.mycompany.thirtys.service.FileService;

@RestController
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Inject
	FileService fileService;
	
	@Resource
	private String uploadPath;
	
	@RequestMapping(value="/upload", method = RequestMethod.GET)
	public void upload() {
		
	}
	
	@RequestMapping(value="/upload", method = RequestMethod.POST, produces = "text/plan;charset=UTF-8")
	public ResponseEntity<String> upload(MultipartFile file) throws Exception{
		
		logger.info("originalName: " + file.getOriginalFilename());
		
		return new ResponseEntity<>(
		UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);
	}
	// 파일 데이터 전송
	@RequestMapping(value="/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception{
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		logger.info("FILE NAME : " + fileName);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(uploadPath+fileName);
			if(mType != null) {
				headers.setContentType(mType);
			}else {
				
				fileName = fileName.substring(fileName.indexOf("_")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\""+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
			}
				entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		return entity;
	}
	
	// 업로드 시 삭제 처리
	@RequestMapping(value="/deleteFile", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName){
		
		logger.info("delete file : " + fileName);
		
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		MediaType mType = MediaUtils.getMediaType(formatName);
		
		if(mType != null) {
			
			String front = fileName.substring(0,12);
			String end = fileName.substring(14);
			new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
		}
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
		
	}
	// 목록
	@RequestMapping(value = "/list/{bno}", method = RequestMethod.GET)
	public ResponseEntity<List<String>> listFiles(@PathVariable("bno") int bno){
		ResponseEntity<List<String>> entity = null;
		try {
			List<String> listFiles = fileService.listFiles(bno);
			entity = new ResponseEntity<>(listFiles, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// 파일 전체 삭제
	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	public ResponseEntity<String> deleteAll(@RequestParam("files[]") String[] files, HttpServletRequest request){
		
		if(files == null || files.length == 0)
			return new ResponseEntity<>("DELETE",HttpStatus.OK);
		
		ResponseEntity<String> entity = null;
		
			for (String fileName : files) {
				String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
				
				MediaType mType = MediaUtils.getMediaType(formatName);
				
				if(mType != null) {
					String front = fileName.substring(0,12);
					String end = fileName.substring(14);
					new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
				}
				new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
			}	
			return new ResponseEntity<String>("DELETE", HttpStatus.OK);
	}
	
}
