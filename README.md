# Identify-Reconcilience
Live API
POST https://render.com/docs/web-services#port-binding/identify

Source Code
GitHub: https://github.com/saravanakumar-2003/Identify-Reconcilience.git

Use Case Scenario
Users like “Doc Brown” often use a mix of emails and phone numbers across transactions. To ensure consistent personalization and avoid fragmented user experiences, the system aims to identify and merge such scattered contact data under a unified identity on platforms like FluxKart.

Sample Input
POST /identify
Content-Type: application/json

{
  "email": "brown@fluxuniv.com",
  "phoneNumber": "123456"
}

Sample Output
{
  "contact": {
    "primaryContactId": 5,
    "emails": ["brown@fluxuniv.com", "emmett@timeflux.com"],
    "phoneNumbers": ["123456", "789012"],
    "secondaryContactIds": [8, 12]
  }
}

Technology Stack
Backend: Java 21, Spring Boot 3
Database: PostgreSQL
ORM: JPA / Hibernate



