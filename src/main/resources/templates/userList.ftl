<html>

<head>
    <title>List of all users in database</title>
</head>

<body>

    <div>
        <div>
            <label>User List</label>
            <table border="1">
                <tr>
                	<td>Id</td>
                    <td>First Name</td>
                    <td>Last Name</td>
                </tr>
                <#list users as user>
                        <tr>
                        	<td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                        </tr>
                </#list>
            </table>
        </div>
    </div>


</body>

</html>
