Feature: Error message Validation on login screen of E-Commerce Website
  I want to use this template for my feature file
  

  @tag2
  Scenario Outline: Validate error message with wrong credentials
  	Given I landed on e-commerce website
    When Logged in with username <username> and password <password>
   Then "Incorrect email or password." message is displayed

   Examples: 
      | username  		| password | 
      | alb@gmail.com | Abc@12345 |
