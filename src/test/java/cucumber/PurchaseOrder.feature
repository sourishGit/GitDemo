@tag
Feature: Purchase the order from E-commerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on e-commerce website

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <username> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit order
    Then "Thankyou for the order." message is displayed on Confirmation page

    Examples: 
      | username  		| password | productName  |
      | alb@gmail.com | Abc@1234 | ZARA COAT 3 	|
      
