#Themis - the business rule engine for zee services
##Spring Boot :  2.6.3
### Steps to bootstrap the project

- clone the repo : https://bitbucket.org/zee5in/themis/src/master/
- go to project root
- from terminal enter : mvn clean install

### mvel + dsl based rule engine which can be customized for domain specific rule handling (payment , order et al)
### components
 - dsl parser for resolving domain specific keywords
 - mvel parser for resolving the final input and output mvel expresession
 - rule parser for getting and applying rule set on the given input
 - mongo db for storing the rules
 - mvel format rules definition 
   - /resources/payment-rules.ini
   - /resources/order-rules.ini

###Dependencies included
- actuator
- dev tools
- lombok
- web
- prometheus
- swagger 3
- aop
- guava
- zipkin
- document db
- security
- mvel




