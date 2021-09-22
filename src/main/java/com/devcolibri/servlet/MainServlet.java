package com.devcolibri.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;

@WebServlet(urlPatterns = "/files")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String[]> params = req.getParameterMap();
        String path = params.get("path")[0] + "/";
        char[] pathChars = path.toCharArray();
        int index = 0;

        for(int i = pathChars.length-2; i > 0; i--) {
            if(pathChars[i] == '/') {
                index = i;
                break;
            }
        }

        String pathAdv = path.substring(0, index);


        try {
            File[] files = new File(path).listFiles();
            ArrayList<Date> dates = new ArrayList<>();
            for(File file:files) {
                dates.add(new Date(file.lastModified()));
            }
            req.setAttribute("dates", dates);
            req.setAttribute("files", files);
        }
        catch (Exception e) {

        }


        req.setAttribute("name", "Devcolibri");
        req.setAttribute("date", new Date());
        req.setAttribute("path", path);
        req.setAttribute("pathAdv", pathAdv);
        req.getRequestDispatcher("mypage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        super.doPost(req, resp);
    }
}
