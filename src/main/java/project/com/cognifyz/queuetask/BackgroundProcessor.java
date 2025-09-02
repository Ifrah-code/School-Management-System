package project.com.cognifyz.queuetask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import project.com.cognifyz.model.UserEntity;

@Service
public class BackgroundProcessor {
	
    private final BlockingQueue<UserEntity> queue = new LinkedBlockingQueue<>();
      
	@Async
	public void runAsyncJob(String jobName) {
		System.out.println("starting async job:" +jobName +"on thread:" + Thread.currentThread());
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		   Thread.currentThread().interrupt();
		}
		System.out.println("Finished async jobs: "+jobName);
	}

	
	
    public void enqueueUser(UserEntity user) {
        queue.add(user); 
    }

	
	@Scheduled(fixedRate = 60000)
	public void sheduledJob()
	{
		System.out.println("Scheduled job running at:" +java.time.LocalDate.now());
	}
}
