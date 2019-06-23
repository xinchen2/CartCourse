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
//未使用，没有问题，生成数字验证码，黑白的
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
        
        //禁止浏览器缓冲随机图片
        response.setDateHeader("Expires", -1);//防止启动浏览器时取先前缓冲的数据
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        
        //通知客户机以图片方式打开发送过去的数据
        response.setHeader("Content-Type", "image/jpeg");
        
        //在内存中创建一副图片
        BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_INT_BGR);
        
        //想图片上写数据
        Graphics graphics = image.getGraphics();
        
        //设置背景色
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 80, 30);
        
        //设置写入数据的颜色和字体
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(null, Font.BOLD, 20));
        
        //像图片上写数据
        String num = makeNum();
        
        //把随机生成的数值，保存到session中
        request.getSession().setAttribute("checkcode", num);
        graphics.drawString(num, 0, 20);
        
        //把写好数据的图片输出给浏览器
        ImageIO.write(image, "jpg", response.getOutputStream());
        
        
    }

    /**
     * 功能：随机生成7位数字
     * @return
     */
    private String makeNum() {
        Random random = new Random();
        //9999999可以生成7位数
        String num = random.nextInt(9999999)+"";
        StringBuffer stringBuffer = new StringBuffer();
        //如果不够指定位数则补0
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