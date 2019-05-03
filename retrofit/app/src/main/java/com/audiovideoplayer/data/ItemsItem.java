package com.audiovideoplayer.data;

import com.google.gson.annotations.SerializedName;
public class ItemsItem{

	@SerializedName("owner")
	private Owner owner;

	@SerializedName("score")
	private int score;

	@SerializedName("is_accepted")
	private boolean isAccepted;

	@SerializedName("last_activity_date")
	private int lastActivityDate;

	@SerializedName("creation_date")
	private int creationDate;

	@SerializedName("answer_id")
	private int answerId;

	@SerializedName("question_id")
	private int questionId;

	@SerializedName("last_edit_date")
	private int lastEditDate;

	public void setOwner(Owner owner){
		this.owner = owner;
	}

	public Owner getOwner(){
		return owner;
	}

	public void setScore(int score){
		this.score = score;
	}

	public int getScore(){
		return score;
	}

	public void setIsAccepted(boolean isAccepted){
		this.isAccepted = isAccepted;
	}

	public boolean isIsAccepted(){
		return isAccepted;
	}

	public void setLastActivityDate(int lastActivityDate){
		this.lastActivityDate = lastActivityDate;
	}

	public int getLastActivityDate(){
		return lastActivityDate;
	}

	public void setCreationDate(int creationDate){
		this.creationDate = creationDate;
	}

	public int getCreationDate(){
		return creationDate;
	}

	public void setAnswerId(int answerId){
		this.answerId = answerId;
	}

	public int getAnswerId(){
		return answerId;
	}

	public void setQuestionId(int questionId){
		this.questionId = questionId;
	}

	public int getQuestionId(){
		return questionId;
	}

	public void setLastEditDate(int lastEditDate){
		this.lastEditDate = lastEditDate;
	}

	public int getLastEditDate(){
		return lastEditDate;
	}

	@Override
 	public String toString(){
		return 
			"ItemsItem{" + 
			"owner = '" + owner + '\'' + 
			",score = '" + score + '\'' + 
			",is_accepted = '" + isAccepted + '\'' + 
			",last_activity_date = '" + lastActivityDate + '\'' + 
			",creation_date = '" + creationDate + '\'' + 
			",answer_id = '" + answerId + '\'' + 
			",question_id = '" + questionId + '\'' + 
			",last_edit_date = '" + lastEditDate + '\'' + 
			"}";
		}
}