# springboot-prometheus-demo
`cd springboot-prometheus-demo
./mvnw clean package -DskipTests`

http://localhost:8080/hello

# springboot-prometheus-monitoring

▶️ Run Everything
From the root directory:
`docker-compose up --build`

🔗 Access Services

1. Spring Boot	http://localhost:8080
2. Prometheus	http://localhost:9090
3. Grafana	http://localhost:3000	

📊 Grafana Setup
1. Go to http://localhost:3000

2. Add Prometheus as a data source:
   URL: http://prometheus:9090

3. Import Dashboard:
   Use the dashboard JSON or ID: 4701

4. View metrics like JVM, HTTP requests, custom counters (custom_hello_requests_total), etc.

✅ Grafana Dashboard (Optional)
You can use this Spring Boot Micrometer dashboard JSON or create your own.
Place it in: grafana/dashboards/springboot-dashboard.json
You can later auto-provision it if desired.

🧪 Quick Test: Container IP
You can also get Prometheus container IP:

`docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' <prometheus_container_id>`

Use that IP in Grafana's data source temporarily (not ideal long-term).

✅ Troubleshooting Steps
🔍 1. Check if Metrics Exist
Go to Explore tab in Grafana and:

Select your Prometheus data source

Try running a basic query like:

prometheus
`1. up`
`2. http_server_requests_seconds_count`

✅ If this shows results → data is flowing
❌ If no data is returned → metrics are missing or Prometheus is not scraping them.

🔍 Check Prometheus Targets
http://localhost:9090/targets

* Ensure the status is "UP"
* Check the "Last Scrape" time
* If it says "DOWN", then Prometheus is not able to scrape your Spring Boot app.

🧪 3. Verify Your Spring Boot App Is Exposing Metrics
Ensure your Spring Boot app has these:

✅ In build.gradle
`dependencies {
runtimeOnly 'org.springframework.boot:spring-boot-devtools'
}`

✅ In application.yml:
`management:
  endpoints:
    web:
      exposure:
        include: health,info,prometheus
  endpoint:
    prometheus:
      enabled: true
  metrics:
    export:
      prometheus:
        enabled: true`

✅ Check that it exposes /actuator/prometheus:

`curl http://localhost:8080/actuator/prometheus`

🔍 4. Check Prometheus prometheus.yml
Ensure your Spring Boot app is a target:

`scrape_configs:
- job_name: 'springboot'
  static_configs:
    - targets: ['host.docker.internal:8080']
      Replace host.docker.internal with the correct IP or container name if in Docker.`

🔍 5. Dashboard Time Range
In Grafana dashboard, check:
Time range (top-right corner)
Try setting to “Last 1 hour” or “Last 6 hours”
Click Refresh 🔄

🔍 6. Panel Query is Empty or Wrong
Open the graph panel:
Click on panel title → Edit
Check the query field (usually something like `rate(http_server_requests_seconds_count[1m])`)
Run it manually in the Explore tab
If no data → issue with query or target metric doesn’t exist.

