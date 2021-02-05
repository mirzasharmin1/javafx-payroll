INSERT INTO payrolldb.user (id, username, password, name, email) VALUES(1, 'sharmin', '12345678', 'Mirza Sharmin', 'mirza.sharmin1@gmail.com');

INSERT INTO payrolldb.department(id, name) VALUES(1, 'Product'), (2, 'Marketing'), (3, 'Finance'), (4, 'HR'), (5, 'QA'), (6, 'Devops'), (7, 'Support'), (8, 'IT'), (9, 'Engineering'), (10, 'Management');

INSERT INTO payrolldb.employee(id, firstName, lastName, dateOfBirth, email, phoneNo, dateOfJoining, monthlySalary, leaveBalance, position, departmentId) VALUES(1, 'Mirza', 'Sharmin', {ts '1994-02-14 00:00:00.00'}, 'mirza.sharmin1@gmail.com', '0955433311', {ts '2020-02-01 00:00:00.00'}, 250000.0, 20, 'Director', 10);
INSERT INTO payrolldb.address(id, addressLine1, addressLine2, city, country, state, zipcode, employeeId) VALUES(1, '505/115, The Iris Condominium, Srinakarin Road Soi 13', 'Suan Luang, Suan Luang', 'Bangkok', 'Thailand', 'Bangkok', '10250', 1);