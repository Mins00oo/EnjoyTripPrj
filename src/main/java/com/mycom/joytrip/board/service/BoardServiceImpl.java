package com.mycom.joytrip.board.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mycom.joytrip.board.dao.BoardDao;
import com.mycom.joytrip.board.dto.BoardRequestDto;
import com.mycom.joytrip.board.dto.BoardResponseDto;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDao boardDao;
	private final String uploadDirectory = "C:\\SSAFY"; // 파일 업로드 디렉토리 설정

	@Override
	public List<BoardResponseDto> list() {
		return boardDao.list();
	}

	@Override
	public BoardResponseDto detail(int boardId) {
		return boardDao.detail(boardId);
	}

	@Override
	public int insert(BoardRequestDto dto, MultipartFile multipartFile) {
		return boardDao.insert(dto);
	}

	@Override
	public int update(BoardRequestDto dto) {
		return boardDao.update(dto);
	}
	
	@Override
	public int delete(int boardId) {
		return boardDao.delete(boardId);
	}
	
    private String saveFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDirectory);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String filename = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(filename);
        Files.copy(file.getInputStream(), filePath);

        return filePath.toString();
    }	
	
}
