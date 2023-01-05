package com.example.randomImage.bucket;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

@Component
public class BucketWrapper {
	Bucket bucket;

	public BucketWrapper(
			@Value("${bucket.refill}") int bucketRefill,
			@Value("${bucket.refillDurationInSeconds}") int bucketRefillDurationInSeconds,
			@Value("${bucket.capacity}") int bucketCapacity
		) {
		Refill refill = Refill.intervally(bucketRefill, Duration.ofSeconds(bucketRefillDurationInSeconds));
		Bandwidth limit = Bandwidth.classic(bucketCapacity, refill);
		bucket = Bucket.builder()
			.addLimit(limit)
		    .build();
	}
	
	public boolean tryConsume() {
		return bucket.tryConsume(1);
	}
	
}
