
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

  Background:
  Given I landed on Ecommerce page

  @tag2
  Scenario Outline: Possitive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product<productName> to cart
    And Checkout<productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is diplayed on ConfirmationPage

    Examples: 
      | name  									| password 		 | productName 		 |
      | rahulshetty@gmail.com 	| IamKing@000  | ADIDAS ORIGINAL |
      | maazshaikh95@gmail.com 	| Manpower@007 | ZARA COAT 3 		 |
