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
  <a>Friend 1</a>
  <a>Friend 2</a>
  <a>Friend 3</a>
  <a>Wumbo</a>
</div>
<span style="color:white;cursor:pointer;" onclick="openNav()"> <object align="right">&#9776; Friends List &emsp;&emsp;&emsp;</object></span>

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
          <form>
            <tr><td><input class="Checkedbox" type="checkbox" name="friend[]" value="Loyed"/></td><td>loyed</td>
            <tr><td><input class="Checkedbox" type="checkbox" name="friend[]" value="jarod"/></td><td>jarod</td>
            <tr><td><input class="Checkedbox" type="checkbox" name="friend[]" value="conner"/></td><td>conner</td>
            <tr><td><input class="Checkedbox" type="checkbox" name="friend[]" value="genny"/></td><td>genny</td>
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
  				<div class="page-direction-button">
             		<a href="/index.html"><i class="fa fa-phone"></i>Return</a>
           		</div>
      	
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