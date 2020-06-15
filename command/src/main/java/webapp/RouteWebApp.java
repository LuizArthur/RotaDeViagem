package webapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.RouteAppService;
import domain.entities.Route;
import webapp.dto.InsertRoutesAdapater;
import webapp.utils.JsonUtils;

@WebServlet("/route")
public class RouteWebApp<InsertRotasDto> extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final RouteAppService routeAppService = new RouteAppService();

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final webapp.dto.InsertRoutesDto insertRoutesDto = insertRotas(request, response);

        final PrintWriter out = response.getWriter();        
        out.println(JsonUtils.classToJson(insertRoutesDto));
    }
    
    private webapp.dto.InsertRoutesDto insertRotas(final HttpServletRequest request, final HttpServletResponse response) {
    	final InsertRoutesAdapater adapter = new InsertRoutesAdapater();
        try {
            final String departureAirportCode = request.getParameter("departureAirportCode");
            final String arrivalAirportCode = request.getParameter("arrivalAirportCode");
            final String cost = request.getParameter("cost");
            final String inputsPath = request.getParameter("inputsPath");

            final Route route = routeAppService.insert(departureAirportCode, arrivalAirportCode, cost, inputsPath);            

            return adapter.RouteToDto(route);
        } catch (Exception e) {
            return adapter.RouteToDto(e);
        }
    }

}