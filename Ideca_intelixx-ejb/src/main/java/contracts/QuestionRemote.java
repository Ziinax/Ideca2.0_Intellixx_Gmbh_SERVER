package contracts;

import java.util.List;

import javax.ejb.Remote;

import ideca.entity.Question;
import ideca.entity.Utilisateur;

@Remote
public interface QuestionRemote {
	public int ajouterQuestion(Question question);
	public void affecterQuestionQuiz(int idQuestion,int idQuiz);
	public List<Question> getAllQuestions();
	public Question ListQuestionByUser(int idQu);
}
