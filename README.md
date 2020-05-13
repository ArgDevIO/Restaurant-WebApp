

# Restaurant WebApp

## About
***Project work for Web Programming course, [FCSE](https://finki.ukim.mk/en)***   

Designed & developed by:
 - **Argetim Ramadani** (*161553*) - GitHub account: [ArgDevIO ](https://github.com/ArgDevIO) 
 - **Musli Ademi** (*151540*) - GitHub account: [mustran ](https://github.com/mustran)

-**Restaurant WebApp** is a Full Stack WebApp mainly used for online food ordering, inspired by the lack of this kind of systems in our city and the poor user experience while ordering food by phone calls which leads to a ton of incorrect orders and unhappy customers...
### App Features:
- Authentication (JWT)
	- e-mail
	- facebook
- Multilanguage 
- OTP phone verification  
- Precise user delivery address, using Map coordinates
- Delivery cost calculation
- Online food menu
- Online ordering
- Online payment gateway
- Real time order tracking
- Online booking/reservations
- Loyalty program
- Admin panel
- Employee panel
- User panel


### Technologies & frameworks used:
- **Backend**:
	- [Spring Boot](https://spring.io/projects/spring-boot)
	- [Spring Security](https://spring.io/projects/spring-security)
	- [Spring Data](https://spring.io/projects/spring-data)
	- [Spring WebSocket](https://spring.io/guides/gs/messaging-stomp-websocket/)
	- [JWT](https://jwt.io/)
	- [MySQL](https://www.mysql.com/)
	- [Google Maps](https://developers.google.com/maps/documentation)
	- [Twilio](https://www.twilio.com/)   

- **Frontend**:
	- [ReactJS](https://reactjs.org/) 
	- [React Hooks](https://reactjs.org/docs/hooks-intro.html)
	- [React Router](https://reacttraining.com/react-router/web/guides/quick-start)
	- [Redux](https://redux.js.org/)
	- [Styled-Components](https://styled-components.com/)
	- [Formik](https://jaredpalmer.com/formik/)
	
- **DevOps**:
	- [Docker](https://www.docker.com/) 

## Prerequisites to run this app
- Java 11>
- Docker
- mvn or IDE
- Yarn

## Steps to Setup the Spring Boot Back end app ([Spring]-RestaurantWebApp)

  

1.  **Clone the application**

  

```bash

git clone https://github.com/ArgDevIO/Restaurant-WebApp.git

cd \[Spring\]-RestaurantWebApp

```

  

2.  **Run docker container**

This commands starts the docker container in the background so we can continue using the same terminal window
```bash

docker-compose up -d # This will start the mysql image on port 3306

```
To stop & remove the above started container and images use this command
```bash
docker-compose down
```

4.  **Run the app**

  

You can run the spring boot app by typing the following command

```bash

mvn spring-boot:run

```
The server will start on port **8080**.

After successfully running the spring boot app, it will automatically create the default Admin user with role `ROLE_ADMIN` 
- Admin, default credentials:
	- email:   `admin@garden.com`
	- passw: `admin@admin1` 

Any new user who registers to the app is assigned the `ROLE_USER` by default.

> If you don't have `mvn` installed, you can also open the
> **[Spring]-RestaurantWebApp** as a project on your **IDE** and run it from there

## Steps to Setup the React Front end app ([ReactJS]-RestaurantWebApp)

  

First go to the `[ReactJS]-RestaurantWebApp` folder

```bash

cd [ReactJS]-RestaurantWebApp

```

Then type the following command to install the dependencies and start the application -

```bash

yarn install && yarn start

```

  

The front-end server will start on port `3000`.
