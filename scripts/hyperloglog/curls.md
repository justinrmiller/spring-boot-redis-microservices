Add a user visit for the main site (adding the user multiple times should result in the same count):

curl -X POST http://localhost:8080/hyperloglog/key/user_main_site_visits/value/1

Get a count for the number of visitors to the main site:

curl http://localhost:8080/hyperloglog/key/user_main_site_visits
