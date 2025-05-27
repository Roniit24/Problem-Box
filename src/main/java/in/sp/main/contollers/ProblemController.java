package in.sp.main.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.sp.main.dto.ProblemDTO;
import in.sp.main.service.ProblemService;

@Controller
public class ProblemController {
	    @Autowired
	    private ProblemService problemService;

	    @GetMapping("/")
	    public String showForm(Model model) {
	        model.addAttribute("problem", new ProblemDTO());
	        return "problem_form";
	    }

	    @PostMapping("/submit")
	    public String submitProblem(@ModelAttribute("problem") ProblemDTO dto) {
	        problemService.submitProblem(dto);
	        return "thank_you";  // returns thank_you.html
	    }
	    @GetMapping("/reply/{id}")
	    public String showReplyForm(@PathVariable Long id, Model model) {
	        System.out.println("okay done");
	        model.addAttribute("problem", problemService.getProblemById(id));
	        return "reply_form"; // Must exist in templates/
	    }
	    @PostMapping("/send-reply/{id}")
	    public String sendReply(@PathVariable Long id, @RequestParam("replyMessage") String replyMessage) {
	        problemService.sendReply(id, replyMessage);
	        return "redirect:/admin/problems";
	    }

}
