package webapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.RotaAppService;
import domain.Rota;
import dto.InsertRotasDto;

@WebServlet("/rotas")
public class RotasWebApp extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final RotaAppService rotaAppService = new RotaAppService();
       
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
        out.println("Hello from ");
    }
    
    private InsertRotasDto insertRotas(final HttpServletRequest request, final HttpServletResponse response) {
        try {
            final String departureAirportCode = request.getParameter("departureAirportCode");
            final String arrivalAirportCode = request.getParameter("arrivalAirportCode");
            final String cost = request.getParameter("cost");
            final String inputsPath = request.getParameter("inputsPath");

            Rota rota = rotaAppService.insert(departureAirportCode, arrivalAirportCode, cost, inputsPath);

            return null;
        } catch (Exception e) {
            return null;
        }
    }

}