<html>
<head>
  <meta charset="UTF-8">
  <title>Table</title>

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
  <link rel="stylesheet" href="../assets/css/tealBody.css">
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
<div id = "main">
  <br>
  <object align="left"><a href="/Frienduler/${userId}/add"><input type="button" class="buttoner" value="Add Friends/Groups"/></a></object>
  <object align="center"><a href="/Frienduler/${userId}/createEvent"><input type="button" class="buttoner" value="Create Event"/></a></object>
  <section>
  <!--for demo wrap-->
  <h1>Events for ${userId}</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
      <tr>
        <th>Event</th>
        <th>Begin Date</th>
        <th>End Date</th>
        <th>Start Time</th>
        <th>End Time</th>
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
        	<td>${event.endDate}</td>
        	<td>${event.startTime}</td>
        	<td>${event.endTime}</td>
      	</tr>
      </#list>
      </tbody>
    </table>
  </div>
</section>

  <object align="center"><a href="/Frienduler/${userId}/compare"><input type="button" class="buttoner" value="Compare Schedules"/></a></object>

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