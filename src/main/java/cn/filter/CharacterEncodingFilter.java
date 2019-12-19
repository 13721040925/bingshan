package cn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//�ַ������ù�����
public class CharacterEncodingFilter implements Filter {
	private String characterEncoding;// �ַ���
	private String enforce;// �Ƿ�ǿ��ִ��

	// 4��tomcatͣ��ʱִ��
	@Override
	public void destroy() {
		System.out.println("�ַ������������٣�");
		characterEncoding = null;
		enforce = null;
	}

	// 3��ִ�й���
	@Override
	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("�ַ�����������ʼ��");
		// �ж��Ƿ��в���������
		if (characterEncoding != null && enforce.equalsIgnoreCase("true")) {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) rep;
			String path = request.getRequestURI();
			System.out.println(path);// ִ��·��
			request.setCharacterEncoding(characterEncoding);
			response.setCharacterEncoding(characterEncoding);
		}
		/*
		 * HttpSession session = ((HttpServletRequest) req).getSession(); User user =
		 * (User) session.getAttribute("USER_LOGIN"); if (user == null) {
		 * ((HttpServletResponse) rep).sendRedirect(((HttpServletRequest)
		 * req).getContextPath() + "/user/logout1"); }
		 */
		// ���������ط���
		chain.doFilter(req, rep);
		System.out.println("�ַ������������У�");
	}

	// 2��
	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("�ַ�����������ʼ����");
		characterEncoding = config.getInitParameter("characterEncoding");// utf-8
		enforce = config.getInitParameter("enforce");// true
	}

	// 1����tomcatִ�У�û������ǰ
	public CharacterEncodingFilter() {
		System.out.println("�ַ���������ʵ����");
	}

}
