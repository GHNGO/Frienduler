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
  <a>Friend 1</a>
  <a>Friend 2</a>
  <a>Friend 3</a>
  <a>Wumbo</a>
</div>
<span style="color:white;cursor:pointer;" onclick="openNav()"> <object align="right"><input type="button" class="buttoner" value="&#9776; Friends List"/></object></span>
<div id = "main">
  <br>
  <object align="left"><a href="./add"><input type="button" class="buttoner" value="Add Friends/Groups"/></a></object>
  <object align="center"><a href="./createEvent"><input type="button" class="buttoner" value="Create Event"/></a></object>
  <section>
    <!--for demo wrap-->
    <h1>Your Events</h1>
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
        <tr>
          <td>Party</td>
          <td>11/20/2018 </td>
          <td>11/22/2018</td>
          <td>11:29 AM</td>
          <td>9:00 PM</td>
        </tr>
        <tr>
          <td>Party</td>
          <td>11/20/2018 </td>
          <td>11/22/2018</td>
          <td>7:00 PM</td>
          <td>11:59 PM</td>
        </tr>
        </tbody>
      </table>
    </div>
  </section>

  <object align="center"><a href="./compare"><input type="button" class="buttoner" value="Compare Schedules"/></a></object>

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