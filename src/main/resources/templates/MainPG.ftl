<html>
<head>
  <meta charset="UTF-8">
  <title>Table</title>

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
  <link rel="stylesheet" href="../assets/css/tealBody.css">

</head>

<body>
<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a>Friend 1</a>
  <a>Friend 2</a>
  <a>Friend 3</a>
  <a>Wumbo</a>
</div>
<span style="color:white;cursor:pointer;" onclick="openNav()"> <object align="right">&#9776; Friends List &emsp;&emsp;&emsp;</object></span>
<div id = "main">
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