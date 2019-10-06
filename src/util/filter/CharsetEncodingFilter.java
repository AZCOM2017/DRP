package util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 采用filter统一处理字符集
 * Filter拦截了request和response
 * @author 陈长
 *
 */
public class CharsetEncodingFilter implements Filter {
	
	private String encoding;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//设置字符集
		request.setCharacterEncoding(encoding);// 服务端，控制台
		response.setContentType("text/html;charset=utf-8");// 客户端，页面
		//继续执行(还有filter就调filter，没有了就继续往下调JSP)
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.encoding =  filterConfig.getInitParameter("encoding");
	}

}
