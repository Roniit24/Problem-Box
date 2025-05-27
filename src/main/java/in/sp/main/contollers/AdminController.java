package in.sp.main.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.sp.main.entities.Problem;
import in.sp.main.service.ProblemService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
    private ProblemService problemService;

    @GetMapping("/problems")
    public String viewAllProblems(Model model) {
        List<Problem> problems = problemService.getAllProblems();
        model.addAttribute("problems", problems);
        return "admin_dashboard";
    }
    @PostMapping("/send-reply/{id}")
    public String sendReply(@PathVariable Long id, @RequestParam("replyMessage") String replyMessage) {
        problemService.sendReply(id, replyMessage);
        return "redirect:/admin/problems";
    }
    
}
