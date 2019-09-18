package ar.edu.ub.progiii.mvc.dto;

public class CinemaShowDTO {

	private String CodeShow;
	private String StartTime;
	private String FinishTime;
	private String Comments;
	
	public CinemaShowDTO(String codeShow, String startTime, String finishTime, String comments) {
		CodeShow = codeShow;
		StartTime = startTime;
		FinishTime = finishTime;
		Comments = comments;
	}

	public String getCodeShow() {
		return CodeShow;
	}

	public void setCodeShow(String codeShow) {
		CodeShow = codeShow;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getFinishTime() {
		return FinishTime;
	}

	public void setFinishTime(String finishTime) {
		FinishTime = finishTime;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}
}
