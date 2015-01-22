Add a feature flag (MEGA_FEATURE) for application "app" with flag definition found in flag.json (note: the name in the json must match the name in the path):

curl -X POST -d@flag.json -H "Content-Type: application/json" http://localhost:8080/flags/application/app/feature/MEGA_FEATURE

Retrieve all feature flags for application "app":

curl http://localhost:8080/flags/application/app

