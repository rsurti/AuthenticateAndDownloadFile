package com.cbnits.authapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cbnits.authapp.model.Doc;
import com.cbnits.authapp.model.UserData;
import com.cbnits.authapp.service.FileService;
import com.cbnits.authapp.service.UserService;


/**
 * @author cbnits-154
 *
 */
@Controller
public class AuthAppController {

	@Autowired
	private FileService fileService;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String get(Model model) {
		return "home";
	}


	@PostMapping("/registerUser")
	public String registerUserIfNotExists(@RequestParam("username") String username, Model model) {
		UserData user = userService.registerUser(username);
		model.addAttribute("user", user);
		return "uploaddoc";
	}

	@PostMapping("/matchKey/{username}")
	public String matchKey(@RequestParam String key, @PathVariable String username) {
		boolean isKeyMatched = userService.matchKey(key, username);
		if (isKeyMatched) {
			return "redirect:/doc";
		} else {
			return "redirect:/uploaddoc";
		}
	}

	@GetMapping("/doc")
	public String getDocPage(Model model) {
		List<Doc> docs = fileService.getFiles();
		model.addAttribute("docs", docs);
		return "doc";
	}

	@PostMapping("/uploadFiles")
	public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		for (MultipartFile file : files) {
			fileService.saveFile(file);

		}
		return "redirect:/doc";
	}

	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId) {
		Doc doc = fileService.getFile(fileId).get();
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + doc.getDocName() + "\"")
				.body(new ByteArrayResource(doc.getData()));
	}

}
