FROM node:14.15.0 AS builder
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

FROM nginx:alpine
COPY --from=builder /app/dist/front /usr/share/nginx/html
EXPOSE 4200
