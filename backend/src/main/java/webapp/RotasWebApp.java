package webapp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.InsertRotasDto;

@WebServlet("/rotas")
public class RotasWebApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final InsertRotasDto insertRotasDto = insertRotas(request, response);

        final PrintWriter out = response.getWriter();
        out.println("Hello from Servlet");
    }

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final InsertRotasDto insertRotasDto = insertRotas(request, response);

        final PrintWriter out = response.getWriter();        
        out.println("Hello from Servlet");
    }
    
    private InsertRotasDto insertRotas(final HttpServletRequest request, final HttpServletResponse response) {
        final String departureAirport = request.getParameter("departureAirport");
        final String arrivalAirport = request.getParameter("arrivalAirport");
        final String cost = request.getParameter("cost");

        System.out.println(departureAirport);
        System.out.println(arrivalAirport);
        System.out.println(cost);

        return null;
    }

}