package com.peach.dec.core.exam.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存放答案实体类
 * 
 * @author peach
 * @date 2015年5月30日
 */
public class ExamPlanAnswer {
	public static Map<String, List<ExamAnswer>> answerMap = new HashMap<String, List<ExamAnswer>>();

	public static Map<String, Map<String, List<ExamAnswer>>> studentMap = new HashMap<String, Map<String, List<ExamAnswer>>>();

	public static Map<String, Map<String, Integer>> mess = new HashMap<String, Map<String, Integer>>();
	
	public static Map<String, List<ExamAnswer>> getAnswerMap() {
		return answerMap;
	}

	public static void setAnswerMap(Map<String, List<ExamAnswer>> answerMap) {
		ExamPlanAnswer.answerMap = answerMap;
	}

	
	
	public static Map<String, Map<String, List<ExamAnswer>>> getStudentMap() {
		return studentMap;
	}

	public static void setStudentMap(
			Map<String, Map<String, List<ExamAnswer>>> studentMap) {
		ExamPlanAnswer.studentMap = studentMap;
	}

	
	
	public static Map<String, Map<String, Integer>> getMess() {
		return mess;
	}

	public static void setMess(Map<String, Map<String, Integer>> mess) {
		ExamPlanAnswer.mess = mess;
	}

	
}
