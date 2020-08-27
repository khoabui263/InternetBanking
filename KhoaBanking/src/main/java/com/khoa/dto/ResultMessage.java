package com.khoa.dto;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.khoa.entity.GoiNho;

@Component
public class ResultMessage {
	public HashMap<String, String> ShowResult(String i, String mess) {
		HashMap<String, String> map = new HashMap<>();
		map.put("active", i);
		map.put("message", mess);

		return map;
	}

	public HashMap<String, Object> ShowList(String string, List list) {
		HashMap<String, Object> map = new HashMap<>();
		map.put(string, list);

		return map;
	}
	
	public HashMap<String, GoiNho> ShowReminder(String i, GoiNho goiNho) {
		HashMap<String, GoiNho> map = new HashMap<>();
		map.put(i, goiNho);

		return map;
	}
}
