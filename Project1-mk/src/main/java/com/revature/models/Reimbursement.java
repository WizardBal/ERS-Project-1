package com.revature.models;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Represents a bank account that a User can own.
 * Every account can be of type CHECKING or SAVINGS.
 * Every account keeps a record of transactions on that account
 * Accounts must be approved before transactions can be performed on it.
 */
public class Reimbursement implements Serializable {
	/**Automatically generated universally unique identifier */
	private static final long serialVersionUID = -662794077232408056L;

	public static enum ReimStatus {
		APPROVED, DENIED, PENDING
	}
	public static enum ReimType {
		WORK, TRAVEL, FOOD, OTHER
	}
	private Integer reimId;
	private Double amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private String author;
	private String resolver;
	private ReimStatus reimStatus;
	private ReimType reimType;
	private String userEmail;

	public Integer getReimId() {
		return reimId;
	}


	public void setReimId(Integer reimId) {
		this.reimId = reimId;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public Timestamp getSubmitted() {
		return submitted;
	}


	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}


	public Timestamp getResolved() {
		return resolved;
	}


	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getResolver() {
		return resolver;
	}


	public void setResolver(String resolver) {
		this.resolver = resolver;
	}


	public ReimStatus getReimStatus() {
		return reimStatus;
	}


	public void setReimStatus(ReimStatus reimStatus) {
		this.reimStatus = reimStatus;
	}


	public ReimType getReimType() {
		return reimType;
	}


	public void setReimType(ReimType reimType) {
		this.reimType = reimType;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
}
