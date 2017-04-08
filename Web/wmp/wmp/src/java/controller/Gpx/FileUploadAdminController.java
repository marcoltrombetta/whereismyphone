package controller.Gpx;


import dbService.PhoneInfoService;
import dbService.PositionInfoService;
import interfaces.PhoneInfoInterface;
import interfaces.PositionInfoInterface;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import obj.Location;
import obj.PhoneInfo;

import obj.PositionInfo;
import obj.User;

//http://stackoverflow.com/questions/9417189/parsing-gpx-files-with-sax-parser-or-xmlpullparser
/**
 *
 * @author marco
 */
@WebServlet(name = "FileUploadAdminController", urlPatterns = {"/Gpx/FileUpload"})
@MultipartConfig(fileSizeThreshold=1024*1024, 
    maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)

public class FileUploadAdminController extends HttpServlet {
    private PhoneInfoInterface phoneInfoService;
      private PositionInfoInterface positionInfoService;
    
    public FileUploadAdminController(){
     phoneInfoService=new PhoneInfoService();
     positionInfoService=new PositionInfoService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userPath = request.getServletPath();
           
        if (userPath.equals("/Gpx/FileUpload")) {      
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("login");

        // Create path components to save the file
         final String path = getServletContext().getRealPath("/")+"uploads";//"/data/ownCloud/whereismyphone/";//request.getParameter("destination");
         final String imei=request.getParameter("idphone");
         final Part filePart = request.getPart("file");
         final String fileName = getFileName(filePart);

        
         OutputStream out = null;
         InputStream filecontent = null;
         final PrintWriter writer = response.getWriter();
         
         try {
             out = new FileOutputStream(new File(path + File.separator + fileName));
             filecontent = filePart.getInputStream();

             int read = 0;
             final byte[] bytes = new byte[1024];

             while ((read = filecontent.read(bytes)) != -1) {
                 out.write(bytes, 0, read);
             }
           
              //save gpx file to history
                PhoneInfo phoneInfo=phoneInfoService.getByIdUsuarioByImei(user.getId(), imei);
                List<Location> lst=new GpxParser().getPoints(new File(path + File.separator + fileName));
                
                for(Location l:lst){
                    positionInfoService.insert_Hist(
                        new PositionInfo(
                        0,
                        l.getLatitude(),
                        l.getLongitude(),
                        new Timestamp(l.getTime().getTime()),
                        0,
                        phoneInfo.getId(),
                        new PhoneInfo(
                          phoneInfo.getId(),phoneInfo.getIdUsuario(),phoneInfo.getImei(),phoneInfo.getModelo()
                        ),
                        null
                        )
                    );
                }
            
             //   writer.println("New file " + fileName + " created at " + path);
            
   
         } catch (FileNotFoundException fne) {
             writer.println("You either did not specify a file to upload or are "
                     + "trying to upload a file to a protected or nonexistent "
                     + "location.");
             writer.println("<br/> ERROR: " + fne.getMessage());
        
            } catch (Exception ex) {
               writer.println(ex.getMessage());
            } finally {
             if (out != null) {
                 out.close();
             }
             if (filecontent != null) {
                 filecontent.close();
             }
             if (writer != null) {
                 writer.close();
             }
         }
        
    }
     }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}