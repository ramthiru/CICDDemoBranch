package com.wow.rpc.TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class TestngAnnotations {
// Test Case 1

@Test
public void testCase1(){
	
	System.out.println("TestCase-1");
	
}
 @Test
 public void testCase2(){
	 
	 System.out.println("TestCase-2");
 }
 
 @BeforeClass
 public void testClass(){
	 
	 System.out.println("before class");
	 
 }
 @AfterClass
 public void testClass1(){
	 
	 System.out.println("After Class");
 }
 
 @BeforeSuite
 public void beforeSuite(){
	 System.out.println("Before suite");
 }
 
 @AfterSuite
 public void afterSuite(){
	 
	 System.out.println("After Suite");
	 
 }
 @BeforeTest
 public void beforeTest(){
	 System.out.println("Before Test");
 }
 
 @AfterTest
 public void afterTest(){
	 
	 System.out.println("After Test");
	 
 }
 
 @BeforeMethod
 public void beforeMethod(){
	 System.out.println("Before Method");
 }
 
 @AfterMethod
 public void afterMethod(){
	 
	 System.out.println("After Method");
	 
 }
 
 
	
}