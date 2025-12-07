# Online Event Management System (OEMS)

**Developer:** Piyush Ranjan  
**Roll No.:** 24SCSE1180555

## Overview
A Java web application to create, manage, and book events. Implemented using Servlets, JSP, JDBC and MySQL. Includes role-based controls for Admin, Organizer and Attendee.

## Features
- User registration and login (admin/organizer/attendee)
- Organizer: create events (sent for admin approval)
- Admin: approve/reject events
- Attendee: view approved events, book tickets
- Booking generates a QR code (Google Chart API) for ticket verification
- Client-side and server-side validation, exception handling

## Technology
- Java (Servlets & JSP)
- JDBC + MySQL
- Tomcat 9+ (or any servlet container)
- HTML, CSS, JavaScript (for client validation)

## Setup (local)
1. Install MySQL and create DB:
   - Run `database/database.sql` in MySQL to create DB and tables.
2. Update DB credentials:
   - Edit `src/com/oems/util/DBConnection.java` to set DB user/password.
3. Build & deploy:
   - Use your IDE (Eclipse/IntelliJ) to create a Dynamic Web Project and copy the `webapp` content to `WebContent`.
   - Add MySQL JDBC driver to `WEB-INF/lib`.
   - Deploy WAR to Tomcat and start Tomcat.
4. Default pages:
   - `http://localhost:8080/<app>/index.jsp`

## Testing Checklist (for Review-2)
- [ ] Register organizer & attendee
- [ ] Organizer creates an event
- [ ] Admin approves the event
- [ ] Attendee views approved event and books tickets
- [ ] Booking success shows QR code
- [ ] Negative tests: invalid login, invalid booking when capacity exceeded
- [ ] Screenshots for each step included in `/screenshots`

## Innovation
- QR code generation using Google Chart API: shows encoded booking info (eventId,userId,tickets).

## Notes & Security
- Passwords stored in plain text for demo; for production, use secure hashing (BCrypt).
- Add HTTPS for production.
- For an email feature, integrate JavaMail.

## Project Structure
(see repo root)

## Contact
If you need help setting up or running, message me.
