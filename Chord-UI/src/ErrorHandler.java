import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class ErrorHandler {

    public static void ShowSocketError(PrintWriter out, String errorMessage) {
        //out.println("<h4>Can't connect to IP</h4> <br />");
        //out.println("<h4>Checking in backup..</h4> <br />");
        System.out.println("I'm in error handling");
        HashMap<String, String> backup_ip= new HashMap<>();
        String path=System.getProperty("user.home")+"/ip_b.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("line :: "+line);
                backup_ip.put(line.split(",")[0], line.split(",")[1]);
            }
            System.out.println("backup Ip :: "+ backup_ip);
            if(backup_ip.containsKey(HomeServlet.node) && HomeServlet._retryCount<=3) {
                HomeServlet._backup = true;
                HomeServlet._retryCount += 1;
                HomeServlet.requestChord(backup_ip.get(HomeServlet.node), HomeServlet.tag, out);
            }
            else throw new Exception("There is problem in connecting the network");
        } catch (Exception e) {
            out.println("<span>"+e.getMessage()+"</span> ");
            e.printStackTrace();
        }
    }
}
