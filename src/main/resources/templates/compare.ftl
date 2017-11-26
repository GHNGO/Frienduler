<html>
<head>

  <title>Compare Schedules</title>
  <link href="/js/tealBody.css" rel="stylesheet">
  <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
  <script src="/js/CompDex.js"></script>

</head>

<body class="pritty-body">
<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <#list friends as friend>
  	<a>${friend.firstName} ${friend.lastName}</a>
  </#list>
</div>
<span style="color:white;cursor:pointer;" onclick="openNav()"> <object align="right"><input type="button" class="buttoner" value="&#9776; Friends List"/></object></span>
<object align="left"><a href="/Frienduler/addFriend"><input type="button" class="buttoner" value="Add Friends/Groups"/></a></object>
<object align="center"><a href="/Frienduler/user/${userId}/createEvent"><input type="button" class="buttoner" value="Create Event"/></a></object>
<div id="main">
  <section class="wrapper">
    <section>
      <!--for demo wrap-->
      <h1>My Friends</h1>
      <div class="tbl-header">
        <table cellpadding="0" cellspacing="0" border="0">
          <thead>
          <tr>
            <th>Add</th>
            <th>Friends</th>
          </tr>
          </thead>
        </table>
      </div>
      <div class="tbl-content">
        <table cellpadding="0" cellspacing="0" border="0">
          <tbody>
          <form id="friendsToCompare">
          	<#list friends as friend>
            	<tr><td><input class="Checkedbox" type="checkbox" name="friend[]" value="${friend.firstName} ${friend.lastName}"/></td><td>${friend.firstName} ${friend.lastName}</td>
            </#list>
            </tr>
            <td>
              <input type="button" class="buttoner" name="submit" value="Compare" action="return getFriendsToCompare(${userId})" />
            </td>
            <td></td>
          </form>
          </tbody>
        </table>
      </div>
    </section>
    <section>
      <!--for demo wrap-->
      <h1>Events</h1>
      <div class="tbl-header">
        <table cellpadding="0" cellspacing="0" border="0" >
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
          <tr>
            <#list events as event>
              <td>${event.name}</td>
              <td>${event.startDate}</td>
              <td>${event.startTime}</td>
              <td>${event.endDate}</td>
              <td>${event.endTime}</td>
            </#list>
          </tr>
          </tbody>
        </table>
      </div>
    </section>
  </section>

  <script>
    function openNav() {
      document.getElementById("mySidenav").style.width = "250px";
      document.getElementById("main").style.marginRight = "250px";
    }
    function closeNav() {
      document.getElementById("mySidenav").style.width = "0";
      document.getElementById("main").style.marginRight = "0";
    }
    function getFriendsToCompare( userName ) {
      var friends = document.getElementById( "friendsToCompare" );
      var friendsChecked = [];
      for( var i = 0; i < friends.length; i++ ){
        if( friends[i].checked ){
          friendsChecked.push( friends[i] );
        }
      }
      $.ajax(
        {
          type : "POST",
          url  : "/Frienduler/user/" + userName + "/compare/result",
          data : { "friendsToCompare" : friendsChecked }
        });
    }
  </script>
</div>

<object align="center"><a href="/Frienduler/user/${userId}"><input type="button" class="buttoner" value="Return to Home"/></a></object>

</body>
</html>
