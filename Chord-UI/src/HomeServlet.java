// jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

@WebServlet("/photoShare/home")
public class HomeServlet extends HttpServlet {
    PrintWriter out;
    public static String tag;
    public static String node;

    public static boolean _backup;

    public static int _retryCount;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean isFirstCall = true;

        response.setContentType("text/html");

        out = response.getWriter();

        if(isFirstCall) {
            out.println("<style>");

            out.println("body {\n" +
                    "    width: 100%;\n" +
                    "    height: 100%;\n" +
                    "    margin: 0px;\n" +
                    "    padding: 0px;\n" +
                    "}\n" +
                    "\n" +
                    ".picture-grid {\n" +
                    "    display: grid;\n" +
                    "    grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));\n" +
                    "    justify-items: center;\n" +
                    "    grid-gap 5px;\n" +
                    "    grid-row-gap: 0px;\n" +
                    "}\n" +
                    "\n" +
                    ".grid-box img {\n" +
                    "    width: 100%;\n" +
                    "}");

            out.println("</style>");

            out.println("<div class=\"picture-grid\">");
            isFirstCall = false;
        }


        node = request.getParameter("node");
        tag = request.getParameter("tag");
        _retryCount=0;
        System.out.println("*** node"+node);
        if (node.equals("null") || node.equals("null")) {
            return;
        }
        _retryCount=0;
        requestChord(node, tag, out);

    }
    public static void requestChord(String IP, String tag, PrintWriter out) throws IOException {
        Socket client = new Socket();
        long time = System.currentTimeMillis();

        try {
            System.out.println("making connection");
            client.connect(new InetSocketAddress(IP, 5678), 2000);
        } catch (Exception e) {
            ErrorHandler.ShowSocketError(out, e.getMessage() + " IP: " + IP);
            return;
        }
        System.out.println("*******");
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        //System.out.println(tag +":" + String.valueOf(backup));
        os.write(tag +"\r\n");
        if (_backup) os.write("true");
        os.flush();

        String res = in.readLine();


        if (res.equals("IP")) {
            res = in.readLine();
            System.out.print(res);
            os.close();
            in.close();
            client.close();
            requestChord(res, tag, out);
        } else if (res.equals("IMAGES")) {
            while ((res = in.readLine()) != null) {
                System.out.println("Read file " + res);

                out.println("<div class=\"grid-box\"> <img src=\"data:image/png;base64, " + res + "\" alt=\"Red dot\" > </img> </div>");
            }
            out.println(" </div>");
            out.println("<br /><br /> <h3><b>Total time: " + (float) (System.currentTimeMillis() - time) / 1000 + "s </b></h3>");
        }else if(res.equals("NO_TAG")){
            out.println("<div>Sorry.. the images for this tag is not present in the network currently!</div>");
        }else if(res.equals("ERROR")){
            out.println("<div>"+ in.readLine()+"</div>");
        }
    }


}
