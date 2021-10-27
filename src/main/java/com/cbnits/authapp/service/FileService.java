package com.cbnits.authapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cbnits.authapp.model.Doc;
import com.cbnits.authapp.repository.FileRepository;

/**
 * @author cbnits-154
 *
 */
@Service
public class FileService {

	@Autowired
	private FileRepository docRepository;

	public Doc saveFile(MultipartFile file) {
		String docname = file.getOriginalFilename();
		try {
			Doc doc = new Doc(docname, file.getContentType(), file.getBytes());
			return docRepository.save(doc);
		} catch (Exception e) {
			System.out.println("{saveFile} :" + e.getMessage());
		}
		return null;
	}

	public Optional<Doc> getFile(Integer fileId) {
		return docRepository.findById(fileId);
	}

	public List<Doc> getFiles() {
		return docRepository.findAll();
	}
}
