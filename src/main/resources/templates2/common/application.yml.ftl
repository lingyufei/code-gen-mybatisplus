spring:
  datasource:
    username: root
    password: root
    url: jdbc:mysql://localhost:3306/xxx
    driver-class-name: com.mysql.cj.jdbc.Driver


mybatis-plus:
  mapper-locations: classpath:/mapper/**/*.xml
  global-config:
    db-config:
      id-type: auto

logging:
  level:
    ${packageName}: debug