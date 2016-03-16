package com.peach.dec.core.exam.entity;

public class ExamAnswer {
	private String id;
	private String answer;
	private Short singleOptionEachPoint;
	private Short multiOptionEachPoint;
	private Short judgeEachPoint;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Short getSingleOptionEachPoint() {
		return singleOptionEachPoint;
	}

	public void setSingleOptionEachPoint(Short singleOptionEachPoint) {
		this.singleOptionEachPoint = singleOptionEachPoint;
	}

	public Short getMultiOptionEachPoint() {
		return multiOptionEachPoint;
	}

	public void setMultiOptionEachPoint(Short multiOptionEachPoint) {
		this.multiOptionEachPoint = multiOptionEachPoint;
	}

	public Short getJudgeEachPoint() {
		return judgeEachPoint;
	}

	public void setJudgeEachPoint(Short judgeEachPoint) {
		this.judgeEachPoint = judgeEachPoint;
	}


	@Override
	public String toString() {
		return "ExamAnswer [id=" + id + ", answer=" + answer
				+ ", singleOptionEachPoint=" + singleOptionEachPoint
				+ ", multiOptionEachPoint=" + multiOptionEachPoint
				+ ", judgeEachPoint=" + judgeEachPoint + "]";
	}

}
