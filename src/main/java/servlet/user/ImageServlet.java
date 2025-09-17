package servlet.user;
import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.admin.AdminProductService;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fileName = request.getParameter("name");
        if (fileName == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        File file = new File(AdminProductService.FILE_PATH, fileName);
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // コンテンツタイプ設定
        if (fileName.endsWith(".png")) response.setContentType("image/png");
        else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) response.setContentType("image/jpeg");
        else if (fileName.endsWith(".gif")) response.setContentType("image/gif");

        try (java.io.FileInputStream fis = new java.io.FileInputStream(file);
             java.io.OutputStream os = response.getOutputStream()) {
            fis.transferTo(os);
        }
    }
}
