package ar.edu.ub.progiii.mvc.dto;

public class FilmDTO {

    private String FilmName;
    private int Code;
    private String Duration;
    private String Summary;

    /**
     * Constructor para pelicula
     * @param filmName
     * @param code
     * @param duration
     * @param summary
     */
    public FilmDTO(String filmName, int code, String duration, String summary) {
        FilmName = filmName;
        Code = code;
        Duration = duration;
        Summary = summary;
    }

    /**
     * Constructor por defecto
     */
    public FilmDTO() {}

    public String getFilmName() {
        return FilmName;
    }

    public void setFilmName(String filmName) {
        FilmName = filmName;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }
    
    public String getDurationWithString() {
        return Duration + " " +"min";
    }
}
