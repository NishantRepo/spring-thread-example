package com.nishant;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
class SpringThreadExampleApplicationTests {

	@Test
	void contextLoads() throws ExecutionException, InterruptedException {

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(100);
		executor.setMaxPoolSize(100);
		executor.setThreadNamePrefix("thread-test-");
		executor.initialize();

		extracted(executor);
//		CompletableFuture<String> feature = CompletableFuture.supplyAsync(getStringSupplier(1000), executor);
//		CompletableFuture<String> feature1 = CompletableFuture.supplyAsync(getStringSupplier(5000), executor);
//		CompletableFuture<String> feature2 = CompletableFuture.supplyAsync(getStringSupplier(3000), executor);
//
//		log.info(feature.get());
//		log.info(feature1.get());
//		log.info(feature2.get());
	}

	private static void extracted(ThreadPoolTaskExecutor executor) throws InterruptedException, ExecutionException {
		IntStream.rangeClosed(0, 100)
				.parallel()
				.mapToObj(i -> CompletableFuture.supplyAsync(getStringSupplier(), executor))
				.forEach(feature -> {
                    try {
                        log.info(feature.get());
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                });
//		for(int i= 0; i<50; i++) {
//			CompletableFuture<String> feature = CompletableFuture.supplyAsync(getStringSupplier(1000), executor);
//			log.info(feature.get());
//		}
	}

	private static Supplier<String> getStringSupplier() {
		return () -> {
			try {
				log.info("thread started.." + Thread.currentThread().getName());
				Thread.sleep(1000);
				log.info("thread ended.." + Thread.currentThread().getName());
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return   " Success!!! " + Thread.currentThread().getName();
		};
	}



}
























