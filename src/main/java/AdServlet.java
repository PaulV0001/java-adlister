import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (name="AdServlet", urlPatterns = "/ads")
public class AdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Ad> ads = DaoFactory.getAdsDao().all();
            request.setAttribute("ads", ads);
            request.getRequestDispatcher("/ads/index.jsp").forward(request, response);
        }
    }

