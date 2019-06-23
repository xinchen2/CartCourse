package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

@WebServlet("/UploadHandleServlet")
public class UploadHandleServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //�õ��ϴ��ļ��ı���Ŀ¼�����ϴ����ļ������WEB-INFĿ¼�£����������ֱ�ӷ��ʣ���֤�ϴ��ļ��İ�ȫ
        String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        //�ϴ�ʱ���ɵ���ʱ�ļ�����Ŀ¼
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        File tempfile = new File(tempPath);
        if (!tempfile.exists() && !tempfile.isDirectory()) {
            System.out.println(savePath + "Ŀ¼�����ڣ���Ҫ������");
            //������ʱĿ¼
            tempfile.mkdir();
        }
        //��Ϣ��ʾ
        String message = "";
        try {
            //ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
            //1������һ��DiskFileItemFactory����
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //���ù����Ļ������Ĵ�С�����ϴ����ļ���С�����������Ĵ�Сʱ���ͻ�����һ����ʱ�ļ���ŵ�ָ������ʱĿ¼���С�
            factory.setSizeThreshold(1024 * 100);
            //�����ϴ�ʱ���ɵ���ʱ�ļ��ı���Ŀ¼
            factory.setRepository(tempfile);
            //2������һ���ļ��ϴ�������
            ServletFileUpload upload = new ServletFileUpload(factory);
            //�����ļ��ϴ�����
            upload.setProgressListener(new ProgressListener() {
                @Override
                public void update(long l, long l1, int i) {
                    System.out.println("�ļ���СΪ��" + l1 + ",��ǰ�Ѵ���" + l);
                }
            });
            //����ϴ��ļ�������������
            upload.setHeaderEncoding("UTF-8");
            //3���ж��ύ�����������Ƿ����ϴ���������
            if (!ServletFileUpload.isMultipartContent(req)) {
                //���մ�ͳ��ʽ��ȡ����
                return;
            }
            //�����ϴ������ļ��Ĵ�С�����ֵ��Ŀǰ������Ϊ1024*1024�ֽڣ�Ҳ����1MB
            upload.setFileSizeMax(1024 * 1024);
            //�����ϴ��ļ����������ֵ�����ֵ=ͬʱ�ϴ��Ķ���ļ��Ĵ�С�����ֵ�ĺͣ�Ŀǰ����Ϊ10MB
            upload.setSizeMax(1024 * 1024 * 10);
            //4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
            List<FileItem> list = upload.parseRequest(req);
            for (FileItem item : list) {
                //���fileitem�з�װ������ͨ�����������
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    //�����ͨ����������ݵ�������������
                    String value = item.getString("UTF-8");
                    System.out.println(name + " = " + value);
                } else {
                    //���fileitem�з�װ�����ϴ��ļ�
                    //�õ��ϴ����ļ�����
                    String filename = item.getName();
                    System.out.println(filename);
                    if (filename == null || filename.trim().equals("")) {
                        continue;
                    }
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                    //�õ��ϴ��ļ�����չ��,�����Ҫ�����ϴ����ļ����ͣ���ô����ͨ���ļ�����չ�����ж��ϴ����ļ������Ƿ�Ϸ�
                    String fileTye = filename.substring(filename.lastIndexOf(".") + 1);
                    System.out.println("�ϴ��ļ�����չ���ǣ� " + fileTye);
                    //��ȡitem�е��ϴ��ļ���������
                    InputStream in = item.getInputStream();
                    //�õ��ļ����������
                    String saveFileName = makeFileName(filename);
                    //�õ��ļ��ı���Ŀ¼
                    String realSavePath = makePath(saveFileName, savePath);
                    //����һ���ļ������
                    FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFileName);
                    //����һ��������
                    byte buffer[] = new byte[1024];
                    //�ж��������е������Ƿ��Ѿ�����ı�ʶ
                    int len = 0;
                    //ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������
                    while ((len = in.read(buffer)) > 0) {
                        //ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\" + filename)����
                        out.write(buffer, 0, len);
                    }
                    //�ر�������
                    in.close();
                    //�ر������
                    out.close();
                    //ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�
                    item.delete();
                    message = "�ļ��ϴ��ɹ�";
                }
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            message = "�����ļ��������ֵ������";
            e.printStackTrace();
            req.setAttribute("message", message);
            req.getRequestDispatcher("/message.jsp").forward(req, resp);
            return;
        } catch (FileUploadBase.SizeLimitExceededException e) {
            message = "�ϴ��ļ����ܵĴ�С�������Ƶ����ֵ������";
            e.printStackTrace();
            req.setAttribute("message", message);
            req.getRequestDispatcher("/message.jsp").forward(req, resp);
            return;
        } catch (Exception e) {
            message = "�ļ��ϴ�ʧ�ܣ�";
            e.printStackTrace();
        }
        req.setAttribute("message", message);
        req.getRequestDispatcher("/message.jsp").forward(req, resp);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * �����ϴ��ļ����ļ������ļ����ԣ�uuid+"_"+�ļ���ԭʼ����
     * @param filename
     * @return
     */
    private String makeFileName(String filename) {
        return UUID.randomUUID().toString() + "_" + filename;
    }

    /**
     * Ϊ��ֹһ��Ŀ¼�������̫���ļ���Ҫʹ��hash�㷨��ɢ�洢
     * @param filename
     * @param filePath
     * @return
     */
    private String makePath(String filename, String filePath) {
        int hashcode = filename.hashCode();
        int dir1 = hashcode & 0xf;
        int dir2 = (hashcode&0xf0) >> 4;
        String dir = filePath + "\\" + dir1 + "\\" + dir2;
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }
}