package epam.zlobich.task6.dao;

public class FactoryDao {

    private UserDao userDAO = new UserDao();
    private ConferenceDao conferenceDAO = new ConferenceDao();
    private ThemeDao themeDAO = new ThemeDao();
    private LectureDao lectureDAO = new LectureDao();
    private RequestDao requestDAO = new RequestDao();
    private QuestionDao questionDao = new QuestionDao();



    public QuestionDao getQuestionDao() {
        return questionDao;
    }

    public UserDao getUserDAO() {
        return userDAO;
    }

    public ConferenceDao getConferenceDAO() {
        return conferenceDAO;
    }

    public ThemeDao getThemeDAO() {
        return themeDAO;
    }

    public LectureDao getLectureDAO() {
        return lectureDAO;
    }

    public RequestDao getRequestDAO() {
        return requestDAO;
    }
}
