package servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class CreateCode
 */
//δʹ�ã�û�����⣬����������֤�룬�ڰ׵�
@WebServlet("/CreateCode")
public class CreateCode extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCode() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //��ֹ������������ͼƬ
        response.setDateHeader("Expires", -1);//��ֹ���������ʱȡ��ǰ���������
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        
        //֪ͨ�ͻ�����ͼƬ��ʽ�򿪷��͹�ȥ������
        response.setHeader("Content-Type", "image/jpeg");
        
        //���ڴ��д���һ��ͼƬ
        BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_INT_BGR);
        
        //��ͼƬ��д����
        Graphics graphics = image.getGraphics();
        
        //���ñ���ɫ
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 80, 30);
        
        //����д�����ݵ���ɫ������
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(null, Font.BOLD, 20));
        
        //��ͼƬ��д����
        String num = makeNum();
        
        //��������ɵ���ֵ�����浽session��
        request.getSession().setAttribute("checkcode", num);
        graphics.drawString(num, 0, 20);
        
        //��д�����ݵ�ͼƬ����������
        ImageIO.write(image, "jpg", response.getOutputStream());
        
        
    }

    /**
     * ���ܣ��������7λ����
     * @return
     */
    private String makeNum() {
        Random random = new Random();
        //9999999��������7λ��
        String num = random.nextInt(9999999)+"";
        StringBuffer stringBuffer = new StringBuffer();
        //�������ָ��λ����0
        for(int i=0;i<7-num.length();i++) {
            stringBuffer.append("0");
        }
        num = stringBuffer.toString() + num;
        return num;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}