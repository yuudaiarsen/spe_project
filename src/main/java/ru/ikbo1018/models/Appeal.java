package ru.ikbo1018.models;

import java.sql.Date;

public class Appeal {
    private int id;
    private int accountId;
    private String sendDate;
    private int status;
    private String checkDate;
    private int operatorId;
    private String appealText;
    private String answerText;
    private String address;
    private int type;

    public Appeal()
    {

    }

    public Appeal(int id, int accountId, String sendDate, int status, String checkDate, int operatorId, String appealText, String answerText, String address, int type) {
        this.id = id;
        this.accountId = accountId;
        this.sendDate = sendDate;
        this.status = status;
        this.checkDate = checkDate;
        this.operatorId = operatorId;
        this.appealText = appealText;
        this.answerText = answerText;
        this.address = address;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getAppealText() {
        return appealText;
    }

    public void setAppealText(String appealText) {
        this.appealText = appealText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
