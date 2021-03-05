package com.softserve.task4.models;

public class RelationResponse {
    private static final String SPEAK_RESULT_TRUE = "They are speaking...";
    private static final String SPEAK_RESULT_FALSE = "They don't have common topic";
    private static final String TOLERATE_RESULT_TRUE = "They tolerate each other...";
    private static final String TOLERATE_RESULT_FALSE = "They can not tolerate each other...";
    private static final String SPEND_RESULT_TRUE = "They spend time together...";
    private static final String SPEND_RESULT_FALSE = "They can not spend time together...";

    private boolean speakResult;
    private boolean tolerateResult;
    private boolean spendResult;
    private Human child;

    public RelationResponse(boolean speakResult, boolean tolerateResult, boolean spendResult, Human child) {
        this.speakResult = speakResult;
        this.tolerateResult = tolerateResult;
        this.spendResult = spendResult;
        this.child = child;
    }

    public String getSpeakResultMessage() {
        return speakResult ? SPEAK_RESULT_TRUE : SPEAK_RESULT_FALSE;
    }

    public String getTolerateResultMessage() {
        return tolerateResult ? TOLERATE_RESULT_TRUE : TOLERATE_RESULT_FALSE;
    }

    public String getSpendResultMessage() {
        return spendResult ? SPEND_RESULT_TRUE : SPEND_RESULT_FALSE;
    }

    public boolean isSpeakResult() {
        return speakResult;
    }

    public void setSpeakResult(boolean speakResult) {
        this.speakResult = speakResult;
    }

    public boolean isTolerateResult() {
        return tolerateResult;
    }

    public void setTolerateResult(boolean tolerateResult) {
        this.tolerateResult = tolerateResult;
    }

    public boolean isSpendResult() {
        return spendResult;
    }

    public void setSpendResult(boolean spendResult) {
        this.spendResult = spendResult;
    }

    public Human getChild() {
        return child;
    }

    public void setChild(Human child) {
        this.child = child;
    }
}

