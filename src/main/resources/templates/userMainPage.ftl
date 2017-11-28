<html>
<head>
  <meta charset="UTF-8">
  <title>Table</title>

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
  <link rel="stylesheet" href="/assets/css/tealBody.css">
  <script src="/js/addEventPage.js"></script>
	<link href="/js/tealBody.css" rel="stylesheet">
</head>

<body class="sexy-body">
<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <#list friends as friend>
  	<a>${friend.firstName} ${friend.lastName}</a>
  </#list>
</div>
<span style="color:white;cursor:pointer;" onclick="openNav()"> <object align="right"><input type="button" class="buttoner" value="&#9776; Friends List"/></object></span>
<br>
<object align="left"><a href="/Frienduler/user/${userId}/manageFriendsAndGroups"><input type="button" class="buttoner" value="Manage Friends/Groups"/></a></object>
<object align="center"><a href="/Frienduler/user/${userId}/createEvent"><input type="button" class="buttoner" value="Create Event"/></a></object>
<div id = "main">
  <section>
  <h1>Events for ${userFullName}</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
      <tr>
        <th>Event</th>
        <th>Start Date</th>
        <th>Start Time</th>
        <th>End Date</th>
        <th>End Time</th>
        <th>Delete Event</th>
      </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
      <#list events as event>
    	<tr>
        	<td>${event.name}</td>
        	<td>${event.startDate}</td>
        	<td>${event.startTime}</td>
            <td>${event.endDate}</td>
        	<td>${event.endTime}</td>
          <td><input type="button" class="buttoner" onclick="deleteEvent('${userId}', '${event.name}')" value="Delete Event"/></td>
      	</tr>
      </#list>
      </tbody>
    </table>
  </div>
</section>

  <object align="center"><a href="/Frienduler/user/${userId}/compare"><input type="button" class="buttoner" value="Compare Schedules"/></a></object>

  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="src/main/resources/static/assets/js/CompDex"></script>

<script>
  function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginRight = "250px";
  }

  function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginRight = "0";
  }
</script>
</div>
</body>
</html>
