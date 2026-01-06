<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <style>
        /* ===== EVENT REGISTRATION FORM ===== */

        .reg-card {
            max-width: 800px;
            margin: 40px auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.15);
        }

        .reg-title {
            color: #004080;
            margin-bottom: 5px;
        }

        .reg-sub {
            color: #666;
            margin-bottom: 25px;
        }

        .reg-label {
            font-weight: bold;
            margin-bottom: 6px;
            display: block;
        }

        .reg-input,
        .reg-select {
            width: 100%;
            padding: 10px;
            margin-bottom: 18px;
            border-radius: 6px;
            border: 1px solid #ccc;
        }

        .reg-row {
            display: flex;
            gap: 15px;
        }

        .reg-col {
            flex: 1;
        }

        .reg-radio {
            margin: 10px 0 20px;
        }

        .reg-radio label {
            margin-right: 20px;
        }

        .reg-btn {
            width: 100%;
            background-color: #0059b3;
            color: white;
            padding: 14px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
        }

        .reg-btn:hover {
            background-color: #3498db;
        }
    </style>
</head>
<body>

<%
    String eventName = request.getParameter("eventName");
    if (eventName == null) eventName = "";
%>

<div class="reg-card">
    <h2 class="reg-title">Event Registration Form</h2>
    <p class="reg-sub">Please fill all required details carefully</p>

    <!-- IMPORTANT: servlet name must match -->
<form action="RegisterEventServlet" method="post">

        <!-- EVENT -->
        <label class="reg-label">Event Name</label>
        <input type="text" class="reg-input"
               name="eventName" value="<%=eventName%>" readonly>

        <!-- NAME -->
        <div class="reg-row">
            <div class="reg-col">
                <label class="reg-label">First Name</label>
                <input type="text" class="reg-input" name="fname" required>
            </div>
            <div class="reg-col">
                <label class="reg-label">Middle Name</label>
                <input type="text" class="reg-input" name="mname">
            </div>
            <div class="reg-col">
                <label class="reg-label">Last Name</label>
                <input type="text" class="reg-input" name="lname" required>
            </div>
        </div>

        <!-- CONTACT -->
        <div class="reg-row">
            <div class="reg-col">
                <label class="reg-label">Contact Number</label>
                <input type="tel" class="reg-input"
                       name="contact" pattern="[0-9]{10}" required>
            </div>
            <div class="reg-col">
                <label class="reg-label">Email</label>
                <input type="email" class="reg-input" name="email" required>
            </div>
        </div>

        <!-- EDUCATION -->
        <div class="reg-row">
            <div class="reg-col">
                <label class="reg-label">Department</label>
                <select class="reg-select" name="department" required>
                    <option value="">Select</option>
                    <option>Computer</option>
                    <option>IT</option>
                    <option>Mechanical</option>
                    <option>ENTC</option>
                    <option>Civil</option>
                </select>
            </div>

            <div class="reg-col">
                <label class="reg-label">Year</label>
                <select class="reg-select" name="year" required>
                    <option value="">Select</option>
                    <option>FY</option>
                    <option>SY</option>
                    <option>TY</option>
                    <option>Final Year</option>
                </select>
            </div>
        </div>

        <!-- GENDER -->
        <label class="reg-label">Gender</label>
        <div class="reg-radio">
            <label><input type="radio" name="gender" value="Male" required> Male</label>
            <label><input type="radio" name="gender" value="Female"> Female</label>
            <label><input type="radio" name="gender" value="Other"> Other</label>
        </div>

        <!-- SUBMIT -->
        <button type="submit" class="reg-btn">Register</button>

    </form>
</div>

</body>
</html>