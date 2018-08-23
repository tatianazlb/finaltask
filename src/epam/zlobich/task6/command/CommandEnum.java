package epam.zlobich.task6.command;

public enum CommandEnum {
    LOGIN(new LoginCommand()),
    REGISTER(new RegisterCommand()),
    THEME(new ThemeCommand()),
    LECTURE(new LectureCommand()),
    LOGOUT(new LogoutCommand()),
    ADDREQUEST(new AddRequestCommand()),
    ADDTHEME(new AddThemeCommand()),
    ANSWERQUESTION(new AnswerQuestionCommand()),
    APPROVEREQUEST(new ApproveRequestCommand()),
    DELETEUSER(new DeleteUserCommand()),
    DELETELECTURE(new DeleteLectureCommand()),
    DELETEREQUEST(new DeleteRequestCommand()),
    UPDATEUSER(new UpdateProfileCommand()),
    MYPAGE(new PersonalPageCommand()),
    ADDCONFERENCE(new AddConferenceCommand()),
    REGISTERFORCONFERENCE(new RegisterForConferenceCommand()),
    DELETEQUESTION (new DeleteQuestionCommand()),
    ASKQUESTION(new AskQuestionCommand());

    private AbstractCommand command;

    CommandEnum(AbstractCommand command1)
    {
        this.command = command1;
    }

    public AbstractCommand getCommand() {
        return command;
    }
}
