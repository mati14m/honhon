package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import persistence.Persistence;

import java.util.ArrayList;

@Controller
@EnableWebMvc
@RequestMapping("/")
public class SimpleController {

	private Persistence persistence = new Persistence();

	
	@RequestMapping("/")
	public String home(Model model) {
		return "index";
	}

	@RequestMapping(value = "/abc",  method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/abc", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseBody
	public String addBook(@RequestBody Book book) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(book.title);
		list.add(book.author);
		list.add(book.publisher);
		list.add(book.comment);
		persistence.addBook(1, list);
		return "OK";
	}
	
}
