@double11_event
Feature: Double 11 Shopping Festival Discount
  As a shopper during Double 11 event
  I want to get bulk discount for same products
  So that I can save money when buying multiple items of the same product

  @scenario1
  Scenario: Buy 12 pieces of same product with Double 11 discount
    Given the Double 11 promotion is active
    When a customer places an order with:
      | productName | quantity | unitPrice |
      | 襪子          | 12       | 100       |
    Then the order summary should be:
      | totalAmount |
      | 1000        |
    And the customer should receive:
      | productName | quantity |
      | 襪子          | 12       |

  @scenario2
  Scenario: Buy 27 pieces of same product with Double 11 discount
    Given the Double 11 promotion is active
    When a customer places an order with:
      | productName | quantity | unitPrice |
      | 襪子          | 27       | 100       |
    Then the order summary should be:
      | totalAmount |
      | 2300        |
    And the customer should receive:
      | productName | quantity |
      | 襪子          | 27       |

  @scenario3
  Scenario: Buy 10 different products without Double 11 discount
    Given the Double 11 promotion is active
    When a customer places an order with:
      | productName | quantity | unitPrice |
      | 商品A         | 1        | 100       |
      | 商品B         | 1        | 100       |
      | 商品C         | 1        | 100       |
      | 商品D         | 1        | 100       |
      | 商品E         | 1        | 100       |
      | 商品F         | 1        | 100       |
      | 商品G         | 1        | 100       |
      | 商品H         | 1        | 100       |
      | 商品I         | 1        | 100       |
      | 商品J         | 1        | 100       |
    Then the order summary should be:
      | totalAmount |
      | 1000        |
    And the customer should receive:
      | productName | quantity |
      | 商品A         | 1        |
      | 商品B         | 1        |
      | 商品C         | 1        |
      | 商品D         | 1        |
      | 商品E         | 1        |
      | 商品F         | 1        |
      | 商品G         | 1        |
      | 商品H         | 1        |
      | 商品I         | 1        |
      | 商品J         | 1        |