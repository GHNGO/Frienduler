<html>

<head>
    <title>CS480 Demo Web Service</title>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>    
    <script src="/js/home-control.js"></script>
</head>

<body>    
    
    <#--<div>-->
        <#--This is a simple page to demonstrate the web UI development. -->
        <#--The key tools and techniques used include:-->
        <#--<ul>-->
            <#--<li>HTML - Obviously</li>-->
            <#--<li><a href="http://freemarker.org/">FreeMarker</a></li>-->
            <#--<li><a href="http://jquery.com/">JQuery</a></li>-->
            <#--<li><a href="http://api.jquery.com/jquery.ajax/">JQuery - AJAX</a></li>-->
        <#--</ul>-->
    <#--</div>-->

    <#--<hr>-->

    <div>
        <div>
            <label>User List</label>
            <table border="1">            
                <tr>
                    <td>ID</td>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Delete</td>
                </tr>
                <#list users as user>
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td><button onclick="deleteUser('${user.id}')">Delete</button></td>
                        </tr>
                </#list>
            </table>
        </div>
        
        <hr>
        
        <div>
            <label>Add User</label>
            <table border="1">
                <tr>
                    <td>ID</td>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Add</td>
                </tr>                
                <tr>
                    <td><input type="text" id="input_id"></td>
                    <td><input type="text" id="input_first_name"></td>
                    <td><input type="text" id="input_last_name"></td>
                    <td><button onclick="addUser()">Add</button></td>
                </tr>
            </table>
        </div>

        <hr>

        <div>
            <label>Query User</label>
            <input type="text" id="query_id"><button onclick="getUser()">Get</button>
            <table border="1" id="queryUserTable">
                <tr>
                    <td>Username</td>
                    <td>First Name</td>
                    <td>Last Name</td>
                </tr>
                <tr>
                    <td><label id="result_id"></td>
                    <td><label id="result_first_name"></td>
                    <td><label id="result_last_name"></td>
                </tr>
            </table>
        </div>

        <hr>

        <div>
            <label>Event List</label>
            <table border="1">
                <tr>
                    <td>Name</td>
                    <td>Linked User</td>
                    <td>Start Date</td>
                    <td>Start Time</td>
                    <td>End Date</td>
                    <td>End Time</td>
                    <td>Delete</td>
                </tr>
            <#list events as event>
                <tr>
                    <td>${event.name}</td>
                    <td>${event.linkedUserId}</td>
                    <td >${event.startDate}</td>
                    <td>${event.startTime}</td>
                    <td>${event.endDate}</td>
                    <td>${event.endTime}</td>
                    <td><button onclick="deleteEvent('${event.linkedUserId}', '${event.name}')">Delete</button></td>
                </tr>
            </#list>
            </table>
        </div>

        <hr>

        <div>
            <label>Add Event</label>
            <table border="1">
                <tr>
                    <td>Name</td>
                    <td>User ID</td>
                    <td>Start Date</td>
                    <td>Start Time</td>
                    <td>End Date</td>
                    <td>End Time</td>
                </tr>
                <tr>
                    <td><input type="text" id="input_event_name"></td>
                    <td><select datatype="text" itemtype="text" id="input_user_id">
                        <#list users as user>
                            <option value="${user.id}">${user.id}</option>
                        </#list>
                    </select></td>
                    <td><input type="date" id="input_start_date"></td>
                    <td><input type="time" id="input_start_time"></td>
                    <td><input type="date" id="input_end_date"></td>
                    <td><input type="time" id="input_end_time"></td>
                    <td><button onclick="addEvent()">Add</button></td>
                </tr>
            </table>
        </div>


    </div>
    
    
</body>

</html>