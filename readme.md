

# 💡 used tech

---

- SpringBoot 3.3.4
- SpringSecurity
- Java 17
- JPA
- Mysql
- Jwt token 0.12.3

# 💡ERD

---

![check erd.png](check_erd.png)

# 💡API

---

## Manager

- 결과
    
    경로[POST] : [http://localhost:8080/api/register/manager](http://localhost:8080/api/register/manager) 
    
    요청바디
    
    ```json
    
    {
    "email": "[manager@gmail.com](mailto:manager@gmail.com)",
    "password": "12345",
    "phoneNumber": "010-1111-2222",
    "username": "매니저"
    }
    ```
    
    결과
    
    ```json
    
    {
    "manId": 1,
    "username": "매니져",
    "password": "$2a$10$KGnP.s5CVAQ/CKbugytbwekaadf/Z3LbE75MnUNRCYYmKToB9ObKO",
    "email": "[manager@gmail.com](mailto:manager@gmail.com)",
    "phoneNumber": "010-1111-2222",
    "memberType": "PARTNER"
    }
    ```
    
    요청[POST] [http://localhost:8080/api/login/manager](http://localhost:8080/api/login/manager) 
    
    요청 바디
    
    ```json
    
    {
    "email": "[manager@gmail.com](mailto:manager@gmail.com)",
    "password": "12345"
    }
    ```
    
    결과
    
    ```json
    
    Login successful
    ```
    
    요청 [GET] [http://localhost:8080/api/partner/info?id=1](http://localhost:8080/api/partner/info?id=1) 
    
    결과
    
    ```json
    {
    "manId": 1,
    "username": "매니저",
    "password": "$2a$10$KGnP.s5CVAQ/CKbugytbwekaadf/Z3LbE75MnUNRCYYmKToB9ObKO",
    "email": "[manager@gmail.com](mailto:manager@gmail.com)",
    "phoneNumber": "010-1111-2222",
    "memberType": "PARTNER"
    }
    ```
    
    요청[POST] [http://localhost:8080/api/store/partner/create](http://localhost:8080/api/store/partner/create) 
    
    요청 바디
    
    ```json
    
    {
    "storeName": "store0"
    }
    ```
    
     
    요청[GET]  [http://localhost:8080/api/store/partner/list?managerId=1](http://localhost:8080/api/store/partner/list?managerId=1) 
    
    결과
    
    ```json
    
    {
    "manager": {
    "regAt": "2024-10-05T22:15:12.193773",
    "updAt": "2024-10-05T22:15:12.193773",
    "manId": 1,
    "username": "매니저",
    "password": "$2a$10$KGnP.s5CVAQ/CKbugytbwekaadf/Z3LbE75MnUNRCYYmKToB9ObKO",
    "phoneNumber": "010-1111-2222",
    "email": "[manager@manager.com](mailto:manager@manager.com)",
    "memberType": "PARTNER",
    "enabled": true,
    "authorities": [
    {
    "authority": "ROLE_PARTNER"
    }
    ],
    "accountNonExpired": true,
    "credentialsNonExpired": true,
    "accountNonLocked": true
    },
    "storeName": store0",
    "location": "대한민국",
    "phoneNumber": "031-1234-5678"
    }
    ```
    
    요청[PUT] [http://localhost:8080/api/store/partner/update/1](http://localhost:8080/api/store/partner/update/1) 
    
    ```json
    
    {
    "storeName": "store1",
    "location": "일본",
    "managerId": 1
    }
    
    ```
    
    결과
    
    ```json
    
    {
    "storeName": "store1",
    "location": "일본"
    }
    ```
    
    요청[PUT]  [http://localhost:8080/api/reservation/partner/reservation-list/1](http://localhost:8080/api/reservation/partner/reservation-list/1) 
    
    결과
    
    ```json
    
    {
    "reservationList": [
    {
    "reservationId": 1,
    "username": "유저",
    "userPhoneNumber": "010-1111-2222",
    "storeName": "store0",
    "status": "WAIT_FOR_APPROVAL",
    "arrivalStatus": "READY",
    "reservationDate": "2024-10-05",
    "reservationTime": "16:00:00"
    }
    ]
    }
    ```
    
    요청[PUT] [http://localhost:8080/api/reservation/partner/approval/1](http://localhost:8080/api/reservation/partner/approval/1) 
    
    요청 바디
    
    ```json
    
    {
    "reservationStatus": "APPROVE"
    }
    {
    "reservationId": 1,
    "username": "유저",
    "storeName": "store0",
    "reservationStatus": "APPROVE",
    "reservationDate": "2024-10-05",
    "reservationTime": "16:00:00"
    }
    
    ```
    
    요청[PUT] [http://localhost:8080/api/reservation/changearrive/1](http://localhost:8080/api/reservation/changearrive/1) 
    
    요청 바디
    
    ```json
    {
    "username": "유저",
    "phoneNumber": "010-1111-2222",
    "arrivalTime": "2024-10-05T15:50:00.000Z"
    }
    ```
    
    결과
    
    ```json
    
    {
    "reservationId": 1,
    "username": "유저",
    "storeName": "store0",
    "status": "FINISHED",
    "arrivalStatus": "ARRIVED"
    }
    
    ```
    
    요청[DELETE] [http://localhost:8080/api/review/delete/1](http://localhost:8080/api/review/delete/1) 
    
    결과
    
    ```json
    리뷰가 삭제되었습니다.
    ```
    

## Member

- 결과
    

    요청바디[POST]  [http://localhost:8080/api/register/member](http://localhost:8080/api/register/member) 


     ```json
    {
    "email": "[user@user.com](mailto:user@user.com)",
    "password": "12345",
    "phoneNumber": "010-1111-2222",
    "username": "유저"
    }
    ```
    
    결과
    
    ```json
    
    {
    "userId": 1,
    "username": "유저",
    "password": "$2a$10$8yL7Nz1TU5UV88XFL5cbeuYNOwA8Ukqf0YMzq6YxWDEhUVa0NAlf.",
    "phoneNumber": "010-1111-2222",
    "email": "[user@user.com](mailto:user@user.com)",
    "memberType": "MEMBER"
    }
    
    ```
    
    요청[POST] [http://localhost:8080/api/login/member](http://localhost:8080/api/login/member) 
    
    입력 바디
    
    ```json
    
    {
    "email": "[user@user.com](mailto:user@user.com)",
    "password": "12345"
    }
    ```
    
    요청[GET] [http://localhost:8080/api/member/info?id=1](http://localhost:8080/api/member/info?id=1) 
    
    결과
    
    ```json
    
    {
    "userId": 1,
    "username": "유저",
    "password": "$2a$10$8yL7Nz1TU5UV88XFL5cbeuYNOwA8Ukqf0YMzq6YxWDEhUVa0NAlf.",
    "phoneNumber": "010-1111-2222",
    "email": "[user@user.com](mailto:user@user.com)",
    "memberType": "MEMBER"
    }
    
    ```
    
    요청[GET] [http://localhost:8080/api/store/detail/store00](http://localhost:8080/api/store/detail/store00) 
    
    결과
    
    ```json
    
    {
    "manager": {
    "regAt": "2024-10-05T23:19:06.316092",
    "updAt": "2024-10-05T23:19:06.316092",
    "manId": 1,
    "username": "매니저",
    "password": "$2a$10$MXgmdCE7uBI5dVm6aJZwue4dTYph99kLZbP5tqqtH4ZlvpVpf/4.W",
    "phoneNumber": "010-1111-2222",
    "email": "[manager@manager.com](mailto:manager@manager.com)",
    "memberType": "PARTNER",
    "enabled": true,
    "accountNonLocked": true,
    "authorities": [
    {
    "authority": "ROLE_PARTNER"
    }
    ],
    "accountNonExpired": true,
    "credentialsNonExpired": true
    },
    "storeName": "store0",
    "location": "대한민국",
    "phoneNumber": "02-1234-5670"
    }
    ```
    
      요청 [POST] [http://localhost:8080/api/reservation/create](http://localhost:8080/api/reservation/create) 
    
    요청 바디
    
    ```json
    
    {
    "username": "유저",
    "userPhoneNumber": "010-1111-2222",
    "storeName": "store0",
    "reservationDate": "2024-10-05",
    "reservationTime": "16:00:00"
    }
    
    ```
    
    요청[POST] [http://localhost:8080/api/review/create?userId=1&storeId=1&reservationId=1](http://localhost:8080/api/review/create?userId=1&storeId=1&reservationId=1)
    
    요청 바디
    
    ```json
    
    {
    "content": "다음에 올게요",
    "rating": 5
    }
    ```
    
    결과
    
    ```json
    
    {
    "reviewId": 1,
    "content": "다음에 올게요",
    "rating": 5.0,
    "username": "유저",
    "storeName": "store0"
    }
    ```
