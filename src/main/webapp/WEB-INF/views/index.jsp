<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <style>
            td, th {
                text-align:center;
            }
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/sockjs/1.0/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
        <script> 
            var socket = new SockJS('/JSPTestWEB/update');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/update', function (msg) {
                    location.reload(true);
                });
            });
            
            jQuery(document).ready(function($) {
		$("#form").submit(function(event) {
			event.preventDefault();
			updateUser();
                        });
                });
            
            function updateUser() {
                var data = {}
                data["id"] = $("#id").val();
                data["username"] = $("#username").val();
                
                $.ajax({
                        type : "POST",
                        contentType : "application/json",
                        url : "updateUser",
                        data : JSON.stringify(data),
                        dataType : 'json',
                        timeout : 100000,
                        success : function(data) {
                                
                        }
                });
            }
        </script>   
    </head>
    <body>
        <c:if test="${not empty users}">
            <table border="1" cellspacing="0" cellpadding="7px">
                <tr>
                    <th>id</th>
                    <th>username</th>
                    <th>password</th>
                    <th>firstname</th>
                    <th>lastname</th>
                    <th>email</th>
                </tr>
                <c:forEach var="u" items="${users}">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.username}</td>
                        <td>${u.password}</td>
                        <td>${u.firstname}</td>
                        <td>${u.lastname}</td>
                        <td>${u.email}</td>
                    </tr>
                </c:forEach>
            </table><br/>
            <form method="POST" action="updateUser" modelAttribute="user" id="form">
                <label for="id">User ID:  </form>
                <input id="id" name="id" size="5"/>
                <label for="username">Username:  </form>
                <input id="username" name="username" size="18"/>
                <input type="submit" value="Update"></input>
            <form>
        </c:if>
    </body>
</html>