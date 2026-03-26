# Swag Labs Test Case Catalog

This project now includes 50 UI test cases mapped to the automated TestNG suite and Allure report.

## Login

| ID | Scenario | Automated In |
|---|---|---|
| TC-001 | Log in successfully with `standard_user`. | `LoginTest` |
| TC-002 | Log in successfully with `problem_user`. | `LoginTest` |
| TC-003 | Log in successfully with `performance_glitch_user`. | `LoginTest` |
| TC-004 | Log in successfully with `error_user`. | `LoginTest` |
| TC-005 | Log in successfully with `visual_user`. | `LoginTest` |
| TC-006 | Confirm `locked_out_user` cannot log in. | `NegativeLoginTest` |
| TC-007 | Validate error when username is blank. | `NegativeLoginTest` |
| TC-008 | Validate error when password is blank. | `NegativeLoginTest` |
| TC-009 | Validate error when username and password are blank. | `NegativeLoginTest` |
| TC-010 | Validate error for invalid credentials. | `NegativeLoginTest` |

## Inventory

| ID | Scenario | Automated In |
|---|---|---|
| TC-011 | Sort products by name ascending. | `AddToCartTest` |
| TC-012 | Sort products by name descending. | `AddToCartTest` |
| TC-013 | Sort products by price ascending. | `AddToCartTest` |
| TC-014 | Sort products by price descending. | `AddToCartTest` |
| TC-015 | Verify the inventory page displays six products. | `AddToCartTest` |
| TC-016 | Add `Sauce Labs Backpack` from inventory. | `AddToCartTest` |
| TC-017 | Add `Sauce Labs Bike Light` from inventory. | `AddToCartTest` |
| TC-018 | Add `Sauce Labs Bolt T-Shirt` from inventory. | `AddToCartTest` |
| TC-019 | Add `Sauce Labs Fleece Jacket` from inventory. | `AddToCartTest` |
| TC-020 | Add `Sauce Labs Onesie` from inventory. | `AddToCartTest` |
| TC-021 | Add `Test.allTheThings() T-Shirt (Red)` from inventory. | `AddToCartTest` |
| TC-022 | Remove `Sauce Labs Backpack` from inventory after adding it. | `AddToCartTest` |
| TC-023 | Remove `Sauce Labs Bike Light` from inventory after adding it. | `AddToCartTest` |
| TC-024 | Remove `Sauce Labs Bolt T-Shirt` from inventory after adding it. | `AddToCartTest` |
| TC-025 | Remove `Sauce Labs Fleece Jacket` from inventory after adding it. | `AddToCartTest` |

## Cart

| ID | Scenario | Automated In |
|---|---|---|
| TC-026 | Verify cart shows one selected product. | `CartTest` |
| TC-027 | Verify cart shows two selected products. | `CartTest` |
| TC-028 | Verify cart shows three selected products. | `CartTest` |
| TC-029 | Verify cart shows all six selected products. | `CartTest` |
| TC-030 | Remove `Sauce Labs Backpack` from cart. | `CartTest` |
| TC-031 | Remove `Sauce Labs Bike Light` from cart. | `CartTest` |
| TC-032 | Remove `Sauce Labs Onesie` from cart. | `CartTest` |
| TC-033 | Continue shopping from cart and return to inventory. | `CartTest` |

## Checkout Information

| ID | Scenario | Automated In |
|---|---|---|
| TC-034 | Block checkout when first name is missing. | `CheckoutInformationTest` |
| TC-035 | Block checkout when last name is missing. | `CheckoutInformationTest` |
| TC-036 | Block checkout when postal code is missing. | `CheckoutInformationTest` |
| TC-037 | Block checkout when only first name is entered. | `CheckoutInformationTest` |
| TC-038 | Block checkout when only last name is entered. | `CheckoutInformationTest` |
| TC-039 | Block checkout when only postal code is entered. | `CheckoutInformationTest` |
| TC-040 | Accept valid checkout data for customer profile 1. | `CheckoutInformationTest` |
| TC-041 | Accept valid checkout data for customer profile 2. | `CheckoutInformationTest` |
| TC-042 | Accept valid checkout data with alphanumeric postal code. | `CheckoutInformationTest` |

## Checkout Completion

| ID | Scenario | Automated In |
|---|---|---|
| TC-043 | Complete checkout with one product. | `CheckOutTest` |
| TC-044 | Complete checkout with two products. | `CheckOutTest` |
| TC-045 | Complete checkout with three products. | `CheckOutTest` |
| TC-046 | Complete checkout with all six products. | `CheckOutTest` |
| TC-047 | Verify order summary totals for a single backpack order. | `CheckOutTest` |
| TC-048 | Verify order summary totals for backpack plus onesie. | `CheckOutTest` |
| TC-049 | Cancel checkout from the information step and return to cart. | `CheckOutTest` |
| TC-050 | Cancel checkout from the overview step and return to products. | `CheckOutTest` |
