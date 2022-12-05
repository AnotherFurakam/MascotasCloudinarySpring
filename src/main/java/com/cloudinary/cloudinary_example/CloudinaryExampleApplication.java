package com.cloudinary.cloudinary_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cloudinary.Cloudinary;
import com.cloudinary.SingletonManager;
import com.cloudinary.utils.ObjectUtils;

@SpringBootApplication
public class CloudinaryExampleApplication {

	public static void main(String[] args) {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "furakam",
				"api_key", "549252233141922",
				"api_secret", "XSIHIHTw1t8GsujGYMR2AJ_eaAs"));

		SingletonManager manager = new SingletonManager();
		manager.setCloudinary(cloudinary);
		manager.init();

		SpringApplication.run(CloudinaryExampleApplication.class, args);
	}

}
