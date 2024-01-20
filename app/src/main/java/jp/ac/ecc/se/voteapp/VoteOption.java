package jp.ac.ecc.se.voteapp;

public class VoteOption {
    private String optionText;
    private int[] votes;

    private boolean hasVoted = false;
    private boolean resultDisplayed = false;

    public VoteOption(String optionText, int optionsCount) {
        this.optionText = optionText;
        this.votes = new int[optionsCount];
    }

    public String getOptionText() {
        return optionText;
    }

    public int getVotes(int optionIndex) {
        return votes[optionIndex];
    }

    public void vote(int optionIndex) {
        if (!hasVoted) {
            votes[optionIndex]++;
            hasVoted = true;
        }
    }

    public boolean hasVoted() {
        return hasVoted;
    }

    public boolean isResultDisplayed() {
        return resultDisplayed;
    }

    public void setResultDisplayed() {
        resultDisplayed = true;
    }
}
