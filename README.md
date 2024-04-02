# Getting Started with Docker Compose

To run the project, it's necessary to have Docker Compose installed [Install docker-compose](https://docs.docker.com/compose/install/).

## Commands

In the project directory, you can run:

### `docker-compose up -d`

Runs the app in the development mode.\
Open [http://localhost:10084](http://localhost:10084) to view mongo-express in your browser.
The page shows you all databases and collections available.\

Open [http://localhost:8080](http://localhost:8080) to begin consuming the services.
You can consume every resource with the help of the command curl or some UI tool like Postman, Insomnia, etc.


### `docker-compose stop`

Stops all containers that are currently running.

### `docker-compose down`

If you want to stop and destroy all containers and networks used for the compose, run this command!

### `docker volume remove nexu_mongo_volume`

If you want to restart all data storage on mongo, you should run this command.

**Note: you need to stop and destroy the mongo container before running this command!**

This is one way to set up the challenge. However, if you have some experience configuring a Java development environment, you need to install Java JDK 1.8+ (this project is configured with Java 17, but you can run it with other versions like 8 or above, and remember to only change the Spring Boot version compatible with the Java version you want to use). Another tool needed is Maven; in this case, I used version 3.8.3.

For checking and editing Java, you can use Eclipse (STS has all the basic plugins for working with Spring) or IntelliJ IDEA, which is the best option on the market.

In this case, I used a `UUID` instead of a conventional `ID` because MongoDB versions above 4.2 deprecate the function $eval, which was used to execute commands like storage functions. My idea was to create a function that generates a sequence and assigns these values to every record.

# Services Availables

```
                              GET    /brands
                              GET    /brands/:id/models
                              POST   /brands
                              POST   /brands/:id/models
                              PUT    /models/:id
                              GET    /models
```

#### GET /brands

List all brands 
```json
[
  {"id": "660bbecea22e6bae6bdb85b5", "nombre": "Acura", "average_price": 702109},
  {"id": "660bd1812e79b74ed307f4ce", "nombre": "Audi", "average_price": 630759},
  {"id": "660bbecea22e6bae6bdb85b4", "nombre": "Bentley", "average_price": 3342575},
  {"id": "660bbecea22e6bae6bdb85b0", "nombre": "BMW", "average_price": 858702},
  "..."
]
```

#### GET /brands/:id/models

List all models of the brand
```json
[
  {"id": "660bbecea22e6bae6bdb85ac", "name": "ILX", "average_price": 303176},
  {"id": "660bbecea22e6bae6bdb85ab", "name": "MDX", "average_price": 448193},
  {"id": "660bbecea22e6bae6bdb85af", "name": "NSX", "average_price": 3818225},
  {"id": "660bbecea22e6bae6bdb85b2", "name": "RDX", "average_price": 395753},
  {"id": "660bbecea22e6bae6bdb85b3", "name": "RL", "average_price": 239050}
]
```

#### POST /brands

You may add new brands. A brand name must be unique.

```json
{"name": "Toyota"}
```

If a brand name is already in use return a response code and error message reflecting it.


#### POST /brands/:id/models

You may add new models to a brand. A model name must be unique inside a brand.

```json
{"name": "Prius", "average_price": 406400}
```
If the brand id doesn't exist return a response code and error message reflecting it.

If the model name already exists for that brand return a response code and error message reflecting it.

Average price is optional, if supply it must be greater than 100,000.


#### PUT /models/:id

You may edit the average price of a model.

```json
{"average_price": 406400}
```
The average_price must be greater then 100,000.

#### GET /models?greater=&lower=

List all models. 
If greater param is included show all models with average_price greater than the param
If lower param is included show all models with average_price lower than the param
```
# /models?greater=380000&lower=400000
```
```json
[
  {"id": 1264, "name": "NSX", "average_price": 3818225},
  {"id": 3, "name": "RDX", "average_price": 395753}
]
```
