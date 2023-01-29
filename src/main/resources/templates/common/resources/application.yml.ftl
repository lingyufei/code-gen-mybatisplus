server:
  port: 8080

spring:
  datasource:
    username: root
    password: root
    url: jdbc:mysql://localhost:3306/xxx
    driver-class-name: com.mysql.cj.jdbc.Driver
<#if !getOptionalSchema().ignoreRedis>
  redis:
    host: localhost
    port: 6379
    database: 0
</#if>

mybatis-plus:
  mapper-locations: classpath:/mapper/**/*.xml
  global-config:
    db-config:
      id-type: auto

logging:
  level:
    ${packageName}: debug