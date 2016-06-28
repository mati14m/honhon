package honhon.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.pac4j.springframework.security.authentication.ClientAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import honhon.persistence.Persistence;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@EnableWebMvc
@RequestMapping("/")
public class SimpleController {

	private Persistence persistence = new Persistence();

	public SimpleController(){}

	
	@RequestMapping("/")
	public String home(HttpServletRequest request) {
		return "index";
	}

	@RequestMapping(value = "/google",  method = RequestMethod.GET)
	@ResponseBody
	public  ModelAndView login(HttpServletRequest request, ModelAndView model) {
		ClientAuthenticationToken token = (ClientAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

		if(!persistence.findUser(token.getUserProfile().getId())){
			persistence.addUser((String)token.getUserProfile().getAttribute("displayName"), token.getUserProfile().getId());
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonInString = mapper.writeValueAsString(persistence.getNotMyBooks(token.getUserProfile().getId()));
			String jsonInString2 = mapper.writeValueAsString(persistence.getMyBooks(token.getUserProfile().getId()));
			model.addObject("bookList", jsonInString);
			model.addObject("bookList2", jsonInString2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.setViewName("google/login");
		return model;
	}

	@RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseBody
	public String addBook(@RequestBody Book book) {
		ClientAuthenticationToken token = (ClientAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		ArrayList<String> list = new ArrayList<String>();
		list.add(book.title);
		list.add(book.author);
		list.add(book.publisher);
		list.add(book.comment);
		persistence.addBook(token.getUserProfile().getId(), list);
		return "OK";
	}

	@RequestMapping(value = "/add/delete", method = RequestMethod.PUT)
	@ResponseBody
	public String deleteBook(@RequestBody String title) {
		ClientAuthenticationToken token = (ClientAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		persistence.deleteBook(token.getUserProfile().getId(), title);
		return "OK";
	}

}
