# RESTful Restaurants

This repo contains a working redux app which makes API calls for CRUD on restaurants and reviews.

It follows idiomatic RESTful conventions, including nested routes for the SPA and the API.

## TODO

Unfortunately, all the API calls are failing, because it hasn't been written yet. Your job is to:

1. Start the server in your IDE
1. `cd client && yarn install && yarn start` to run the client
1. Open the network panel in chrome
1. Use exploratory testing to cause the client to make API calls to the server
1. Note the API route, method, and body of each request
1. Implement the necessary server code to handle the requests
1. When all requests function correctly, you are done!

## Execution Notes

1. Be sure to create both controllers and services, placing the correct logic in each
1. Do _not_ use a database - keep all data in memory
1. Write unit tests for your services