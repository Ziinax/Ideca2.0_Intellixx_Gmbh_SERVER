package ideca.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Quiz quiz;
	private String contenu;
	private String rep1;
	private String rep2;
	private String rep3;
	private String repVrai;

	public Question() {
		super();
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getRep1() {
		return rep1;
	}

	public void setRep1(String rep1) {
		this.rep1 = rep1;
	}

	public String getRep2() {
		return rep2;
	}

	public void setRep2(String rep2) {
		this.rep2 = rep2;
	}

	public String getRep3() {
		return rep3;
	}

	public void setRep3(String rep3) {
		this.rep3 = rep3;
	}

	public String getRepVrai() {
		return repVrai;
	}

	public void setRepVrai(String repVrai) {
		this.repVrai = repVrai;
	}

	@Override
	public String toString() {
		return "Question [quiz=" + quiz + ", contenu=" + contenu + ", rep1=" + rep1 + ", rep2=" + rep2 + ", rep3="
				+ rep3 + ", repVrai=" + repVrai + "]";
	}

}
