package com.youthchina.domain.jinhao.communityQA;

public class QuestionAndPopAnswer {
    private Question question;
    private QuestionAnswer popQuestionAnswer;
    private StuInfo stuInfo;
    private Integer agreement;

    public Integer getAgreement() {
        return agreement;
    }

    public void setAgreement(Integer agreement) {
        this.agreement = agreement;
    }

    public QuestionAndPopAnswer(Question question, QuestionAnswer popQuestionAnswer, StuInfo stuInfo, Integer agreement){
        this.question = question;
        this.popQuestionAnswer = popQuestionAnswer;
        this.stuInfo = stuInfo;
        this.agreement = agreement;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public QuestionAnswer getPopQuestionAnswer() {
        return popQuestionAnswer;
    }

    public void setPopQuestionAnswer(QuestionAnswer popQuestionAnswer) {
        this.popQuestionAnswer = popQuestionAnswer;
    }

    public StuInfo getStuInfo() {
        return stuInfo;
    }

    public void setStuInfo(StuInfo stuInfo) {
        this.stuInfo = stuInfo;
    }
}
