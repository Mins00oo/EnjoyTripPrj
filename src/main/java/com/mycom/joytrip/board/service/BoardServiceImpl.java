package com.mycom.joytrip.board.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.joytrip.board.dao.BoardDao;
import com.mycom.joytrip.board.dto.BoardFileDto;
import com.mycom.joytrip.board.dto.BoardRequestDto;
import com.mycom.joytrip.board.dto.BoardResponseDto;
import com.mycom.joytrip.common.exception.CustomInsertException;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDao boardDao;
	
	@Value("${app.fileupload.uploadPath}")
	String uploadPath;
	
	@Value("${app.fileupload.uploadFolder}")
	String uploadFolder;

	@Override
	public List<BoardResponseDto> list() {
		return boardDao.list();
	}

	@Override
	public BoardResponseDto detail(int boardId) {
		return boardDao.detail(boardId);
	}

	@Override
	public int update(BoardRequestDto dto) {
		return boardDao.update(dto);
	}
	
	@Override
	public int delete(int boardId) {
		return boardDao.delete(boardId);
	}

	@Override
	@Transactional
	public int boardInsert(BoardRequestDto boardDto, MultipartHttpServletRequest request) {
		System.out.println(boardDto);
		try {
			boardDao.insert(boardDto); 
		} catch (Exception e) {
			throw new CustomInsertException("게시글 작성 데이터가 문제가 있습니다");
		}
		
		try {
			List<MultipartFile> fileList = request.getFiles("file");
			
			File uploadDir = new File(uploadPath + File.separator + uploadFolder);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			for (MultipartFile part : fileList) {
				int boardId = boardDto.getBoardId();
				String fileName = part.getOriginalFilename();
				String extension = FilenameUtils.getExtension(fileName);
				UUID uuid = UUID.randomUUID();
				String savingFileName = uuid + "." + extension;
				
				File destFile = new File(uploadPath + File.separator + uploadFolder);
				part.transferTo(destFile);
				
				// DB 파일 정보 저장
				BoardFileDto boardFileDto = new BoardFileDto();
				boardFileDto.setBoardId(boardId);
				boardFileDto.setFileName(fileName);
				boardFileDto.setFileSize(part.getSize());
				boardFileDto.setFileContentType(part.getContentType());
				boardFileDto.setFileUrl(uploadFolder + "/" + savingFileName);
				
				boardDao.boardFileInsert(boardFileDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomInsertException("게시글 파일 입력 과정에서 문제가 생겼습니다");
		}
		return 1;
	}

	
}
