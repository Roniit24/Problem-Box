package in.sp.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import in.sp.main.dto.ProblemDTO;
import in.sp.main.entities.Problem;
import in.sp.main.repositories.ProblemRepository;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void submitProblem(ProblemDTO dto) {
        Problem problem = new Problem();
        problem.setSenderName("MAHI ❤️");
        problem.setEmail(dto.getEmail());
        problem.setMessage(dto.getMessage());
        problem.setReplied(false);
        problemRepository.save(problem);
    }

    @Override
    public List<Problem> getAllProblems() {
        return problemRepository.findAll(); // Fix: Use instance not class
    }

    @Override
    public Problem getProblemById(Long id) {
        return problemRepository.findById(id).orElse(null);
    }

    @Override
    public void sendReply(Long id, String replyMessage) {
        Problem problem = getProblemById(id);
        if (problem != null) {
            problem.setReplied(true);
            problem.setReplyMessage(replyMessage);
            problemRepository.save(problem);

            // Send email
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(problem.getEmail());
            mail.setSubject("Reply to Your Problem");
            mail.setText("Dear " + problem.getSenderName() + ",\n\n" +
                         replyMessage + "\n\n" +
                         "Regards,\nYour Problem Review Team");
            mailSender.send(mail);
        }
    }
}
