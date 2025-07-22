# springboot-prometheus-demo
cd springboot-app
./mvnw clean package -DskipTests

# springboot-prometheus-monitoring

▶️ Run Everything
From the root directory:
docker-compose up --build

🔗 Access Services

1.Spring Boot	http://localhost:8080
2.Prometheus	http://localhost:9090
3.Grafana	http://localhost:3000	

📊 Grafana Setup
1.Go to http://localhost:3000

2.Add Prometheus as a data source:
URL: http://prometheus:9090

3.Import Dashboard:
Use the dashboard JSON or ID: 4701

4.View metrics like JVM, HTTP requests, custom counters (custom_hello_requests_total), etc.