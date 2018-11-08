package contracts;

import java.util.List;

import javax.ejb.Remote;

import ideca.entity.Question;
import ideca.entity.Quiz;


@Remote
public interface QuizRemote {
	public int ajouterQuiz(Quiz quiz);
	
}
