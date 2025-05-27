package in.sp.main.service;

import java.util.List;

import in.sp.main.dto.ProblemDTO;
import in.sp.main.entities.Problem;

public interface ProblemService {
	void submitProblem(ProblemDTO dto);

    List<Problem> getAllProblems();

    Problem getProblemById(Long id);

    void sendReply(Long id, String replyMessage);
}
