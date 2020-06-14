Feature: SDET - BDD Testing

Scenario Outline: Make My Trip Flight Booking  
Given Open "<Browser>"
When MakemyTrip Booking
|Chennai|Coimbatore|
Then Veriify the Page
Examples: 
| Browser |
| Chrome  |
| FFox    |

Scenario Outline: Make My Trip Flight Booking  
Given Open "<Browser>"
When MakemyTrip Booking
|Madurai|Coimbatore|
Then Veriify the Page
Examples: 
| Browser |
| Chrome  |
| FFox    |

Scenario Outline: Gillette Testing - India  
Given Open "<Browser>"
When Gillette India create account
And Sign in and Sign out
And forgot passwprd
Then Verify gillette india 
Examples: 
| Browser |
| Chrome  |
| FFox    |


Scenario Outline: Gillette Testing - German  
Given Open "<Browser>"
When Gillette German create account
And Sign in and Sign out - German
And forgot passwprd - German
Then Verify gillette - German 
Examples: 
| Browser |
| Chrome  |
| FFox    |


Scenario Outline: Gillette Testing - France  
Given Open "<Browser>"
When Gillette France create account
#And Sign in and Sign out - France
#And forgot passwprd - France         Commented the these steps, because https://www.gillette.fr/ using Captcha validation
#Then Verify gillette - France 
Examples: 
| Browser |
| Chrome  |
| FFox    |