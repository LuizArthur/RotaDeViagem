package webapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.IRouteAppService;
import application.RouteAppService;
import domain.entities.Airport;
import domain.vobjects.BestRoute;
import webapp.dto.BestRouteAdapter;
import webapp.dto.BestRouteDto;
import webapp.utils.JsonUtils;

@WebServlet("/route")
public class RouteWebApp extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final IRouteAppService routeAppService = new RouteAppService(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final webapp.dto.BestRouteDto bestRouteDto = insertRotas(request, response);
		
		final PrintWriter out = response.getWriter();
		out.println(JsonUtils.classToJson(bestRouteDto));
	}
	
	private BestRouteDto insertRotas(final HttpServletRequest request, final HttpServletResponse response) {
		final BestRouteAdapter adapter = new BestRouteAdapter();

		try {
			final String departureAirportCode = request.getParameter("departureAirportCode");
            final String arrivalAirportCode = request.getParameter("arrivalAirportCode");
            
            BestRoute bestRoute = routeAppService.getBestRoute(departureAirportCode, arrivalAirportCode);
            
            return adapter.BestRouteToDto(bestRoute);
            
		} catch (Exception e) {
			return adapter.RouteToDto(e);
		}
	}
	
	

}
