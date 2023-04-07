package usc_mealmatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//@WebServlet(name = "Servlet", urlPatterns = {"/login"})

@WebServlet("/login")
public class LoginAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		System.out.println("hello");
		BufferedReader in = req.getReader();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		EmailPassword curr = gson.fromJson(in, EmailPassword.class);

		String email = curr.getEmail();
		String password = curr.getPassword();

		PrintWriter pw = resp.getWriter();

		if (UserAuthenticator.login(email, password)) {
			// setting status
			resp.setStatus(200);
			System.out.println("test case successful");
			resp.setContentType("application/json");
			pw.print("{\"authenticated\": \"true\"}");
			pw.flush();
		} else {
			resp.setStatus(400);
			System.out.println("Test case failed");
			pw.print("{\"authenticated\": \"false\"}");
			pw.flush();
		}

		pw.close();
		in.close();
	}
}
