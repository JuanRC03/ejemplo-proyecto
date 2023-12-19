# Construye la aplicación Angular
FROM node:18 as build-step  
WORKDIR /app
COPY . .
RUN npm install
RUN npm install -g @angular/cli  # Instala Angular CLI
RUN ng build --configuration=production

# Configura el servidor web NGINX
FROM nginx:1.17.1-alpine
COPY --from=build-step /app/dist/frontend /usr/share/nginx/html