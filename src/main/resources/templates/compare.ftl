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
<div id="main">
  <object align="left"><a href="/Frienduler/add"><input type="button" class="buttoner" value="Add Friends/Groups"/></a></object>
  <object align="center"><a href="/Frienduler/${userId}/createEvent"><input type="button" class="buttoner" value="Create Event"/></a></object>

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
          <form>
          	<#list friends as friend>
            	<tr><td><input class="Checkedbox" type="checkbox" name="friend[]" value="${friend.firstName} ${friend.lastName}"/></td><td>${friend.firstName} ${friend.lastName}</td>
            </#list>
            </tr>
            <td>
           
              <input type="button" class="buttoner" name="submit" value="Compare"/>
    
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
            <td>Party</td>
            <td>11/20/2018 </td>
            <td>11/22/2018</td>
            <td>5:30 PM</td>
            <td>8:00 PM</td>
          </tr>
          <tr>
            <td>Camping trip</td>
            <td>10/10/2018 </td>
            <td>10/12/2018</td>
            <td></td>
            <td></td>
          </tr>

          </tbody>
        </table>
        	 
      </div>
      

      	
  
    </section>
				
  </section>

  <object align="center"><a href="/Frienduler/${userId}"><input type="button" class="buttoner" value="Return to Home"/></a></object>


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