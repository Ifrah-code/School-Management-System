package project.com.cognifyz.ratefilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.com.cognifyz.rateexception.RateLimitException;

@Component
public class RateLimitFilter implements Filter {
	 
	private final Map<String ,Bucket> buckets = new ConcurrentHashMap<>();
	private Bucket createNewBucket() {
	Refill refill = Refill.greedy(5, Duration.ofMinutes(1));
	Bandwidth limit = Bandwidth.classic(5, refill);
	return Bucket.builder().addLimit(limit).build();
	
	}

@Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
	// TODO Auto-generated method stub
	HttpServletRequest httpRequest =(HttpServletRequest) request;
	String ip = httpRequest.getRemoteAddr();
	
	Bucket bucket = buckets.computeIfAbsent(ip, k -> createNewBucket());
	
	if(bucket.tryConsume(1)) {
		chain.doFilter(request, response);
	}else {
		throw new RateLimitException("Too many requests , please try again later.");
//		HttpServletResponse httpResponse =(HttpServletResponse) response;
//		httpResponse.setStatus(429);
//		httpResponse.getWriter().write("Too many requests , please try again later.");
	}
	
}

}
