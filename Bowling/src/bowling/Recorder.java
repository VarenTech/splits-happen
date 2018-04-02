package bowling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Recorder
 */
@WebServlet(
		description = "Records the scores submited by the Bowlingjs to disk", 
		urlPatterns = { "/Recorder" }, 
		initParams = { 
				@WebInitParam(name = "scoreData", value = "null", description = "JSON record of the game")
		})
public class Recorder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private File recordFile;
	private static final String tempDir = System.getProperty("java.io.tmpdir");
	private static final Lock lock = new ReentrantLock();


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Recorder() {
		super();
		recordFile = new File(tempDir+"/bowlingScore.log");
	}

	@Override

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		response.setContentType("APPLICATION/OCTET-STREAM");

		lock.lock();
		try(PrintWriter out = response.getWriter();
				FileInputStream fl = new FileInputStream(recordFile);){
			response.setHeader("Content-Disposition", "attachment; filename=\"Scores.txt\"");
			int i;
			while ((i = fl.read()) != -1) {
				out.write(i);
			}
			recordFile.delete();
		}finally {
			lock.unlock();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//		System.out.println(request.getParameterMap());
		String jsonRecord = request.getParameter("data");

		lock.lock();
		try(FileWriter fw = new FileWriter(recordFile);
				BufferedWriter bw = new BufferedWriter(fw);){			
			bw.write(new Record(jsonRecord).toString());
		}finally {
			lock.unlock();
		}
	}

}
