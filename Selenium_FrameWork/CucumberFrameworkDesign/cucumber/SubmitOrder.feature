@tag 1
  Feature: sav
    Background:
      Given : I have to land on e-commerce website
    Scenario:
      Given : Logging in valid user credentials <Email> and <Password>
      When : I'm adding <Product Name> into cart
      And : then checking out from payment page
      Then : Conformation message should be display

      Example:
        |          Email          |   Password   | Product Name |
        | dummykannapa2@gmail.com | Password@123 | ZARA COAT 3 |