package coding.test.find;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import coding.test.DTO.MemberDTO;
import jakarta.servlet.http.HttpSession;


@Controller
public class FindEmailController {
	@Autowired
	private coding.test.repository.QuaryRepository quary;

	@GetMapping("/findEmail")
	public String findEmail() {
		return "findEmail";
	}

	@PostMapping("/findEmail")
	public String findEmailPost(@RequestParam("mobile") String mobile, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {
		String ph = quary.selectMobile(mobile);

		if (ph == null) {
			System.err.println("err");
			return "findEmail";
		} else {
			Object dtoObject = session.getAttribute("dto");
			String email = quary.selectMobilebyEmail(ph);
			MemberDTO dto = new MemberDTO();
			dto.setEmail(email);
			
			
			

			model.addAttribute("dto", dto);
			redirectAttributes.addFlashAttribute("message", "err");
			redirectAttributes.addFlashAttribute("dto", dto);
			System.err.println("성공");
			return "resultFindEmail";
		}
	}

	@GetMapping("/resultFindEmail")
	public String resultFindEmail() {
		return "resultFindEmail";
	}
}
