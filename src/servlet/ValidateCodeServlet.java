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
 * Servlet implementation class ValidateCodeServlet
 */
//未使用，可能存在问题
@WebServlet("/ValidateCodeServlet")
public class ValidateCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public ValidateCodeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//禁止页面缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		//创建一个位于缓冲中的图像，宽度为60，高度为20
		int width=60;int height=20;
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics(); //获取上下文的画笔
		Random random = new Random();
		g.setColor(getRandomColor(200,250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman",Font.PLAIN,18));
		g.setColor(getRandomColor(160,200));
		for(int i=0;i<130;i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.drawLine(x1, y, x+x1, y+y1);
		}
		String strCode = "";
		for(int i=0;i<4;i++) {
			String strNumber = String.valueOf(random.nextInt(10));
			strCode = strCode+strNumber;
			g.setColor(new Color(15+random.nextInt(120),15+random.nextInt(120),15+random.nextInt(120)));
			g.drawString(strNumber, 13*i+6, 16);
		}
		request.getSession().setAttribute("Code", strCode);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	public Color getRandomColor(int fc,int bc) {
		Random random = new Random();
		Color randomColor = null;
		if(fc>255) fc=255;
		if(bc>255) bc=255;
		int r = fc+random.nextInt(bc-fc);
		int g = fc+random.nextInt(bc-fc);
		int b = fc+random.nextInt(bc-fc);
		randomColor = new Color(r,g,b);
		return randomColor;
	}

}
